package side2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class MemberList {

	private JFrame frame;
	private JTextField textSerch;
	private JTable table;
	private DefaultTableModel tableModel;
	private MemberDAO dao;
	private ArrayList<MemberDTO> list;
	private String clickedID;
	private boolean selectOrSerch = true;

	public MemberList() {
		initialize();

	}

	private void initialize() {
		dao = MemberDAOImple.getInstance();
		frame = new JFrame();
		frame.setBounds(100, 100, 598, 554);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("회원 아이디 검색");
		lblNewLabel.setBounds(196, 36, 187, 15);
		frame.getContentPane().add(lblNewLabel);

		textSerch = new JTextField();
		textSerch.setBounds(315, 33, 116, 21);
		frame.getContentPane().add(textSerch);
		textSerch.setColumns(10);

		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				int idColumnIndex = table.getColumnModel().getColumnIndex("ID");
				clickedID = table.getModel().getValueAt(selectedRow, idColumnIndex).toString();
				System.out.println("클릭인덱스: " + clickedID);
			}
		});
		frame.getContentPane().add(table);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 83, 436, 406);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scrollPane);
		frame.setVisible(true);

		JButton btnUserDelete = new JButton("멤버삭제");
		btnUserDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "해당 회원을 삭제하겠습니까?", "멤버삭제 확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {

					userDelete();
					if (selectOrSerch) {
						reloadTable();
					} else {
						memberSerch();
					}
				}
			}
		});
		btnUserDelete.setBounds(460, 228, 110, 51);
		frame.getContentPane().add(btnUserDelete);

		JButton btnChangeAdmin = new JButton("관리자 등록");
		btnChangeAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (clickedID != null) {
					int result = JOptionPane.showConfirmDialog(frame, "해당 회원을 관리자로 등록하겠습니까?", "관리자 등록",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "관리자 등록이 되었습니다");
						memderUserToAdmin();
						if (selectOrSerch) {
							reloadTable();
						} else {
							memberSerch();
						}
					}
				} else {
					System.out.println("클릭안됨");
				}
			}

		});
		btnChangeAdmin.setBounds(460, 338, 110, 51);
		frame.getContentPane().add(btnChangeAdmin);

		JButton btnUserDelete_1 = new JButton("멤버검색");
		btnUserDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberSerch();

			}
		});
		btnUserDelete_1.setBounds(460, 18, 110, 51);
		frame.getContentPane().add(btnUserDelete_1);

		JButton btnUserDelete_2 = new JButton("전체멤버");
		btnUserDelete_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberSelect();

			}
		});
		btnUserDelete_2.setBounds(460, 118, 110, 51);
		frame.getContentPane().add(btnUserDelete_2);

		JButton btnChangeUser = new JButton("관리자 삭제");
		btnChangeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				int idColumnIndex = table.getColumnModel().getColumnIndex("회원등급");
				String clickedGrade = table.getModel().getValueAt(selectedRow, idColumnIndex).toString();
				if (!isOnlyAdmin()) {
					if (clickedGrade.equals("ADMIN")) {
						int result = JOptionPane.showConfirmDialog(frame, "해당 사용자를 일반유저로 변경하겠습니까?", "관리자 권한 제거",
								JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null, "일반유저로 전환되었습니다");
							memderAdminToUser();
							if (selectOrSerch) {
								reloadTable();
							} else {
								memberSerch();
							}
						} // end (result == JOptionPane.YES_OPTION)

					} // end clickedGrade.equals("ADMIN")
						// end !isOnlyAdmin()// 관리자 계정이 하나임
				} else {
					JOptionPane.showMessageDialog(null, "관리자 계정이 하나 남아서 권한을 삭제할 수 없습니다");
				}
			}// end actionPerformed
		});
		btnChangeUser.setBounds(460, 438, 110, 51);
		frame.getContentPane().add(btnChangeUser);

		memberSelect();

	}

	public void memberSelect() {
		selectOrSerch = true;
		list = dao.select();
		table();

	}

	public void show() {
		frame.setVisible(true);
	}

	public void memberSerch() {
		selectOrSerch = false;
		String serchId = "%" + textSerch.getText() + "%";
		list = dao.serch(serchId);
		table();

	}

	private void reloadTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		memberSelect();

	}

	public void memderUserToAdmin() {
		MemberDTO dto = dao.currentUserInfo(clickedID);
		dao.memberGrade(dto);
	}

	public void memderAdminToUser() {
	
		MemberDTO dto = dao.currentUserInfo(clickedID);
		dao.memberChangeAdminToUser(dto);
		
	}

	public void userDelete() {
		MemberDTO dto = dao.currentUserInfo(clickedID);
		if(dto == null) {
			JOptionPane.showMessageDialog(null, "유저가 선택되지 않았습니다");
		}else if (!dto.getMemberGrade().equals("ADMIN")) {
			dao.delete(dto.getMemberID());
		} else {
			JOptionPane.showMessageDialog(null, "관리자 계정은 삭제할 수 없습니다");
		}
	}

	@SuppressWarnings("serial")
	public void table() {
		int size = list.size();
		String[] header = { "ID", "비밀번호", "이름", "EMAIL", "PHONE", "회원등급" };
		Object[][] data = new Object[size][header.length];
		for (int i = 0; i < size; i++) {
			data[i][0] = list.get(i).getMemberID();
			data[i][1] = list.get(i).getPw();
			data[i][2] = list.get(i).getName();
			data[i][3] = list.get(i).getEmail();
			data[i][4] = list.get(i).getPhone();
			data[i][5] = list.get(i).getMemberGrade();

		}

		DefaultTableModel model = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}

	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}// end addFrameCloseListener

	public boolean isOnlyAdmin() {
		list = dao.select();
		int count = 0;
		for (MemberDTO x : list) {
			if (x.getMemberGrade().equals("ADMIN")) {
				count++;
			}
		}
		if (count <= 1) {
			return true;
		} else {
			return false;
		}
	}

}