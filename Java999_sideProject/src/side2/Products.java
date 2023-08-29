package side2;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Products {

	private JFrame frame;
	private int quantity;
	private List<OptionDTO> list;
	private OptionDAO dao;
	private JComboBox comboBox;
	private JPanel panel;
	private Set<OptionDTO> selectedOptions = new HashSet<>();
	private Map<OptionDTO, JSpinner> sumOptionPrice = new HashMap<>();
	private int currentY = 10;
	private int optionTotalPrice;
	private long totalPrice = 0;
	private JLabel lblTotalPrice;

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
		btnNewButton.setBounds(489, 550, 320, 38);
		frame.getContentPane().add(btnNewButton);

		JLabel lblApName = new JLabel(dto.getApName());
		lblApName.setFont(new Font("굴림", Font.PLAIN, 18));
		lblApName.setBounds(32, 125, 349, 25);
		frame.getContentPane().add(lblApName);

		JLabel lblNewLabel_6 = new JLabel("주문금액");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_6.setBounds(502, 495, 85, 25);
		frame.getContentPane().add(lblNewLabel_6);

		JLabel lblMfr = new JLabel(dto.getApMfr());
		lblMfr.setBounds(32, 180, 143, 15);
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
		textArea.setText(dto.getApInfo());

		panel = new JPanel();

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(480, 110, 350, 337);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPane);

		dao = OptionDAOImple.getInstance();
		list = dao.serchByApId(dto.getApID());
		comboBox = new JComboBox<OptionDTO>();
		for (int i = 0; i < list.size(); i++) {
			comboBox.addItem(list.get(i));
		}
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OptionDTO selectedOption = (OptionDTO) comboBox.getSelectedItem();
				if (selectedOption != null && !selectedOptions.contains(selectedOption)) {
					optionClicked(selectedOption);
					selectedOptions.add(selectedOption);
				}
			}

		});
		
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

	public void purchaseProcess(ApplianceDTO dto) {
		PurchaseDAO dao = PurchaseDAOImple.getInstance();
		Session session = Session.getInstance();
		PurchaseDTO pdto = new PurchaseDTO(session.getDto().getMemberID(),dto.getApID());
		int result = dao.purchase(pdto);
		if(result == -1) {
			JOptionPane.showMessageDialog(null, "입력실패");
		}else {
			OrderDetailDAO orderDetailDAO = OrderDetailDAOImple.getInstance();
			//주문번호,옵션코드,주문수량,옵션가격
			OrderDetailDTO orderDTO = new OrderDetailDTO();
			for (Map.Entry<OptionDTO, JSpinner> entry : sumOptionPrice.entrySet()) {
			    OptionDTO option = entry.getKey();
			    int quantity = (int) entry.getValue().getValue();
			    orderDTO.getOrderOptions().put(option, quantity);
			}
			
		}
		
		
		
	}// end

	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}

	public String numberFormat(int num) {
		String formatPrice = NumberFormat.getNumberInstance().format(num);
		return formatPrice;
	}

	public String longNumberFormat(long num) {
		String formatPrice = NumberFormat.getNumberInstance().format(num);
		return formatPrice;
	}

	public void optionClicked(OptionDTO selectedOption) {

		optionTotalPrice = selectedOption.getPrice();
		
		
		lblTotalPrice.setText(longNumberFormat(totalPrice) + "원");
		JPanel optionPanel = new JPanel();
		optionPanel.setLayout(null);
		optionPanel.setBounds(0, currentY, panel.getWidth(), 160);
		JLabel lblNewLabel_3 = new JLabel("<html>" + selectedOption.toString() + "</html>");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setBounds(22, 29, 290, 65);
		optionPanel.add(lblNewLabel_3);

		JLabel lblOptionTotalPrice = new JLabel(numberFormat(selectedOption.getPrice()));
		lblOptionTotalPrice.setFont(new Font("굴림", Font.BOLD, 15));
		lblOptionTotalPrice.setBounds(22, 136, 193, 22);
		optionPanel.add(lblOptionTotalPrice);

		SpinnerNumberModel model = new SpinnerNumberModel(1, 1, selectedOption.getStock(), 1);
		JSpinner spinner = new JSpinner(model);
		spinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSpinner source = (JSpinner) e.getSource();
				Number value = (Number) source.getValue();
				int intValue = value.intValue();
				optionTotalPrice = intValue * selectedOption.getPrice();
				lblOptionTotalPrice.setText(numberFormat(optionTotalPrice)+"원");
				totalPriceUpdate();
				lblTotalPrice.setText(longNumberFormat(totalPrice) + "원");
			}
		});
		spinner.setBounds(22, 104, 59, 22);
		optionPanel.add(spinner);
		
		sumOptionPrice.put(selectedOption, spinner);
		totalPriceUpdate();
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 168, 324, 16);
		optionPanel.add(separator_3);

		JButton btnNewButton_2 = new JButton("X");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedOptions.remove(selectedOption);
				panel.remove(optionPanel);
				
		        sumOptionPrice.remove(selectedOption);
		        
		        totalPriceUpdate();
				lblTotalPrice.setText(longNumberFormat(totalPrice) + "원");
				currentY -= 120;

				int preferredHeight = currentY + 160;
				panel.setPreferredSize(new Dimension(panel.getWidth(), preferredHeight));

				panel.revalidate();
				panel.repaint();
			}
		});
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 8));
		btnNewButton_2.setBounds(273, 10, 39, 29);

		optionPanel.add(btnNewButton_2);

		panel.add(optionPanel);
		currentY += 120;
		int preferredHeight = currentY + 160;
		panel.setPreferredSize(new Dimension(panel.getWidth(), preferredHeight));

		panel.revalidate();
		panel.repaint();

	}

	public void totalPriceUpdate() {
		totalPrice = 0;
		    for (Map.Entry<OptionDTO, JSpinner> entry : sumOptionPrice.entrySet()) {
		        OptionDTO option = entry.getKey();
		        JSpinner spinner = entry.getValue();

		        totalPrice += option.getPrice() * (int) spinner.getValue();;
		    }

		lblTotalPrice.setText(longNumberFormat(totalPrice) + "원");
		panel.revalidate();
		panel.repaint();
	}
}// end Products