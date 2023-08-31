package side1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class OrdersList {

	private JFrame frame;

	private JPanel panel;
	private int count = 0;
	private JButton btnNewButton;
	private JScrollPane scrollPane;

	public OrdersList() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 378, 582);// 위치,위치,가로,세로
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNewButton = new JButton("더보기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count++;
				ordersHistory(count);
				SwingUtilities.invokeLater(() ->
				scrollPane.getVerticalScrollBar().
				setValue(scrollPane.getVerticalScrollBar().getMaximum())
				);
			}
		});
		btnNewButton.setBounds(22, 437, 289, 23);
		frame.getContentPane().add(btnNewButton);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);

		panel.setBounds(20, 30, 359, 371);
		frame.getContentPane().add(panel);

		scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(2, 30, 359, 371);
		frame.getContentPane().add(scrollPane);

		JLabel lblNewLabel = new JLabel("주문 내역");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(12, 10, 161, 18);
		frame.getContentPane().add(lblNewLabel);

		ordersHistory(count);
	}

	public void show() {
		frame.setVisible(true);
	}

	public void ordersHistory(int clicked) {

		Session session = Session.getInstance();
		PurchaseDAO dao = PurchaseDAOImple.getInstance();
		ArrayList<PurchaseDTO> list = dao.purchaseRecord(session.getDto().getMemberID());

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		int separatorSpacing = 15;
		int size = list.size();

		int page = 3;
		int start = size - 1 - (page * clicked);// 49 46 43 40 size는 50
		int end = start - page;// 46 43 40

		JLabel[] lblName = new JLabel[size];
		JLabel[] lblOrderDate = new JLabel[size];
		JLabel[] lblQuantity = new JLabel[size];
		JLabel[] lblOrderNumber = new JLabel[size];
		for (int i = start; i > end; i--) {
			if (i < 0) {
				JLabel lblOerdersEnd = new JLabel("이전 주문 내역이 없습니다");
				lblOerdersEnd.setFont(new Font("굴림", Font.BOLD, 23));
				panel.add(lblOerdersEnd);
				btnNewButton.setEnabled(false);
				break;
			} // end if

			lblOrderNumber[i] = new JLabel("주문번호 : " + String.valueOf(list.get(i).getOrderNumber()));
			panel.add(lblOrderNumber[i]);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(list.get(i).getOrderDate());
			lblOrderDate[i] = new JLabel(date);
			panel.add(lblOrderDate[i]);

			lblName[i] = new JLabel(list.get(i).getApMfr() + " " + list.get(i).getApName());
			panel.add(lblName[i]);

			lblQuantity[i] = new JLabel(numberFormat(list.get(i).getOrderQunatity()) + "개 "
					+ numberFormat((long) list.get(i).getOrderPrice()) + " 원");

			panel.add(lblQuantity[i]);

			panel.add(Box.createVerticalStrut(separatorSpacing));
			JSeparator separator = new JSeparator();
			panel.add(separator);

			panel.add(Box.createVerticalStrut(separatorSpacing));

		} // end for

		panel.revalidate();
		panel.repaint();
	}// end orders

	public String numberFormat(long num) {
		String formattedPrice = NumberFormat.getNumberInstance().format(num);
		return formattedPrice;
	}

	public String numberFormat(int num) {
		String formattedPrice = NumberFormat.getNumberInstance().format(num);
		return formattedPrice;
	}

	
	public void addFrameCloseListener(WindowListener listener) {
		frame.addWindowListener(listener);
	}// end addFrameCloseListener
}// end OrderList
