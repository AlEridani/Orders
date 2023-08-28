package side2;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class AppInsert {

	private JFrame frame;
	private JTextField textApName;
	private JTextField textApMfr;
	private JTextArea textAppInfo;
	private JTextField textOptionId;
	private JTextField textOptionName;
	private JTextField textPrice;
	private JTextField textStock;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JPanel panel;

	private boolean insertToken = false;

	private int clickedCount = 0;
	private int y = -20;
	private int HEIGHT = 20;

	private int OPTION_ID_XPOS = 20;
	private int TEXT_ID_XPOS = 90;
	private int OPTION_NAME_XPOS = 280;
	private int TEXT_NAME_XPOS = 330;
	private int OPTION_PRICE_XPOS = 560;
	private int TEXT_PRICE_XPOS = 600;
	private int OPTION_STOCK_XPOS = 730;
	private int TEST_STOCK_XPOS = 770;

	private int LABEL_WIDTH = 60;
	private int TEXT_ID_WIDTH = 160;
	private int TEXT_NAME_WIDTH = 200;
	private int TEXT_PRICE_WIDTH = 120;
	private int TEXT_STOCK_WIDTH = 100;
	
	
	JScrollPane scrollPane_1;

	List<JTextField> optionIdFields = new ArrayList<>();
	List<JTextField> optionNameFields = new ArrayList<>();
	List<JTextField> priceFields = new ArrayList<>();
	List<JTextField> stockFields = new ArrayList<>();

	List<JTextField> tempOptionIdFields = new ArrayList<>();
	List<JTextField> tempOptionNameFields = new ArrayList<>();
	List<JTextField> tempPriceFields = new ArrayList<>();
	List<JTextField> tempStockFields = new ArrayList<>();

	public AppInsert() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1022, 821);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(23, 299, 950, 250); // 초기 높이를 200으로 설정 (스크롤 패널 높이와 동일)
		panel.setPreferredSize(new Dimension(950, 1200)); // 초기 선호 크기를 설정
		scrollPane_1 = new JScrollPane(panel);
		panel.setLayout(null);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(23, 299, 950, 230);
		frame.getContentPane().add(scrollPane_1);

		JLabel lblNewLabel_1 = new JLabel("상품이름");
		lblNewLabel_1.setBounds(51, 30, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("제조사");
		lblNewLabel_3.setBounds(51, 67, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);

		JButton btnNewButton = new JButton("상품 등록");
		btnNewButton.setBounds(667, 702, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appInsert();
			}
		});
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.setBounds(810, 702, 97, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnNewButton_1);

		textApName = new JTextField();
		textApName.setBounds(120, 27, 750, 21);
		textApName.setColumns(10);
		frame.getContentPane().add(textApName);

		textApMfr = new JTextField();
		textApMfr.setBounds(120, 64, 750, 21);
		textApMfr.setColumns(10);
		frame.getContentPane().add(textApMfr);

		textAppInfo = new JTextArea();

		JScrollPane scrollPane = new JScrollPane(textAppInfo);
		scrollPane.setBounds(117, 102, 750, 120);
		frame.getContentPane().add(scrollPane);

		JLabel lblNewLabel_2 = new JLabel("상품설명");
		lblNewLabel_2.setBounds(51, 107, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("옵션(필수)");
		lblNewLabel.setBounds(51, 266, 122, 31);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton_2 = new JButton("추가");
		btnNewButton_2.setBounds(181, 270, 97, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clickedCount < 5) {
					clickedCount++;
					addOption(clickedCount);
				} else {
					System.out.println("옵션 최대 수치 도달");
				}

			}
		});
		frame.getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("확인");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isFieldNotEmpty(tempOptionIdFields) && isFieldNotEmpty(tempOptionNameFields)
						&& isFieldNotEmpty(tempPriceFields) && isFieldNotEmpty(tempStockFields)) {

					optionIdFields.addAll(tempOptionIdFields);
					optionNameFields.addAll(tempOptionNameFields);
					priceFields.addAll(tempPriceFields);
					stockFields.addAll(tempStockFields);

					disableTextFields(tempOptionIdFields);
					disableTextFields(tempOptionNameFields);
					disableTextFields(tempPriceFields);
					disableTextFields(tempStockFields);

					tempOptionIdFields.clear();
					tempOptionNameFields.clear();
					tempPriceFields.clear();
					tempStockFields.clear();
					insertToken = true;
				} else {
					JOptionPane.showMessageDialog(null, "모든 필드를 채워주세요.");
				}
			}
		});
		btnNewButton_3.setBounds(852, 266, 97, 23);
		frame.getContentPane().add(btnNewButton_3);

		addOption(clickedCount);

	}// end initialize

	public void show() {
		frame.setVisible(true); // 창 보이기
	}

	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}// end addFrameCloseListener

	public void appInsert() {
		String apName = textApName.getText();
		String apMfr = textApMfr.getText();
		String apInfo = textAppInfo.getText();
		int apId = -1;
		ApplianceDAO dao = ApplianceDAOImple.getInstance();

		if (apInfo.isBlank()) {
			apInfo = "상품 정보가 없습니다";
		}

		// apName이 빈칸이 아니면서 mfr가 빈칸 이 아니면

		// false false false
		if ((apName.isBlank() || apMfr.isBlank())) {
			JOptionPane.showMessageDialog(null, "제목과 제조사를 입력해 주세요");
		} else if (insertToken == false) {
			JOptionPane.showMessageDialog(null, "옵션칸을 입력해주세요");
		} else {
			ApplianceDTO dto = new ApplianceDTO(apName, apMfr, apInfo);
			apId = dao.appInsert(dto);
		
			for (int i = 0; i < clickedCount + 1; i++) {
				
				String optionId = optionIdFields.get(i).getText();
				String optionName = optionNameFields.get(i).getText();
				int price = Integer.parseInt(priceFields.get(i).getText());
				int stock = Integer.parseInt(stockFields.get(i).getText());
				
				OptionDAO odao = OptionDAOImple.getInstance();
				System.out.println("odto 확인 : " + optionId + optionName + price + stock);
				OptionDTO odto = new OptionDTO(optionId, optionName, price, stock, apId, (i+1));
				
				int result = odao.insert(odto);

				if (result == 1) {
					System.out.println("입력 성공");
					JOptionPane.showMessageDialog(null, "등록에 성공했습니다");
					frame.dispose();
				} else {
					System.out.println("입력 실패");
					
				}
			}
		}
	}

	public void addOption(int count) {// 옵션 추가는 5번까지만 가능하게

		System.out.println("카운트 확인" + count);
		if (count < 5) {
			System.out.println("addOption 메서드 동작확인");
			y += 40;
			JLabel lblOptionId = new JLabel("상품코드");
			lblOptionId.setBounds(OPTION_ID_XPOS, y, LABEL_WIDTH, HEIGHT);
			panel.add(lblOptionId);

			JTextField textOptionId = new JTextField();
			textOptionId.setBounds(TEXT_ID_XPOS, y, TEXT_ID_WIDTH, HEIGHT);
			panel.add(textOptionId);

			JLabel lblOptionName = new JLabel("상품명");
			lblOptionName.setBounds(OPTION_NAME_XPOS, y, LABEL_WIDTH, HEIGHT);
			panel.add(lblOptionName);

			textOptionName = new JTextField();
			textOptionName.setBounds(TEXT_NAME_XPOS, y, TEXT_NAME_WIDTH, HEIGHT);
			panel.add(textOptionName);

			JLabel lblPrice = new JLabel("가격");
			lblPrice.setBounds(OPTION_PRICE_XPOS, y, LABEL_WIDTH, HEIGHT);
			panel.add(lblPrice);

			textPrice = new JTextField();
			textPrice.setBounds(TEXT_PRICE_XPOS, y, TEXT_PRICE_WIDTH, HEIGHT);
			panel.add(textPrice);

			JLabel lblStock = new JLabel("재고");
			lblStock.setBounds(OPTION_STOCK_XPOS, y, LABEL_WIDTH, HEIGHT);
			panel.add(lblStock);

			textStock = new JTextField();
			textStock.setBounds(TEST_STOCK_XPOS, y, TEXT_STOCK_WIDTH, HEIGHT);
			panel.add(textStock);

			// 데이터 input부분
			tempOptionIdFields.add(textOptionId);
			tempOptionNameFields.add(textOptionName);
			tempPriceFields.add(textPrice);
			tempStockFields.add(textStock);
			
			//추가버튼누르면 초기화
			optionIdFields.clear();
			optionNameFields.clear();
			priceFields.clear();
			stockFields.clear();

			panel.setPreferredSize(new Dimension(950, y + HEIGHT + 10));
			panel.repaint();
			scrollPane_1.revalidate();
			scrollPane_1.repaint();
		} else {
			JOptionPane.showMessageDialog(null, "옵션은 최대 5개 까지 쓸 수 있습니다");
		}

	}// end addOption

	private boolean isFieldNotEmpty(List<JTextField> fields) {
		for (JTextField field : fields) {
			if (field.getText().isBlank()) {
				return false;
			}
		}
		return true;
	}

	private void disableTextFields(List<JTextField> fields) {
		for (JTextField field : fields) {
			field.setEnabled(false);
		}
	}
}// end AppInsert
