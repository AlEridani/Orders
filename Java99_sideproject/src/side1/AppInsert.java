package side1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JComboBox;

public class AppInsert {

	private JFrame frame;
	private JTextField textApId;
	private JTextField textApName;
	private JTextField textApPrice;
	private JTextField textApMfr;
	private JTextField textStock;
	private JTextField textOption;

	/**
	 * @wbp.parser.constructor
	 */
	public AppInsert() {
		initialize();
	}

	public AppInsert(ApplianceDTO dto) {// 수정용 데이터 가져오기
		initialize2(dto);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 499, 557);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("상품번호");
		lblNewLabel.setBounds(51, 48, 57, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("상품이름");
		lblNewLabel_1.setBounds(51, 112, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("상품가격");
		lblNewLabel_2.setBounds(51, 161, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("제조사");
		lblNewLabel_3.setBounds(51, 212, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("재고");
		lblNewLabel_4.setBounds(51, 258, 57, 15);
		frame.getContentPane().add(lblNewLabel_4);

		JButton btnNewButton = new JButton("상품 등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApInsert();

			}
		});
		btnNewButton.setBounds(255, 485, 97, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(364, 485, 97, 23);
		frame.getContentPane().add(btnNewButton_1);

		textApId = new JTextField();
		textApId.setBounds(120, 45, 250, 21);
		frame.getContentPane().add(textApId);
		textApId.setColumns(10);

		textApName = new JTextField();
		textApName.setColumns(10);
		textApName.setBounds(120, 109, 250, 21);
		frame.getContentPane().add(textApName);

		textApPrice = new JTextField();
		textApPrice.setColumns(10);
		textApPrice.setBounds(120, 158, 250, 21);
		frame.getContentPane().add(textApPrice);

		textApMfr = new JTextField();
		textApMfr.setColumns(10);
		textApMfr.setBounds(120, 209, 250, 21);
		frame.getContentPane().add(textApMfr);

		textStock = new JTextField();
		textStock.setColumns(10);
		textStock.setBounds(120, 255, 82, 21);
		frame.getContentPane().add(textStock);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		
		comboBox.addItem("TV/영상가전");
		comboBox.addItem("주방가전");
		comboBox.addItem("생활가전");
		comboBox.addItem("음향가전");
		comboBox.addItem("노트북");
		comboBox.addItem("태블릿/스마트폰");
		comboBox.setBounds(120, 304, 120, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_5 = new JLabel("분류");
		lblNewLabel_5.setBounds(51, 308, 57, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		textOption = new JTextField();
		textOption.setBounds(120, 364, 250, 21);
		frame.getContentPane().add(textOption);
		textOption.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("옵션");
		lblNewLabel_6.setBounds(51, 367, 57, 15);
		frame.getContentPane().add(lblNewLabel_6);
	}// end initialize

	private void initialize2(ApplianceDTO dto) {
		frame = new JFrame();
		frame.setBounds(100, 100, 499, 557);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("상품번호");
		lblNewLabel.setBounds(51, 48, 57, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("상품이름");
		lblNewLabel_1.setBounds(51, 112, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("상품가격");
		lblNewLabel_2.setBounds(51, 161, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("제조사");
		lblNewLabel_3.setBounds(51, 212, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("재고");
		lblNewLabel_4.setBounds(51, 258, 57, 15);
		frame.getContentPane().add(lblNewLabel_4);

		JButton btnNewButton = new JButton("상품 수정");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appUpdate();

			}
		});
		btnNewButton.setBounds(255, 485, 97, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(364, 485, 97, 23);
		frame.getContentPane().add(btnNewButton_1);

		textApId = new JTextField(dto.getApID());
		textApId.setBounds(120, 45, 250, 21);
		frame.getContentPane().add(textApId);
		textApId.setColumns(10);
		textApId.setEnabled(false);

		textApName = new JTextField(dto.getApName());
		textApName.setColumns(10);
		textApName.setBounds(120, 109, 250, 21);
		frame.getContentPane().add(textApName);

		textApPrice = new JTextField(String.valueOf(dto.getApPrice()));
		textApPrice.setColumns(10);
		textApPrice.setBounds(120, 158, 250, 21);
		frame.getContentPane().add(textApPrice);

		textApMfr = new JTextField(dto.getApMfr());
		textApMfr.setColumns(10);
		textApMfr.setBounds(120, 209, 250, 21);
		frame.getContentPane().add(textApMfr);

		textStock = new JTextField(String.valueOf(dto.getApStock()));
		textStock.setColumns(10);
		textStock.setBounds(120, 255, 82, 21);
		frame.getContentPane().add(textStock);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("TV/영상가전");
		comboBox.addItem("주방가전");
		comboBox.addItem("생활가전");
		comboBox.addItem("음향가전");
		comboBox.addItem("노트북");
		comboBox.addItem("태블릿/스마트폰");
		comboBox.setBounds(120, 304, 82, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_5 = new JLabel("분류");
		lblNewLabel_5.setBounds(51, 308, 57, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		textOption = new JTextField();
		textOption.setBounds(120, 364, 250, 21);
		frame.getContentPane().add(textOption);
		textOption.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("옵션");
		lblNewLabel_6.setBounds(51, 367, 57, 15);
		frame.getContentPane().add(lblNewLabel_6);
	}// end initialize2

	public void show() {
		frame.setVisible(true);
	}

	public void ApInsert() {
		ApplianceDTO dto = appDataInsert();
		if (dto != null) {
			ApplianceDAO dao = ApplianceDAOImple.getInstance();
			int result = dao.appInsert(dto);
			if (result == -1) {
				JOptionPane.showMessageDialog(null, "등록실패");
			} else {
				JOptionPane.showMessageDialog(null, "등록성공");
				frame.dispose();
			}
		}
	}// end ApInsert

	public void appUpdate() {
		ApplianceDTO dto = appDataInsert();
		if (dto != null) {
			ApplianceDAO dao = ApplianceDAOImple.getInstance();
			int result = dao.appUpdate(dto);
			if (result == -1) {
				JOptionPane.showMessageDialog(null, "수정 실패");
			} else {
				JOptionPane.showMessageDialog(null, "수정 성공");
				frame.dispose();
			}
		}
	}// end appUpdate

	/**
	 * Insert와 Update가 너무 겹쳐서 겹치는부분 빼둠
	 * 
	 * @return
	 */

	private ApplianceDTO appDataInsert() {
		String apId = textApId.getText();
		String apName = textApName.getText();
		String apPrice = textApPrice.getText();
		String apMfr = textApMfr.getText();
		String apStock = textStock.getText();
		String optionName = textOption.getText();
	

		int price = stringToInteger(apPrice);
		int stock = stringToInteger(apStock);
		if (apId.isBlank() || apName.isBlank()) {
			JOptionPane.showMessageDialog(null, "상품번호와 상품이름은 필수적으로 입력해야합니다 ");
		}
		if (price == -1 || stock == -1) {
			System.out.println("가격 재고 잘못입력");
			JOptionPane.showMessageDialog(null, "가격과 재고에는 숫자만 들어갈 수 있습니다");
			return null;
		} else {
			return new ApplianceDTO(apId, apName, price, apMfr, stock);
		}
	}

	/**
	 * 텍스트필드의 입력된값이 String으로 들어와서 숫자만 있는 문자열이면 integer형으로 리턴 아닐경우 -1 리턴
	 */
	public int stringToInteger(String str) {
		System.out.println("돌려줄 리턴값 확인"+ str);
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!Character.isDigit(ch) && !Character.isWhitespace(ch)) {
				return -1;
			}
		}
		if (str.isEmpty()) {
			return -1;
		}
		
		return Integer.parseInt(str);
	}// end StringToInteger

	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}// end addFrameCloseListener
}// end AppInsert