package side2;

public interface SqlQuery {
	
	String URL = "jdbc:oracle:thin:@172.16.3.3:1521:xe";
	String USER = "side3";
	String PW = "123";
	String ORDER_DETAIL_TABLE = "ORDER_DETAIL";
	String COL_SEQ2 = "SEQ2.NEXTVAL";
	String COL_DETAIL_ID = "DETAIL_ID";
	String COL_ORDER_NUMBER = "ORDER_NUMBER";
	String COL_OPTION_ID = "OPTION_ID";
	String COL_QUANTITY = "ORDER_QUANTITY";
	String COL_PRICE = "PRICE";
	String COL_ORDER_DATE = "ORDER_DATE";
	String COL_AP_ID = "AP_ID";
	String COL_AP_NAME = "AP_NAME";
	String COL_AP_MFR = "AP_MFR";
	String COL_OPTION_NAME = "OPTION_NAME";

	String TABLE_BUY_INFO = "BUY_INFO";
	String COL_ID = "AP_ID";
	String COL_AP_NUMBER = "AP_NUMBER";
	String COL_SEQ = "SEQ1.NEXTVAL";
	String COL_NAME = "AP_NAME";
	String COL_INFO = "AP_INFO";
	String COL_MFR = "AP_MFR";
	String COL_DELETED = "IS_DELETED";
	
	



	String appInsert = "INSERT INTO " + TABLE_BUY_INFO +
									  " VALUES ("
									  + COL_SEQ + ",?,?,?,0)";
	
	
	String appColId = "SELECT " + COL_ID + " FROM " + TABLE_BUY_INFO
									  + " WHERE " 
									  + COL_NAME + " = ? AND " 
									  + COL_MFR + " = ? AND "
									  + COL_INFO + " = ? ";
	

	String appSelect = "SELECT * FROM " + TABLE_BUY_INFO + 
									  " WHERE " + COL_DELETED +" = 0";
	
	
//	SELECT A.AP_NAME, A.AP_MFR, O.PRICE
//	FROM APPLIANCE A
//	JOIN APP_OPTION O ON A.AP_ID = O.AP_ID;
	String tableSelect = "SELECT A."+ COL_NAME + ", A."+ COL_ID + ", A."+ COL_MFR + ", O.PRICE"
										+ " FROM " + TABLE_BUY_INFO + " A"
										+ " JOIN APP_OPTION O ON A.AP_ID = O.AP_ID"
										+ " WHERE O.POSITION = 1 AND " + COL_DELETED + " = 0";
 	
	String appSerch = "SELECT A."+ COL_NAME + ", A."+ COL_ID + ", A."+ COL_MFR + ", O.PRICE"
									+ " FROM " + TABLE_BUY_INFO + " A"
									+ " JOIN APP_OPTION O ON A.AP_ID = O.AP_ID"
									+ " WHERE O.POSITION = 1 AND " + COL_DELETED + " = 0"
									+ " AND A." + COL_NAME + " LIKE ?";


	String appInfo = "SELECT * FROM " + TABLE_BUY_INFO + " WHERE " + COL_ID + " = ?";

	
	String appSoftDelte = "UPDATE " + TABLE_BUY_INFO + " SET "
										 + COL_DELETED + " = 1 "
										 + "WHERE " + COL_ID + " = ?";

	String appUpdate = "UPDATE " + TABLE_BUY_INFO + " SET "
									  + COL_NAME + " = ?, "
									  + COL_MFR + " = ?, "
									  + COL_INFO + " = ? "
									  + "WHERE AP_ID = ?";
	//나중에 휴지통 기능 구현한다면 사용
	String appDelete = "DELETE FROM " + TABLE_BUY_INFO + " WHERE " + COL_ID + " = ?";
	
	String detailInsert = "INSERT INTO " + ORDER_DETAIL_TABLE + " VALUES( " + COL_SEQ2 + ", ?, ?, ?, ?)";

	String ORDER_LIST = "SELECT "
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
	
	
	
	String LAST_ORDER = "SELECT "
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
	
	
	String ORDER_LIST_BY_ORDER_NUMBER = "SELECT "
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
			
			

}
