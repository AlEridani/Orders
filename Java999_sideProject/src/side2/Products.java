package side2;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Products {

	private JFrame frame;
	private long totalPrice;
	private int quantity;
	private List<OptionDTO> list;
	private OptionDAO dao;

	public Products(Session session, ApplianceDTO dto) {
		initialize(session, dto);
	}

	private void initialize(Session session, ApplianceDTO dto) {
//		quantity = dto.getApPrice();
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
		btnNewButton.setBounds(509, 550, 320, 38);
		frame.getContentPane().add(btnNewButton);

		JLabel lblApName = new JLabel(dto.getApName());
		lblApName.setFont(new Font("굴림", Font.PLAIN, 18));
		lblApName.setBounds(32, 125, 349, 25);
		frame.getContentPane().add(lblApName);

		JLabel lblNewLabel_6 = new JLabel("주문금액");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_6.setBounds(525, 513, 289, 25);
		frame.getContentPane().add(lblNewLabel_6);

		JLabel lblMfr = new JLabel(dto.getApMfr());
		lblMfr.setBounds(32, 180, 143, 15);
		frame.getContentPane().add(lblMfr);

		JLabel lblApId = new JLabel();
		lblApId.setBounds(32, 155, 194, 15);
		frame.getContentPane().add(lblApId);

		JSeparator separator = new JSeparator();
		separator.setBackground(SystemColor.desktop);
		separator.setBounds(12, 577, 410, 2);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(UIManager.getColor("Button.focus"));
		separator_1.setBounds(504, 453, 335, 15);
		frame.getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(UIManager.getColor("Button.focus"));
		separator_2.setBounds(504, 543, 335, 2);
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
		
		
		//커밋테스트
		JPanel panel = new JPanel();
		panel.setBounds(509, 110, 326, 337);
		frame.getContentPane().add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JLabel lblNewLabel_2 = new JLabel("총n개");
		lblNewLabel_2.setBounds(526, 480, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(23, 233, 410, 162);
		frame.getContentPane().add(textArea);
		textArea.setText(dto.getApInfo());
		
		JComboBox comboBox = new JComboBox();
		dao = OptionDAOImple.getInstance();
		list = dao.serchByApId(dto.getApID());
		comboBox = new JComboBox<OptionDTO>();
		for (int i = 0; i < list.size(); i++) {
			comboBox.addItem(list.get(i));
		}

		comboBox.setBounds(509, 59, 320, 25);
		frame.getContentPane().add(comboBox);

//		int maxStock = dto.getApStock();

//		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, maxStock, 1));
//		spinner.setBounds(331, 356, 77, 24);
//		frame.getContentPane().add(spinner);
//		Integer currentValue = (Integer) spinner.getValue();

//		JLabel lblNewLabel_2 = new JLabel("가격");
//		lblNewLabel_2.setBounds(144, 403, 57, 15);
//		frame.getContentPane().add(lblNewLabel_2);

//		JLabel lblPrice = new JLabel(numberFormat(dto.getApPrice()) + " 원");
//		lblPrice.setHorizontalAlignment(JLabel.RIGHT);
//		lblPrice.setFont(new Font("굴림", Font.BOLD, 15));
//		lblPrice.setBounds(255, 396, 153, 29);
//		frame.getContentPane().add(lblPrice);

//		JLabel lblTotalPrice = new JLabel(numberFormat(currentValue * dto.getApPrice()) + " 원");
//		lblTotalPrice.setFont(new Font("굴림", Font.BOLD, 18));
//		lblTotalPrice.setBounds(198, 464, 210, 25);
//		frame.getContentPane().add(lblTotalPrice);
//		lblTotalPrice.setHorizontalAlignment(JLabel.RIGHT);
//		spinner.addChangeListener(new ChangeListener() {
//			@Override
//			public void stateChanged(ChangeEvent e) {
//				quantity = (Integer) spinner.getValue();
//				lblTotalPrice.setText(longNumberFormat(quantity * (long) dto.getApPrice()) + " 원");
//				totalPrice = quantity * (long) dto.getApPrice();
//			}
//		});
//
	}

	private char[] getApID() {
		// TODO Auto-generated method stub
		return null;
	}

	public void show() {
		frame.setVisible(true);
	}// end show

//	public void purchaseProcess(ApplianceDTO apDto) {
//		PurchaseDAO dao = PurchaseDAOImple.getInstance();
//
//		Session session = Session.getInstance();
//		System.out.println(apDto.getApPrice());
//		totalPrice = (long) apDto.getApPrice() * quantity;
//		System.out.println("구매 메서드의 long확인 : " + totalPrice);
//		PurchaseDTO dto = new PurchaseDTO(session.getDto().getMemberID(), apDto.getApID(), quantity, totalPrice);
//		int result = dao.purchase(dto);
//		if (result == -1) {
//			JOptionPane.showMessageDialog(null, "구매 실패");
//		} else {
//			JOptionPane.showMessageDialog(null, "구매 성공");
//			apDto.setApStock(apDto.getApStock() - quantity);
//
//			ApplianceDAO apDao = ApplianceDAOImple.getInstance();
//			apDao.appUpdate(apDto);
//			System.out.println("스톡에서 빼기");
//			frame.dispose();
//		}
//
//	}// end

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
}// end Products