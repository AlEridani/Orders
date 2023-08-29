package side2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Map;

import oracle.jdbc.driver.OracleDriver;

public class OrderDetailDAOImple implements OrderDetailDAO {

	private static OrderDetailDAOImple instance = null;

	private OrderDetailDAOImple() {
	}

	public static OrderDetailDAOImple getInstance() {
		if (instance == null) {
			instance = new OrderDetailDAOImple();
		}
		return instance;
	}

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "side3";
	private static final String PW = "123";
	private static final String TABLE_NAME = "ORDER_DETAIL";
	private static final String COL_SEQ2 = "SEQ2.NEXTVAL";
	private static final String COL_DETAIL_ID = "DETAIL_ID";
	private static final String COL_ORDER_NUMBER = "ORDER_NUMBER";
	private static final String COL_OPTION_ID = "OPTION_ID";
	private static final String COL_QUANTITY = "ORDER_QUANTITY";
	private static final String COL_PRICE = "PRICE";

	private static final String detailInsert = "INSERT INTO " + TABLE_NAME + " VALUES( " + COL_SEQ2 + ", ?, ?, ?, ?)";

	@Override
	public int orderInsert(OrderDetailDTO dto) {
		int result = -1;
		try {
			// 주문번호,옵션코드,주문수량,옵션가격
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(detailInsert);

			for (Map.Entry<OptionDTO, Integer> entry : dto.getOrderOptions().entrySet()) {
				OptionDTO option = entry.getKey();
				Integer quantity = entry.getValue();
				pstmt.setInt(1, dto.getOrderNumber());
				pstmt.setString(2, dto.getOptionId());
				pstmt.setInt(3, dto.getQuantity());
				pstmt.setInt(4, dto.getPrice());

				result = pstmt.executeUpdate();
				pstmt.close();
			}
			conn.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<OrderDetailDTO> orderSelect() {
		// TODO Auto-generated method stub
		return null;
	}

}
