package side2;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppUpdate {

	private JFrame frame;
	private JPanel panel;
	private JTextField textApName;
	private JTextField textApMfr;
	private JTextArea textAppInfo;

	private ArrayList<OptionDTO> list;
	private OptionDTO dto;
	private OptionDAO dao;
	private JTextField textOptionId;
	private JTextField textOptionName;
	private JTextField textprice;
	private JTextField textStock;
	private JComboBox<OptionDTO> comboBox;

	private int Y = 333;

	public AppUpdate(ApplianceDTO dto) {
		initialize(dto);
	}

	private void initialize(ApplianceDTO dto) {
		dao = OptionDAOImple.getInstance();
		frame = new JFrame();
		frame.setBounds(100, 100, 951, 531);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("상품이름");
		lblNewLabel_1.setBounds(51, 30, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("제조사");
		lblNewLabel_3.setBounds(51, 67, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);

		textApName = new JTextField(dto.getApName());
		textApName.setBounds(120, 27, 750, 21);
		textApName.setColumns(10);
		frame.getContentPane().add(textApName);

		textApMfr = new JTextField(dto.getApMfr());
		textApMfr.setBounds(120, 64, 750, 21);
		textApMfr.setColumns(10);
		frame.getContentPane().add(textApMfr);

		textAppInfo = new JTextArea();
		textAppInfo.setText(dto.getApInfo());
		JScrollPane scrollPane = new JScrollPane(textAppInfo);
		scrollPane.setBounds(117, 102, 750, 120);
		frame.getContentPane().add(scrollPane);

		JLabel lblNewLabel_2 = new JLabel("상품설명");
		lblNewLabel_2.setBounds(51, 107, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("상품코드");
		lblNewLabel.setBounds(20, Y, 60, 20);
		frame.getContentPane().add(lblNewLabel);

		textOptionId = new JTextField();
		textOptionId.setBounds(90, Y, 160, 20);
		frame.getContentPane().add(textOptionId);
		textOptionId.setColumns(10);
		textOptionId.setEnabled(false);

		JLabel lblNewLabel_4 = new JLabel("상품명");
		lblNewLabel_4.setBounds(280, Y, 60, 20);
		frame.getContentPane().add(lblNewLabel_4);

		textOptionName = new JTextField();
		textOptionName.setBounds(330, Y, 200, 20);
		frame.getContentPane().add(textOptionName);
		textOptionName.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("가격");
		lblNewLabel_5.setBounds(560, Y, 60, 20);
		frame.getContentPane().add(lblNewLabel_5);

		textprice = new JTextField();
		textprice.setBounds(600, Y, 120, 20);
		frame.getContentPane().add(textprice);
		textprice.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("재고");
		lblNewLabel_6.setBounds(730, Y, 100, 20);
		frame.getContentPane().add(lblNewLabel_6);

		textStock = new JTextField();
		textStock.setBounds(770, Y, 116, 20);
		frame.getContentPane().add(textStock);
		textStock.setColumns(10);

		list = dao.serchByApId(dto.getApID());
		comboBox = new JComboBox<OptionDTO>();
		for (int i = 0; i < list.size(); i++) {
			comboBox.addItem(list.get(i));
		}
		OptionDTO selectedDto = (OptionDTO) comboBox.getSelectedItem();
		setTextField(selectedDto);

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					OptionDTO selectedDto = (OptionDTO) comboBox.getSelectedItem();

					if (selectedDto != null) {
						setTextField(selectedDto);

					}
				}
			}
		});
		comboBox.setBounds(84, 268, 326, 23);
		frame.getContentPane().add(comboBox);

		JButton btnNewButton = new JButton("수정");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (OptionUpdate()) {
					appUpdate(dto.getApID());
				}
			}
		});
		btnNewButton.setBounds(662, 439, 97, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(792, 439, 97, 23);
		frame.getContentPane().add(btnNewButton_1);

	}

	public void show() {
		frame.setVisible(true);
	}

	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}// end addFrameCloseListener

	public void setTextField(OptionDTO selectedDto) {
		textOptionId.setText(selectedDto.getOptionId());
		textOptionName.setText(selectedDto.getApName());
		textprice.setText(String.valueOf(selectedDto.getPrice()));
		textStock.setText(String.valueOf(selectedDto.getStock()));
	}

	public boolean OptionUpdate() {
		String optionId = textOptionId.getText();
		String OptionName = textOptionName.getText();
		int price = stringToInteger(textprice.getText());
		int stock = stringToInteger(textStock.getText());

		if (OptionName.isBlank()) {
			JOptionPane.showMessageDialog(null, "제품명을 입력해주세요");
			return false;
		} else if (price == -1 || stock == -1) {
			JOptionPane.showMessageDialog(null, "가격과 재고는 숫자만 입력할 수 있습니다");
			return false;
		} else {
			OptionDTO dto = new OptionDTO(optionId, OptionName, price, stock);
			int result = dao.update(dto);
			if (result == -1) {
				JOptionPane.showMessageDialog(null, "수정 실패");
				return false;
			} else {
				JOptionPane.showMessageDialog(null, "옵션 수정 성공");
				return true;

			}

		}
	}

	public void appUpdate(int apId) {
		String apName = textApName.getText();
		String apMfr = textApMfr.getText();
		String apInfo = textAppInfo.getText();

		ApplianceDTO dto = new ApplianceDTO(apId, apName, apMfr, apInfo);
		ApplianceDAO dao = ApplianceDAOImple.getInstance();
		int result = dao.appUpdate(dto);
		if (result == -1) {
			JOptionPane.showMessageDialog(null, "수정 실패");
		} else {
			JOptionPane.showMessageDialog(null, "수정 성공");
			
		}

	}

	public int stringToInteger(String str) {
		System.out.println("돌려줄 리턴값 확인" + str);
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

	public void refreshComboBox(int apid) {
		comboBox.removeAll();
		list = dao.serchByApId(apid);
		comboBox = new JComboBox<OptionDTO>();
		for (int i = 0; i < list.size(); i++) {
			comboBox.addItem(list.get(i));
		}
		OptionDTO selectedDto = (OptionDTO) comboBox.getSelectedItem();
		setTextField(selectedDto);
	}
}
