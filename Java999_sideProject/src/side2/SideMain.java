package side2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class SideMain {

	private JFrame frame;
	private JTable inven;
	private JButton btnMyInfo;
	private static Session session;
	static String grade;

	private JButton btnAdmin;
	private JLabel lblId;
	private JButton btnLogout;
	private JButton btnSignup;
	private JButton btnNewButton;
	private ArrayList<ApplianceDTO> appList;
	private DefaultTableModel tableModel;
	private String clickedID;
	private boolean userInfoIsOpen = false;
	private boolean loginIsOpen = false;
	private boolean signupIsOpen = false;

	private JButton btnAppInsert;
	private JTextField textSerch;
	private static SideMain instance = null;

	public static SideMain getInstance() {
		if (instance == null) {
			instance = new SideMain();
		}
		return instance;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		session = session.getInstance();
		session.setDto("비회원", "none");
		EventQueue.invokeLater(() -> {

			try {
				SideMain window = new SideMain();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	public SideMain() {
		initialize();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 909, 540);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNewButton = new JButton("로그인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openLogin();

			}
		});
		btnNewButton.setBounds(640, 38, 97, 23);
		frame.getContentPane().add(btnNewButton);

		btnSignup = new JButton("회원가입");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signup sign = new Signup();
				sign.addFrameCloseListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						signupIsOpen = false;

					}
				});
				if (!signupIsOpen) {
					sign.show();
					signupIsOpen = true;
				}

			}
		});
		btnSignup.setBounds(781, 38, 97, 23);
		frame.getContentPane().add(btnSignup);

		appTableOutput();
		inven = new JTable(tableModel);

		inven.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = inven.getSelectedRow();
				int idColumnIndex = inven.getColumnModel().getColumnIndex("번호");
				clickedID = inven.getModel().getValueAt(selectedRow, idColumnIndex).toString();
				System.out.println("클릭 : " + clickedID);

				int count = e.getClickCount();// 클릭횟수 기록 시간지나면 초기화 되는듯

				if (count == 2 && !isLoggedIn()) {
					JOptionPane.showMessageDialog(null, "로그인이 필요합니다");
				} else if (count == 2 && session.getGrade().equals("ADMIN")) {
					JOptionPane.showMessageDialog(null, "관리자 계정을 구매 기능을 사용할 수 없습니다");
				} else if (count == 2 && isLoggedIn()) {
					showProductForPurchase();
				}

			}
		});
		inven.setBackground(Color.LIGHT_GRAY);
		inven.setBounds(1, 38, 570, 405);
		frame.getContentPane().add(inven);

		JScrollPane scrollPane = new JScrollPane(inven);
		scrollPane.setBounds(50, 85, 790, 318);
		frame.getContentPane().add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		btnMyInfo = new JButton("내 정보");
		btnMyInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfo userInfo = new UserInfo();
				userInfo.addFrameCloseListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						userInfoIsOpen = false;
					}
				});
				userInfo.setMainCloseListener(() -> {
					MemberDAO dao = MemberDAOImple.getInstance();
					MemberDTO newDto = dao.currentUserInfo(session.getDto().getMemberID());
					if (newDto != null) {// 회원 수정일때
						session.setDto(newDto);
					} else {// 회원 탈퇴일때
						setUIVisibilByRole();
					}
				});
				if (!userInfoIsOpen) {
					userInfo.show();
					userInfoIsOpen = true;
				}

			}
		});
		btnMyInfo.setBounds(781, 38, 97, 23);
		frame.getContentPane().add(btnMyInfo);

		btnAdmin = new JButton("유저 관리");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberList memberList = new MemberList();
				memberList.addFrameCloseListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						MemberDAO dao = MemberDAOImple.getInstance();
						session.setDto(dao.currentUserInfo(session.getDto().getMemberID()));
						setUIVisibilByRole();
					}
				});
				memberList.show();
			}
		});
		btnAdmin.setBounds(185, 413, 113, 60);
		frame.getContentPane().add(btnAdmin);

		lblId = new JLabel("아이디 들어가는 자리");
		lblId.setHorizontalAlignment(JLabel.RIGHT);
		lblId.setBounds(687, 13, 168, 15);
		frame.getContentPane().add(lblId);

		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userLogout();
			}
		});
		btnLogout.setBounds(640, 38, 97, 23);
		frame.getContentPane().add(btnLogout);

		btnAppInsert = new JButton("물품관리");
		btnAppInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppManage appManage = new AppManage();
				appManage.addFrameCloseListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						appTableRefresh();
					}
				});
				appManage.show();

			}
		});
		btnAppInsert.setBounds(60, 413, 113, 60);
		frame.getContentPane().add(btnAppInsert);

		textSerch = new JTextField();
		textSerch.setBounds(283, 39, 162, 21);
		frame.getContentPane().add(textSerch);
		textSerch.setColumns(10);

		JButton btnNewButton_1 = new JButton("검색");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appSerch();

			}
		});
		btnNewButton_1.setBounds(493, 38, 97, 23);
		frame.getContentPane().add(btnNewButton_1);

		JLabel lblNewLabel = new JLabel("전자제품");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				appTableRefresh();
			}
		});
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 23));
		lblNewLabel.setBounds(50, 13, 199, 62);
		frame.getContentPane().add(lblNewLabel);
		btnAdmin.setVisible(false);

		setUIVisibilByRole();

	}

	/**
	 * 로그인 상태를 확인하는 메서드 세션의 등급을 확인해서 멤버 등급이 비회원인 none이면 false 아니면 true
	 * 
	 */
	public boolean isLoggedIn() {

		if (session.getGrade().equals("none")) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * ui를 멤버 등급별로 쓸수 있는것을 나눠둠
	 */
	public void setUIVisibilByRole() {
		System.out.println("UI 새로고침");
		if ("none".equals(session.getGrade())) {// 비회원
			btnMyInfo.setVisible(false);
			btnAdmin.setVisible(false);
			btnSignup.setVisible(true);
			btnNewButton.setVisible(true);
			btnAppInsert.setVisible(true);// 변경후 다시 false로 돌려둬야됨
			lblId.setVisible(false);
			btnLogout.setVisible(false);
		} else if (session.getGrade().equals("USER")) {// 회원
			lblId.setText(session.getDto().getMemberID() + "님");
			lblId.setVisible(true);
			btnMyInfo.setVisible(true);
			btnSignup.setVisible(false);
			btnLogout.setVisible(true);
			btnAdmin.setVisible(false);
			btnNewButton.setVisible(false);
			btnAppInsert.setVisible(false);
		} else if (session.getGrade().equals("ADMIN")) {// 관리자
			btnAdmin.setVisible(true);
			lblId.setText("관리자    " + session.getDto().getMemberID() + "님");
			lblId.setVisible(true);
			btnMyInfo.setVisible(true);
			btnSignup.setVisible(false);
			btnLogout.setVisible(true);
			btnNewButton.setVisible(false);
			btnAppInsert.setVisible(true);
		}
		System.out.println(session.getGrade());
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * 로그인창을 열고 로그인창이 닫히면 메인클래스의 테이블을 새로고침 하고 로그인 정보를 가져옴
	 */
	public void openLogin() {
		if (!loginIsOpen) {
			Login login = new Login();
			login.addFrameCloseListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					session = Session.getInstance();
					setUIVisibilByRole();
					loginIsOpen = false;
				}
			});
			login.show();
			loginIsOpen = true;
		}
	}

	/**
	 * 제품의 제고가 있으면 구매창을 띄우는 기능 창이 닫히면 메인 테이블에 변한 ui상태(재고)를 새로고침
	 */
	public void showProductForPurchase() {
		session = Session.getInstance();
		ApplianceDAO dao = ApplianceDAOImple.getInstance();
		ApplianceDTO dto = dao.appInfo(clickedID);
		if(dto == null) {
			System.out.println("null값 확인");
		}
		
		Products product = new Products(session, dto);
		product.addFrameCloseListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				session = Session.getInstance();
				appTableRefresh();
			}
		});
		product.show();
	}



	

	// 전자제품 테이블 첫 출력
	public void appTableOutput() {
		ApplianceDAO dao = ApplianceDAOImple.getInstance();
		appList = dao.tableSelect(); // 데이터 조회

		table();

	}

	// 전자제품 테이블 새로고침
	public void appTableRefresh() {
		DefaultTableModel model = (DefaultTableModel) inven.getModel();
		model.setNumRows(0);
		appTableOutput();
		inven.setModel(tableModel);

	}// end refresh

	// 텍스트필드의 문자여을 가져와서 출력
	public void appSerch() {
		ApplianceDAO dao = ApplianceDAOImple.getInstance();
		appList = dao.serch("%" + textSerch.getText() + "%");
		table();

		DefaultTableModel model = (DefaultTableModel) inven.getModel();
		model.setNumRows(0);
		inven.setModel(tableModel);
	}

	// 테이블만 보여주는 기능
	public void table() {

		int size = appList.size();
		String[] header = { "번호","상품 명", "제조사", "가격" };
		Object[][] data = new Object[size][header.length];
		for (int i = 0; i < size; i++) {
			data[i][0] = appList.get(i).getApID();
			data[i][1] = appList.get(i).getApName();
			data[i][2] = appList.get(i).getApMfr();
			data[i][3] = appList.get(i).getMainPrice();
		}
		tableModel = new DefaultTableModel(data, header) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}// end table

	public String numberFormat(int num) {
		String formattedPrice = NumberFormat.getNumberInstance().format(num);
		return formattedPrice;
	}

	// 로그아웃 ui 새로 고침
	public void userLogout() {
		session.setDto("비회원", "none");
		session.getDto().setPw(null);
		session.getDto().setName(null);
		session.getDto().setEmail(null);
		session.getDto().setPhone(null);
		setUIVisibilByRole();
		JOptionPane.showMessageDialog(null, "로그아웃 완료");
	}

}// end main