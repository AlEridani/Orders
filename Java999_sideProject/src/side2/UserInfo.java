package side2;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.WindowConstants;

import side2.UserUpdate.CloseListener;

public class UserInfo {

	private JFrame frame;
	static Session session;
	private JLabel lblOrderDate;
	private CloseListener mainCloseListener;
	private JLabel lblNewLabel_6;
	private boolean orderlistIsOpen = false;
	private JLabel lblApName;
	private int y = 520;

	public UserInfo() {
		initialize();
	}

	private void initialize() {
		session = Session.getInstance();

		frame = new JFrame();
		frame.setBounds(100, 100, 362, 990);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lableName = new JLabel(session.getDto().getName());
		lableName.setFont(new Font("굴림", Font.PLAIN, 15));
		lableName.setBounds(105, 33, 195, 93);
		frame.getContentPane().add(lableName);

		JLabel labelphone = new JLabel(session.getDto().getPhone());
		labelphone.setFont(new Font("굴림", Font.PLAIN, 15));
		labelphone.setBounds(105, 183, 171, 50);
		frame.getContentPane().add(labelphone);

		JLabel labelEmail = new JLabel(session.getDto().getEmail());
		labelEmail.setFont(new Font("굴림", Font.PLAIN, 15));
		labelEmail.setBounds(105, 121, 171, 36);
		frame.getContentPane().add(labelEmail);

		JButton btnNewButton = new JButton("개인정보 변경");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserUpdate update = new UserUpdate();
				update.setCloseListener(() -> {
					if (mainCloseListener != null) {
						mainCloseListener.onClose();
					}
				});
				update.show();
				frame.dispose();

			}
		});
		btnNewButton.setBounds(193, 281, 124, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 72, 57, 20);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("이메일");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 132, 57, 20);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("연락처");
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(12, 201, 57, 20);
		frame.getContentPane().add(lblNewLabel_2);

		JSeparator separator = new JSeparator();
		separator.setBackground(SystemColor.desktop);
		separator.setBounds(12, 376, 322, 15);
		frame.getContentPane().add(separator);

		JLabel lblNewLabel_5 = new JLabel();
		lblNewLabel_5.setText("최근주문 기록");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_5.setBounds(22, 401, 147, 23);
		frame.getContentPane().add(lblNewLabel_5);

		lblOrderDate = new JLabel();
	
		lblOrderDate.setFont(new Font("굴림", Font.BOLD, 12));
		lblOrderDate.setBounds(22, 461, 136, 23);
		frame.getContentPane().add(lblOrderDate);

		JButton btnNewButton_1 = new JButton("주문내역 전체보기 >");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrdersList orderlist = new OrdersList();
				orderlist.addFrameCloseListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						orderlistIsOpen = false;
					}
				});
				if (!orderlistIsOpen) {
					orderlistIsOpen = true;
					orderlist.show();
				}
			}
		});
		btnNewButton_1.setBounds(0, 928, 346, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		lblNewLabel_6 = new JLabel("주문번호");
		lblNewLabel_6.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(26, 434, 250, 23);
		frame.getContentPane().add(lblNewLabel_6);
		
	
		
		lblApName = new JLabel();
		lblApName.setFont(new Font("굴림", Font.PLAIN, 14));
		lblApName.setBounds(22, 502, 254, 23);
		frame.getContentPane().add(lblApName);
		
		

	}

	public void show() {
		lastOrderHistory(session);
		frame.setVisible(true);
	}

	// 최근 구매 기록
	public void lastOrderHistory(Session session) {
		OrderDetailDAO dao = OrderDetailDAOImple.getInstance();
		ArrayList<OrderDetailDTO> list = dao.orderSelect(session.getDto().getMemberID());
		if (!list.isEmpty()) {
			int size = list.size();
			OrderDetailDTO dto = list.get(size - 1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(dto.getOrderDate());
			lblOrderDate.setText(date);
			lblApName.setText(dto.getApName());
			lblNewLabel_6.setText(String.valueOf("주문번호 " + dto.getOrderNumber()));
			list.clear();
			list = dao.lastOrder(session.getDto().getMemberID());
			
			
			for(int i =0; i < list.size();i++) {//y값만 조지기
				JLabel lblNewLabel_3 = new JLabel(list.get(i).getOptionName());
				lblNewLabel_3.setBounds(22, y, 274, 36);
				frame.getContentPane().add(lblNewLabel_3);
				
				String quantityAndPrice = String.valueOf(list.get(i).getQuantity())
										 + "개/" + numberFormat(list.get(i).getPrice()) +"원";
				JLabel lblNewLabel_4 = new JLabel(quantityAndPrice);
				lblNewLabel_4.setBounds(22, y+30, 178, 15);
				frame.getContentPane().add(lblNewLabel_4);
				y+=60;
			}
			

		} else {
			lblNewLabel_6.setText("이전 주문 내역이 없습니다");
			lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 18));
			lblOrderDate.setVisible(false);
			
		}
	}// end showLabelRecord

	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}// end addFrameCloseListener

	public void setMainCloseListener(CloseListener listener) {
		this.mainCloseListener = listener;
	}// end mainCloseListener
	
	public String numberFormat(int num) {
		String formatPrice = NumberFormat.getNumberInstance().format(num);
		return formatPrice;
	}
}