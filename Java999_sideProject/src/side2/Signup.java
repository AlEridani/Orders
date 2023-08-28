package side2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Signup {

	private JFrame frame;
	private JFrame frame2;
	private JTextField textId;
	private JTextField textName;
	private JTextField textEmail;
	private JTextField textPhone;
	private JPasswordField textPw;
	private MemberDTO dto;
	private MemberDAO dao;
	private JRadioButton rbtnSignAccept;
	private JRadioButton rbtnSignDecl;
	private JRadioButton rbtnAccept;
	private JRadioButton rbtnDecl;
	private JButton btnNewButton_1;
	private boolean buttonClickedCheck = false;
	private JLabel lblCheckedId;
	private JCheckBox chckbxNewCheckBox;

	public Signup() {
		initialize();
	}

	private void initialize() {// 약관동의 프레임
		frame = new JFrame();
		frame.setBounds(100, 100, 927, 768);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(false);

		JTextArea textArea = new JTextArea();
		textArea.setText("로그인 약관");
		textArea.setBounds(33, 65, 866, 232);
		frame.getContentPane().add(textArea);
		textArea.setEditable(false);

		rbtnSignAccept = new JRadioButton("동의");
		rbtnSignAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtnChecked();
			}
		});
		rbtnSignAccept.setBounds(672, 307, 77, 23);
		frame.getContentPane().add(rbtnSignAccept);

		rbtnSignDecl = new JRadioButton("거절");
		rbtnSignDecl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtnChecked();
			}
		});
		rbtnSignDecl.setBounds(753, 303, 150, 31);
		rbtnSignDecl.setSelected(true);
		frame.getContentPane().add(rbtnSignDecl);

		ButtonGroup signGroup = new ButtonGroup();
		signGroup.add(rbtnSignAccept);
		signGroup.add(rbtnSignDecl);

		JLabel lblNewLabel_6 = new JLabel("회원가입");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_6.setBounds(387, 10, 199, 67);
		frame.getContentPane().add(lblNewLabel_6);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setText("개인정보 동의");
		textArea_1.setBounds(33, 357, 866, 232);
		frame.getContentPane().add(textArea_1);
		textArea_1.setEditable(false);
		rbtnAccept = new JRadioButton("동의");
		rbtnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtnChecked();
			}
		});
		rbtnAccept.setBounds(672, 591, 61, 23);
		frame.getContentPane().add(rbtnAccept);

		rbtnDecl = new JRadioButton("거절");
		rbtnDecl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtnChecked();
				if(rbtnDecl.isSelected()) {
					chckbxNewCheckBox.setSelected(false);
				}
			}
		});
		rbtnDecl.setBounds(763, 591, 121, 23);
		rbtnDecl.setSelected(true);
		frame.getContentPane().add(rbtnDecl);

		ButtonGroup rbtnGroup = new ButtonGroup();
		rbtnGroup.add(rbtnAccept);
		rbtnGroup.add(rbtnDecl);

		btnNewButton_1 = new JButton("회원가입");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame2.setVisible(true);

			}
		});
		btnNewButton_1.setBounds(443, 659, 181, 48);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.setEnabled(false);

		JButton btnNewButton_1_1 = new JButton("취소");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		btnNewButton_1_1.setBounds(672, 659, 181, 48);
		frame.getContentPane().add(btnNewButton_1_1);

		chckbxNewCheckBox = new JCheckBox("모든 약관에 동의합니다");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbtnAccept.setSelected(true);
				rbtnSignAccept.setSelected(true);
				rbtnChecked();

			}
		});
		chckbxNewCheckBox.setBounds(43, 617, 258, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		signupFrame();
	}

	public void signupFrame() {// 회원가입 프레임
		frame2 = new JFrame();
		frame2.setVisible(false);
		frame2.setBounds(100, 100, 474, 464);
		frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame2.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setBounds(201, 20, 57, 15);
		frame2.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(43, 75, 57, 15);
		frame2.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setBounds(43, 136, 57, 15);
		frame2.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("이름");
		lblNewLabel_3.setBounds(43, 194, 57, 15);
		frame2.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("이메일");
		lblNewLabel_4.setBounds(43, 254, 57, 15);
		frame2.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("연락처");
		lblNewLabel_5.setBounds(43, 315, 57, 15);
		frame2.getContentPane().add(lblNewLabel_5);

		JButton btnNewButton = new JButton("등록");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (buttonClickedCheck) {
					sign();
				} else {
					JOptionPane.showMessageDialog(null, "아이디 중복검사를 해주세요");
				}

			}
		});
		btnNewButton.setBounds(43, 357, 146, 44);
		frame2.getContentPane().add(btnNewButton);

		JButton isIdAvailable = new JButton("중복 확인");
		isIdAvailable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idcheck();
			}
		});

		isIdAvailable.setBounds(336, 71, 99, 22);
		frame2.getContentPane().add(isIdAvailable);

		textId = new JTextField();
		textId.setBounds(122, 72, 198, 21);
		frame2.getContentPane().add(textId);
		textId.setColumns(10);

		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(122, 191, 198, 21);
		frame2.getContentPane().add(textName);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(122, 251, 198, 21);
		frame2.getContentPane().add(textEmail);

		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(122, 312, 198, 21);
		frame2.getContentPane().add(textPhone);

		textPw = new JPasswordField();
		textPw.setBounds(122, 133, 198, 21);
		frame2.getContentPane().add(textPw);

		JButton btnNewButton_2 = new JButton("취소");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
			}
		});
		btnNewButton_2.setBounds(263, 357, 146, 44);
		frame2.getContentPane().add(btnNewButton_2);

		lblCheckedId = new JLabel();
		lblCheckedId.setBounds(122, 103, 210, 20);
		frame2.getContentPane().add(lblCheckedId);
	}

	public void show() {
		frame.setVisible(true);
	}

	public void sign() {// 회원가입 메서드
		String id = textId.getText();
		char[] pw = textPw.getPassword();
		String name = textName.getText();
		String phone = textEmail.getText();
		String email = textPhone.getText();

		dao = MemberDAOImple.getInstance();
		System.out.println("아이디 확인" + id);
		System.out.println("비밀번호 확인" + pw);
		System.out.println("이름" + name);
		System.out.println("연락처 확인" + phone);
		System.out.println("이메일 확인" + email);
		dto = new MemberDTO(id, pw, name, phone, email);
		int result = dao.insert(dto);
		if (id.isBlank() || pwNull(pw) || name.isBlank() || phone.isBlank() || email.isBlank()) {
			System.out.println("값이 모두 채워지지 않았음");
			JOptionPane.showMessageDialog(null, "빈칸이 없이 모두 입력해 주세요");
		} else if (result == -1) {
			JOptionPane.showMessageDialog(null, "아이디가 중복입니다");
		} else {
			JOptionPane.showMessageDialog(null, "회원가입 성공");
			frame2.dispose();

			System.out.println(result);

		}

	}// end sign

	public boolean pwNull(char[] ch) {
		if (ch == null || ch.length == 0) {
			return true;
		}
		for (char c : ch) {
			if (!Character.isWhitespace(c)) {// 공백문자 찾기
				return false;
			}
		}
		return true;
	}

	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
		frame2.addWindowListener(listener);
	}// end addFrameCloseListenerForFrame

	public void rbtnChecked() {// 라디오 버튼 체크
		if (!rbtnSignAccept.isSelected() || !rbtnAccept.isSelected()) {
			btnNewButton_1.setEnabled(false);
		} else {
			btnNewButton_1.setEnabled(true);
		}
	}

	/**
	 * 아이디 중복검사 text박스의 아이디를 불러서 확인 비교후 사용가능하면 1리턴 사용불가능하면 -1 아이디가 빈칸이면 -2
	 * 
	 */
	public void idcheck() {
		int result = 1;
		String id = textId.getText();
		System.out.println("idcheck 확인 : " + id);
		dao = MemberDAOImple.getInstance();
		ArrayList<MemberDTO> list = dao.select();

		if (id.isBlank()) {//아이디 공백시 
			result = -2;
		} else {
			for (int i = 0; i < list.size(); i++) {
				System.out.println("DB의 아이디 순환 확인 :" + list.get(i).getMemberID());
				if (list.get(i).getMemberID().equals(id)) {// 아이디 순회해서 중복 있으면 false반환
					result = -1;
				}//end if
			}//end for
		}//end else
		if (result == 1) {// true일때 아이디 사용가능
			System.out.println("아이디 사용가능");
			buttonClickedCheck = true;
			lblCheckedId.setVisible(true);
			lblCheckedId.setText("사용 가능한 아이디 입니다");
			lblCheckedId.setFont(new Font("굴림", Font.BOLD, 15));
			lblCheckedId.setForeground(Color.BLACK);
		} else if (result == -1) {// 아이디 중복
			buttonClickedCheck = false;
			lblCheckedId.setVisible(true);
			lblCheckedId.setForeground(Color.RED);
			lblCheckedId.setFont(new Font("굴림", Font.BOLD, 15));
			lblCheckedId.setText("이미 사용중인 아이디 입니다");

		} else if (result == -2) {// 입력칸 공백
			buttonClickedCheck = false;
			lblCheckedId.setVisible(true);
			lblCheckedId.setForeground(Color.RED);
			lblCheckedId.setFont(new Font("굴림", Font.BOLD, 15));
			lblCheckedId.setText("아이디를 입력해주세요");
		}

	
	}
}