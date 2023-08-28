package side2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class UserUpdate {

	private JFrame frame;
	private JTextField textEmail;
	private JTextField textPhone;
	private JPasswordField textPw;
	private MemberDAO dao;
	private Session session;
	private JTextField textName;
	private CloseListener closeListener;
	private SideMain mainUI;

	private static UserUpdate instance = null;

	public UserUpdate() {
		initialize();
	}

	public static UserUpdate getInstance() {
		if (instance == null) {
			instance = new UserUpdate();
		}
		return instance;
	}

	public void init(SideMain mainUI) {
		this.mainUI = mainUI;
	}

	private void initialize() {
		session = Session.getInstance();

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 426);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// 커밋테스
		JLabel lblNewLabel = new JLabel("비밀번호");
		lblNewLabel.setBounds(36, 54, 57, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("이메일");
		lblNewLabel_1.setBounds(36, 159, 57, 15);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("연락처");
		lblNewLabel_2.setBounds(36, 212, 57, 15);
		frame.getContentPane().add(lblNewLabel_2);

		textEmail = new JTextField();
		textEmail.setBounds(105, 156, 247, 21);
		frame.getContentPane().add(textEmail);
		textEmail.setColumns(10);

		textPhone = new JTextField();
		textPhone.setBounds(105, 209, 247, 21);
		frame.getContentPane().add(textPhone);
		textPhone.setColumns(10);

		textPw = new JPasswordField();
		textPw.setBounds(105, 51, 247, 21);
		frame.getContentPane().add(textPw);

		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				UserInfo userInfo = new UserInfo();
				userInfo.show();
				frame.dispose();
			}
		});
		btnUpdate.setBounds(105, 254, 97, 23);
		frame.getContentPane().add(btnUpdate);

		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserInfo info = new UserInfo();
				info.show();
			}
		});
		btnNewButton_1.setBounds(255, 254, 97, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnNewButton = new JButton("회원탈퇴");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnNewButton.setBounds(308, 334, 97, 23);
		frame.getContentPane().add(btnNewButton);

		if (session.getGrade().equals("ADMIN")) {
			btnNewButton.setEnabled(false);
			btnNewButton.setVisible(false);
		}

		JLabel lblNewLabel_3 = new JLabel("이름");
		lblNewLabel_3.setBounds(36, 107, 57, 15);
		frame.getContentPane().add(lblNewLabel_3);

		textName = new JTextField();
		textName.setBounds(105, 104, 247, 21);
		frame.getContentPane().add(textName);
		textName.setColumns(10);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				notifyCloseListener();
			}
		});

	}

	public void show() {
		frame.setVisible(true);
	}

	public void update() {
		int result = -1;
		char[] pw = textPw.getPassword();
		String name = textName.getText();
		String email = textEmail.getText();
		String phone = textPhone.getText();

		dao = MemberDAOImple.getInstance();
		Signup sign = new Signup();

		if (sign.pwNull(pw)) {
			pw = session.getDto().getPw();
		}
		if (name.isBlank()) {
			name = session.getDto().getName();
		}
		if (email.isBlank()) {
			email = session.getDto().getEmail();
		}
		if (phone.isBlank()) {
			phone = session.getDto().getPhone();
		}
		MemberDTO dto = new MemberDTO(session.getDto().getMemberID(), pw, name, email, phone);
		result = dao.update(dto);
		if(result == 1) {
			JOptionPane.showMessageDialog(null, "수정 성공");
			dto.setMemberGrade(session.getGrade());
			session.setDto(dto);
			
		}else {
			JOptionPane.showMessageDialog(null, "수정에 실패했습니다");
		}

	}
	//회원탈퇴
	public void delete() {
		int result = -1;
		dao = MemberDAOImple.getInstance();
		System.out.println(session.getDto().getMemberID());
		String id = session.getDto().getMemberID();//회원탈퇴를 위한 id 가져오기
		int userDeleteResult = JOptionPane.showConfirmDialog(frame, "회원 탈퇴 하시겠습니까?", "회원 탈퇴",
				JOptionPane.YES_NO_OPTION);
		if (userDeleteResult == JOptionPane.YES_OPTION) {
			result = dao.delete(id);//삭제
			if (result == -1) {
				JOptionPane.showMessageDialog(null, "회원탈퇴 실패");
			} else {
				session.setDto("비회원", "none");
				System.out.println("멤버 등급 확인 : " + session.getGrade());
				mainUI = SideMain.getInstance();
				mainUI.setUIVisibilByRole();
				frame.dispose();
			}

		}

	}

	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}// end addFrameCloseListener

	public interface CloseListener {
		void onClose();
	}

	public void setCloseListener(CloseListener listener) {
		this.closeListener = listener;
	}

	private void notifyCloseListener() {
		if (closeListener != null) {
			closeListener.onClose();
		}
	}
}