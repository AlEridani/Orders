package side2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

public class PurchaseDAOImple implements PurchaseDAO {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "side3";
	private static final String PW = "123";

	private static final String TABLE_NAME = "PURCHASE";
	private static final String COL_ORDER_NUMBER = "ORDER_NUMBER";
	private static final String COL_ORDER_NUMBER_SQE = "SEQ1.NEXTVAL";
	private static final String COL_SESSION_ID = "MEMBER_ID";
	private static final String COL_APID = "AP_ID";
	private static final String COL_OPTION_ID = "OPTION_ID";
	private static final String COL_AP_NAME = "AP_NAME";
	private static final String COL_AP_MFR = "AP_MFR";
	private static final String COL_ORDER_QUANTITY = "ORDER_QUANTITY";
	private static final String COL_ORDER_PRICE = "ORDER_PRICE";
	private static final String COL_DATE = "ORDER_DATE";

	// sql문 새로 파야됨
	private static final String PURCHASE_ORDER = "INSERT INTO " + TABLE_NAME + " (" + COL_ORDER_NUMBER + ", "
												+ COL_SESSION_ID + ", " + COL_APID + ")" + " VALUES (" + COL_ORDER_NUMBER_SQE + ", ?, ?)"
												+ " RETURNING " + COL_ORDER_NUMBER + " INTO ?";
	// 주문번호,멤버id,apid
	// a = APPLIANCE Table
	// p = PURCHASE Table
	private static final String PURCHASE_SELECT = "SELECT"

			+ ", a." + COL_AP_NAME + ", a." + COL_AP_MFR + ", p." + COL_DATE + ", p." + COL_ORDER_QUANTITY + ", p."
			+ COL_ORDER_PRICE + ", p." + COL_ORDER_NUMBER + " FROM APPLIANCE a join " + TABLE_NAME + " p" + " ON a."
			+ COL_APID + " = p." + COL_APID + " WHERE p." + COL_SESSION_ID + " = ?" + " ORDER BY " + COL_ORDER_NUMBER;

	private static PurchaseDAOImple instance = null;

	public static PurchaseDAOImple getInstance() {
		if (instance == null) {
			instance = new PurchaseDAOImple();
		}
		return instance;
	}

	private PurchaseDAOImple() {

	}

	@Override
	public int purchase(PurchaseDTO dto) {
	    int result = -1;
	    CallableStatement cstmt = null;
	    try {
	        DriverManager.registerDriver(new OracleDriver());
	        Connection conn = DriverManager.getConnection(URL, USER, PW);
	        cstmt = conn.prepareCall("{ call ins_purchase_return_ordnum(?, ?, ?) }");

	        cstmt.setString(1, dto.getMemberID());
	        cstmt.setInt(2, dto.getApID());
	        cstmt.registerOutParameter(3, java.sql.Types.INTEGER);

	        cstmt.executeUpdate();

	        result = cstmt.getInt(3); 

	        cstmt.close();
	        conn.close();
	        
	        return result; 
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (cstmt != null) {
	            try {
	                cstmt.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return result;
	}

	@Override
	public ArrayList<PurchaseDTO> purchaseRecord(String id) {
		System.out.println("구매기록 sql 문 실행" + PURCHASE_SELECT);
		ArrayList<PurchaseDTO> list = new ArrayList<>();
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(PURCHASE_SELECT);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PurchaseDTO dto = new PurchaseDTO();
				dto.setOrderNumber(rs.getInt(COL_ORDER_NUMBER));
				dto.setOrderDate(rs.getDate(COL_DATE));
				dto.setApID(rs.getInt(COL_APID));
				dto.setApName(rs.getString(COL_AP_NAME));
				dto.setApMfr(rs.getString(COL_AP_MFR));
				dto.setOrderQunatity(rs.getInt(COL_ORDER_QUANTITY));
				dto.setOrderPrice(rs.getLong(COL_ORDER_PRICE));
				dto.setMemberID(id);
				list.add(dto);

			}

			rs.close();
			pstmt.close();
			conn.close();
			System.out.println("구매기록 메서드 실행 끝");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}