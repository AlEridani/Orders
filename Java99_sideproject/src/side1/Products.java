package side1;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;



public class Products {

	private JFrame frame;
	private long totalPrice;
	private int quantity;
	private List<ApplianceDTO> list;
	private ApplianceDAO dao;
	private JComboBox comboBox;
	private JPanel panel;
	private Set<ApplianceDTO> selectedOptions = new HashSet<>();
	private Map<ApplianceDTO, JSpinner> sumOptionPrice = new HashMap<>();
	private int currentY = 10;
	private int optionTotalPrice;

	private JLabel lblTotalPrice;
	

	public Products(Session session, ArrayList<ApplianceDTO> list) {
		initialize(session, list);
	}

	private void initialize(Session session, ArrayList<ApplianceDTO> list) {
		
		quantity = 1;
		frame = new JFrame();
		frame.setBounds(100, 100, 874, 648);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("주문하기");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(171, 10, 115, 64);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("주문자 정보");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1.setBounds(32, 429, 97, 25);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblName = new JLabel(session.getDto().getName());
		lblName.setBounds(32, 476, 210, 15);
		frame.getContentPane().add(lblName);

		JLabel lblPhone = new JLabel(session.getDto().getPhone());
		lblPhone.setBounds(32, 501, 180, 15);
		frame.getContentPane().add(lblPhone);

		JLabel lblEmail = new JLabel(session.getDto().getEmail());
		lblEmail.setBounds(32, 526, 180, 15);
		frame.getContentPane().add(lblEmail);

		JLabel lblNewLabel_1_1 = new JLabel("상품정보");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(32, 95, 97, 25);
		frame.getContentPane().add(lblNewLabel_1_1);

		JButton btnNewButton = new JButton("구입");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				purchaseProcess(dto);
				System.out.println("구입버튼 누른후 끝나는시점");
			}
		});
		btnNewButton.setBounds(489, 550, 320, 38);
		frame.getContentPane().add(btnNewButton);
		JLabel lblApName = new JLabel("<html>" + list.get(0).getApName() + "</html>");
		lblApName.setFont(new Font("굴림", Font.PLAIN, 18));
		lblApName.setBounds(32, 125, 349, 50);
		frame.getContentPane().add(lblApName);

		JLabel lblNewLabel_6 = new JLabel("주문금액");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_6.setBounds(502, 495, 85, 25);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblMfr = new JLabel(list.get(0).getApMfr());
		lblMfr.setFont(new Font("굴림", Font.PLAIN, 15));
		lblMfr.setBounds(32, 190, 180, 33);
		frame.getContentPane().add(lblMfr);

		JLabel lblApId = new JLabel();
		lblApId.setBounds(32, 155, 194, 15);
		frame.getContentPane().add(lblApId);

		JSeparator separator = new JSeparator();
		separator.setBackground(SystemColor.desktop);
		separator.setBounds(12, 424, 410, 2);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(UIManager.getColor("Button.focus"));
		separator_1.setBounds(484, 460, 335, 15);
		frame.getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(UIManager.getColor("Button.focus"));
		separator_2.setBounds(484, 543, 335, 2);
		frame.getContentPane().add(separator_2);

		JButton btnNewButton_1 = new JButton("변경");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserUpdate userUpdate = new UserUpdate();
				userUpdate.show();
			}
		});
		btnNewButton_1.setBounds(328, 472, 80, 23);
		frame.getContentPane().add(btnNewButton_1);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(23, 233, 410, 162);
		frame.getContentPane().add(textArea);
		

		panel = new JPanel();

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(480, 110, 350, 337);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPane);


		ArrayList<ApplianceDTO> item = dao.appPurchaseShow(list.get(0).getApName());
		comboBox = new JComboBox<ApplianceDTO>();
		for(int i =0; i < list.size(); i++) {
			comboBox.addItem(item.get(i));
		}
	

		comboBox.setBounds(480, 57, 350, 25);
		frame.getContentPane().add(comboBox);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		lblTotalPrice = new JLabel(longNumberFormat(totalPrice) + "원");
		lblTotalPrice.setFont(new Font("굴림", Font.PLAIN, 18));
		lblTotalPrice.setBounds(599, 495, 210, 21);
		lblTotalPrice.setHorizontalAlignment(JLabel.RIGHT);
		frame.getContentPane().add(lblTotalPrice);

	}

	public void show() {
		frame.setVisible(true);
	}// end show
	/**
	 * 
	 * 
	 */
	public void purchaseProcess(ApplianceDTO apDto) {
		PurchaseDAO dao = PurchaseDAOImple.getInstance();

		Session session = Session.getInstance();
		System.out.println(apDto.getApPrice());
		totalPrice = (long) apDto.getApPrice() * quantity;
		System.out.println("구매 메서드의 long확인 : " + totalPrice);
		PurchaseDTO dto = new PurchaseDTO(session.getDto().getMemberID(), apDto.getApID(), quantity, totalPrice);
		int result = dao.purchase(dto);
		if (result == -1) {
			JOptionPane.showMessageDialog(null, "구매 실패");
		} else {
			JOptionPane.showMessageDialog(null, "구매 성공");
			apDto.setApStock(apDto.getApStock() - quantity);

			ApplianceDAO apDao = ApplianceDAOImple.getInstance();
			apDao.appUpdate(apDto);
			System.out.println("스톡에서 빼기");
			frame.dispose();
		}

	}// end

	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}

	public String numberFormat(int num) {
		String formattedPrice = NumberFormat.getNumberInstance().format(num);
		return formattedPrice;
	}

	public String longNumberFormat(long num) {
		String formattedPrice = NumberFormat.getNumberInstance().format(num);
		return formattedPrice;
	}
	
	public void totalPriceUpdate() {
		totalPrice = 0;
		for (Map.Entry<ApplianceDTO, JSpinner> entry : sumOptionPrice.entrySet()) {
			ApplianceDTO dto = entry.getKey();
			JSpinner spinner = entry.getValue();

			totalPrice += dto.getApPrice() * (int) spinner.getValue();
			;
		}

		lblTotalPrice.setText(longNumberFormat(totalPrice) + "원");
		panel.revalidate();
		panel.repaint();
	}

}// end Products