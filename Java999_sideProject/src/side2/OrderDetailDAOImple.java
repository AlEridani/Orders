package side2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

	private static final String URL = "jdbc:oracle:thin:@172.16.3.3:1521:xe";
	private static final String USER = "side3";
	private static final String PW = "123";
	private static final String TABLE_NAME = "ORDER_DETAIL";
	private static final String COL_SEQ2 = "SEQ2.NEXTVAL";
	private static final String COL_DETAIL_ID = "DETAIL_ID";
	private static final String COL_ORDER_NUMBER = "ORDER_NUMBER";
	private static final String COL_OPTION_ID = "OPTION_ID";
	private static final String COL_QUANTITY = "ORDER_QUANTITY";
	private static final String COL_PRICE = "PRICE";
	private static final String COL_ORDER_DATE = "ORDER_DATE";
	private static final String COL_AP_ID = "AP_ID";
	private static final String COL_AP_NAME = "AP_NAME";
	private static final String COL_AP_MFR = "AP_MFR";
	private static final String COL_OPTION_NAME = "OPTION_NAME";
	
	private static final String detailInsert = "INSERT INTO " + TABLE_NAME + " VALUES(" + COL_SEQ2 + ", ?, ?, ?, ?)";

	private static final String ORDER_LIST = "SELECT "
			+ "P.ORDER_NUMBER, "
			+ "P.ORDER_DATE, "
			+ "A.AP_ID, "
			+ "A.AP_NAME, "
			+ "A.AP_MFR, "
			+ "O.OPTION_NAME, "
			+ "O.OPTION_ID, "
			+ "D.ORDER_QUANTITY, "
			+ "D.PRICE "
			+ "FROM "
			+ "PURCHASE P "
			+ "JOIN "
			+ "BUY_INFO A ON P.AP_ID = A.AP_ID "
			+ "JOIN "
			+ "ORDER_DETAIL D ON P.ORDER_NUMBER = D.ORDER_NUMBER "
			+ "JOIN "
			+ "APP_OPTION O ON D.OPTION_ID = O.OPTION_ID "
			+ "WHERE "
			+ "P.MEMBER_ID = ?";
	
	
	
	private static final String LAST_ORDER = "SELECT "
			+ "P.ORDER_NUMBER, "
			+ "P.ORDER_DATE, "
			+ "A.AP_ID, "
			+ "A.AP_NAME, "
			+ "A.AP_MFR, "
			+ "O.OPTION_NAME, "
			+ "O.OPTION_ID, "
			+ "D.ORDER_QUANTITY, "
			+ "D.PRICE "
			+ "FROM "
			+ "PURCHASE P "
			+ "JOIN "
			+ "BUY_INFO A ON P.AP_ID = A.AP_ID "
			+ "JOIN "
			+ "ORDER_DETAIL D ON P.ORDER_NUMBER = D.ORDER_NUMBER "
			+ "JOIN "
			+ "APP_OPTION O ON D.OPTION_ID = O.OPTION_ID "
			+ "WHERE "
			+ "P.MEMBER_ID = ? "
			+ "AND P.ORDER_NUMBER = (SELECT MAX(ORDER_NUMBER) FROM PURCHASE)";
	
	
	private static final String ORDER_LIST_BY_ORDER_NUMBER = "SELECT "
			+ "P.ORDER_NUMBER, "
			+ "P.ORDER_DATE, "
			+ "A.AP_ID, "
			+ "A.AP_NAME, "
			+ "A.AP_MFR, "
			+ "O.OPTION_NAME, "
			+ "O.OPTION_ID, "
			+ "D.ORDER_QUANTITY, "
			+ "D.PRICE "
			+ "FROM "
			+ "PURCHASE P "
			+ "JOIN "
			+ "BUY_INFO A ON P.AP_ID = A.AP_ID "
			+ "JOIN "
			+ "ORDER_DETAIL D ON P.ORDER_NUMBER = D.ORDER_NUMBER "
			+ "JOIN "
			+ "APP_OPTION O ON D.OPTION_ID = O.OPTION_ID "
			+ "WHERE "
			+ "P.MEMBER_ID = ? AND P.ORDER_NUMBER = ?";
			
			
			
			

	@Override
	public int orderInsert(OrderDetailDTO dto) {
		int result = -1;
		try {
			// 주문번호,옵션코드,주문수량,옵션가격
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(detailInsert);

			pstmt.setInt(1, dto.getOrderNumber());
			pstmt.setString(2, dto.getOptionId());
			pstmt.setInt(3, dto.getQuantity());
			pstmt.setLong(4, dto.getPrice());

			result = pstmt.executeUpdate();
			pstmt.close();

			conn.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<OrderDetailDTO> orderSelect(String id) {
		ArrayList<OrderDetailDTO> list = new ArrayList<OrderDetailDTO>();

		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(ORDER_LIST);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderDetailDTO dto = new OrderDetailDTO();
				
				dto.setOrderNumber(rs.getInt(COL_ORDER_NUMBER));
				dto.setOrderDate(rs.getDate(COL_ORDER_DATE));
				dto.setApId(rs.getInt(COL_AP_ID));
				dto.setApName(rs.getString(COL_AP_NAME));
				dto.setMfr(rs.getString(COL_AP_MFR));
				dto.setOptionName(rs.getString(COL_OPTION_NAME));
				dto.setOptionId(rs.getString(COL_OPTION_ID));
				dto.setQuantity(rs.getInt(COL_QUANTITY));
				dto.setPrice(rs.getInt(COL_PRICE));

				list.add(dto);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<OrderDetailDTO> lastOrder(String id) {
		ArrayList<OrderDetailDTO> list = new ArrayList<OrderDetailDTO>();

		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(LAST_ORDER);
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderDetailDTO dto = new OrderDetailDTO();
				
				dto.setOrderNumber(rs.getInt(COL_ORDER_NUMBER));
				dto.setOrderDate(rs.getDate(COL_ORDER_DATE));
				dto.setApId(rs.getInt(COL_AP_ID));
				dto.setApName(rs.getString(COL_AP_NAME));
				dto.setMfr(rs.getString(COL_AP_MFR));
				dto.setOptionName(rs.getString(COL_OPTION_NAME));
				dto.setOptionId(rs.getString(COL_OPTION_ID));
				dto.setQuantity(rs.getInt(COL_QUANTITY));
				dto.setPrice(rs.getInt(COL_PRICE));
				list.add(dto);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<OrderDetailDTO> optionList(String id, int ordernumber) {
		ArrayList<OrderDetailDTO> list = new ArrayList<OrderDetailDTO>();

		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(ORDER_LIST_BY_ORDER_NUMBER);
			pstmt.setString(1, id);
			pstmt.setInt(2, ordernumber);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderDetailDTO dto = new OrderDetailDTO();
				
				dto.setOrderNumber(rs.getInt(COL_ORDER_NUMBER));
				dto.setOrderDate(rs.getDate(COL_ORDER_DATE));
				dto.setApId(rs.getInt(COL_AP_ID));
				dto.setApName(rs.getString(COL_AP_NAME));
				dto.setMfr(rs.getString(COL_AP_MFR));
				dto.setOptionName(rs.getString(COL_OPTION_NAME));
				dto.setOptionId(rs.getString(COL_OPTION_ID));
				dto.setQuantity(rs.getInt(COL_QUANTITY));
				dto.setPrice(rs.getInt(COL_PRICE));
				list.add(dto);
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



}
