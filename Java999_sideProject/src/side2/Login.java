package side2;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Login {

	private JFrame frame;
	private JTextField textID;
	private JPasswordField textPw;
	private MemberDAO dao;
	private JLabel labelId;
	public static String id;

	public Login() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 382, 300);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("로그인");
		lblNewLabel.setBounds(153, 10, 121, 50);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(60, 83, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("비밀번호");
		lblNewLabel_2.setBounds(60, 142, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);

		textID = new JTextField();
		textID.setBounds(144, 80, 116, 21);
		frame.getContentPane().add(textID);
		textID.setColumns(10);

		JButton btnNewButton = new JButton("로그인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();

			}
		});
		btnNewButton.setBounds(56, 206, 97, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("회원가입");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup sign = new Signup();
				sign.show();
			}
		});
		btnNewButton_1.setBounds(188, 206, 97, 23);
		frame.getContentPane().add(btnNewButton_1);

		textPw = new JPasswordField();
		textPw.setBounds(144, 139, 116, 21);
		frame.getContentPane().add(textPw);

		labelId = new JLabel(" ");
		labelId.setForeground(Color.RED);
		labelId.setBounds(131, 170, 223, 29);
		frame.getContentPane().add(labelId);
		labelId.setVisible(false);

	}// end initialize

	public void show() {
		frame.setVisible(true);
	}

	public boolean login() {
		id = textID.getText();
		char[] pw = textPw.getPassword();
		dao = MemberDAOImple.getInstance();

		MemberDTO dto = dao.currentUserInfo(id);
		if (dto == null) {// 해당 ID가 DB에 없음
			labelId.setText("해당 ID가 존재하지 않습니다.");
			labelId.setVisible(true);
			return false;
		}

		char[] pw2 = dao.currentUserInfo(id).getPw();

		if (id.isBlank() || pwNull(pw)) {// 아이디 비밀번호 빈칸
			labelId.setText("ID와 비밀번호를 입력해주세요");
			labelId.setVisible(true);

		} else if (Arrays.equals(pw, pw2)) {
			System.out.println("로그인 성공");
			Session session = Session.getInstance();
			session.setDto(dto);
			JOptionPane.showMessageDialog(null, "로그인 성공");
			frame.dispose();
			return true;
		} else {// 비밀번호 틀림
			labelId.setText("비밀번호를 다시 확인해주세요");
			textPw.setText("");
			labelId.setVisible(true);
		}

		return false;
	}// end login

	public boolean pwNull(char[] ch) {
		if (ch == null || ch.length == 0) {
			return true;
		}
		for (char c : ch) {
			if (!Character.isWhitespace(c)) {// 공백문자 찾는 기능
				return false;
			}
		}
		return true;
	}// end pwNull

	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}// end addFrameCloseListener
}// end login