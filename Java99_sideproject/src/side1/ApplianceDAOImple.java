package side1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import oracle.jdbc.driver.OracleDriver;

public class ApplianceDAOImple implements ApplianceDAO {


	private static final String TABLE_NAME = "APPLIANCE";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "side2";
	private static final String PW = "123";
	private static final String COL_ID = "AP_ID";
	private static final String COL_NAME = "AP_NAME";
	private static final String COL_PRICE = "AP_PRICE";
	private static final String COL_MFR = "AP_MFR";
	private static final String COL_STOCK = "AP_STOCK";
	private static final String COL_DELETED = "IS_DELETED";
	private static final String COL_OPTION = "OPTION_NAME";
	private static final String COL_CATEGORIE = "CATEGORIE";
	private static final String COL_PK_NUMBER = "AP_NUMBER";
	private static final String COL_SEQ = "SEQ2.NEXTVAL";



	private static String appInsert = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?,?,?,?," + COL_SEQ + ")";

	private static String appSelect = "SELECT * FROM " + TABLE_NAME + 
									  " WHERE " + COL_DELETED +" = 0";
	
	private static String appSerch = "SELECT * FROM " + TABLE_NAME 
								   + " WHERE " + COL_NAME + " LIKE ?"
								   + " AND " + COL_DELETED + " = 0";
	
	private static String appSerchByCate  = "SELECT * FROM " + TABLE_NAME 
			   + " WHERE " + COL_NAME + " LIKE ?"
			   + " AND " + COL_CATEGORIE + " LIKE ?" 
			   + " AND " + COL_DELETED + " = 0";

	private static String appInfo = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL_PK_NUMBER + " = ?";
	
	private static String appPurchase = "SELECT * FROM " + TABLE_NAME + " WHERE " 
										+ COL_NAME + " = ? AND "
										+ COL_DELETED + " = 0";
	
	private static String appMainTable = "SELECT DISTINCT " 
										+ COL_NAME + ", "
										+ COL_ID + ", "
										+ COL_PRICE + ", "
										+ COL_MFR + ", "
										+ COL_STOCK + ", "
										+ COL_CATEGORIE
										+ " FROM " + TABLE_NAME
										+ " WHERE " + COL_DELETED + " = 0 ";
	

	
	private static String appSoftDelte = "UPDATE " + TABLE_NAME + " SET "
										 + COL_DELETED + " = 1 "
										 + "WHERE " + COL_PK_NUMBER + " = ?";

	private static String appUpdate = "UPDATE " + TABLE_NAME + " SET "
									  + COL_NAME + " = ?, "
									  + COL_PRICE + " = ?, "
									  + COL_MFR + " = ?, "
									  + COL_STOCK + " = ?, "
									  + COL_OPTION + " = ?, "
									  + COL_CATEGORIE + " = ?"
									  + " WHERE " + COL_PK_NUMBER + " = ?";
	//나중에 휴지통 기능 구현한다면 사용
	private static String appDelete = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_PK_NUMBER + " = ?";

	private static ApplianceDAOImple instance = null;

	private ApplianceDAOImple() {

	}

	public static ApplianceDAOImple getInstance() {
		if (instance == null) {
			instance = new ApplianceDAOImple();
		}
		return instance;
	}

	@Override
	public int appInsert(ApplianceDTO dto) {
		int result = -1;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(appInsert);
			System.out.println("실행 sql문 확인 : " + appInsert);
			//ID,이름,가격,제조사,재고,옵션명,카테고리
			pstmt.setString(1, dto.getApID());
			pstmt.setString(2, dto.getApName());
			pstmt.setInt(3, dto.getApPrice());
			pstmt.setString(4, dto.getApMfr());
			pstmt.setInt(5, dto.getApStock());
			pstmt.setInt(6, 0);
			pstmt.setString(7, dto.getOptionName());
			pstmt.setString(8, dto.getCategorie());
			result = pstmt.executeUpdate();

			pstmt.close();
			conn.close();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();

			
		}

		return result;
	}

	@Override
	public ArrayList<ApplianceDTO> select() {
		ArrayList<ApplianceDTO> list = new ArrayList<>();
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(appSelect);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(appSelect);
			
			while(rs.next()) {
				ApplianceDTO dto = new ApplianceDTO();
				dto.setApID(rs.getString(COL_ID));
				dto.setApName(rs.getString(COL_NAME));
				dto.setApPrice(rs.getInt(COL_PRICE));
				dto.setApMfr(rs.getString(COL_MFR));
				dto.setApStock(rs.getInt(COL_STOCK));
				dto.setOptionName(rs.getString(COL_OPTION));
				dto.setCategorie(rs.getString(COL_CATEGORIE));
				dto.setApPkNumber(rs.getInt(COL_PK_NUMBER));
				list.add(dto);
			}

			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql문 에러 :" + appSelect);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("다른 무언가 에러");
		}

		return list;
	}//end select

	@Override
	public ArrayList<ApplianceDTO> serch(String apName) {
		ArrayList<ApplianceDTO> list = new ArrayList<>();
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(appSerch);
			System.out.println("sql문 : " + appSerch);
			System.out.println("검색text : " + apName);
			pstmt.setString(1, apName);
			ResultSet rs = pstmt.executeQuery();

			list = new ArrayList<>();
			while(rs.next()) {
				//ID,이름,가격,제조사,재고순
				ApplianceDTO dto = new ApplianceDTO();
				dto.setApID(rs.getString(COL_ID));
				dto.setApName(rs.getString(COL_NAME));
				dto.setApPrice(rs.getInt(COL_PRICE));
				dto.setApMfr(rs.getString(COL_MFR));
				dto.setApStock(rs.getInt(COL_STOCK));
				dto.setOptionName(rs.getString(COL_OPTION));
				dto.setCategorie(rs.getString(COL_CATEGORIE));
				dto.setApPkNumber(rs.getInt(COL_PK_NUMBER));
				list.add(dto);
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql문 에러 :" + appSelect);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("다른 무언가 에러");
		}

		return list;
	}//end serch



	@Override
	public int appUpdate(ApplianceDTO dto) {
		int result = -1;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(appUpdate);
			//1.name 2.price 3.mfr 4.stock 5.id
			System.out.println("쿼리문 확인 : " + appUpdate);
			pstmt.setString(1, dto.getApName());
			pstmt.setInt(2, dto.getApPrice());
			pstmt.setString(3, dto.getApMfr());
			pstmt.setInt(4, dto.getApStock());
			pstmt.setString(5, dto.getOptionName());
			pstmt.setString(6, dto.getCategorie());
			pstmt.setInt(7, dto.getApPkNumber());
//			쿼리문 확인 : UPDATE APPLIANCE SET AP_NAME = ?, AP_PRICE = ?, AP_MFR = ?, AP_STOCK = ?, OPTION_NAME = ? CATEGORIE = ? WHERE AP_NUMBER = ?

			result = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
			return result;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}//and appUpdate

	@Override
	public int appDelete(int index) {
		int result = -1;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(appSoftDelte);
			System.out.println(appSoftDelte);
			pstmt.setInt(1, index);
			System.out.println("넘겨받은 인덱스 " + index);
			result = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//end appDelete

	@Override
	
	//int로 바꾸기
	public ApplianceDTO appInfo(int index) {
		ApplianceDTO dto = new ApplianceDTO();
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(appInfo);
			System.out.println("sql문 확인 : " + appInfo);
			System.out.println("매개변수 확인 : " + index);
			pstmt.setInt(1, index);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				dto.setApID(rs.getString(COL_ID));
				dto.setApName(rs.getString(COL_NAME));
				dto.setApPrice(rs.getInt(COL_PRICE));
				dto.setApMfr(rs.getString(COL_MFR));
				dto.setApStock(rs.getInt(COL_STOCK));
				dto.setOptionName(rs.getString(COL_OPTION));
				dto.setCategorie(rs.getString(COL_CATEGORIE));
				dto.setApPkNumber(rs.getInt(COL_PK_NUMBER));
			}

			rs.close();
			pstmt.close();
			conn.close();
			return dto;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql구문 에러");
		} 


		return null;
	}//end appInfo
	
	public ArrayList<ApplianceDTO> appPurchaseShow(String apName) {
		System.out.println("구매 리스트 sql 문 확인 " + appPurchase);
		ArrayList<ApplianceDTO> list = new ArrayList<>();
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(appPurchase);
			System.out.println("sql문 확인 : " + appInfo);
			System.out.println("매개변수 확인 : " + apName);
			pstmt.setString(1, apName);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				ApplianceDTO dto = new ApplianceDTO();
				dto.setApID(rs.getString(COL_ID));
				dto.setApName(rs.getString(COL_NAME));
				dto.setApPrice(rs.getInt(COL_PRICE));
				dto.setApMfr(rs.getString(COL_MFR));
				dto.setApStock(rs.getInt(COL_STOCK));
				dto.setOptionName(rs.getString(COL_OPTION));
				dto.setCategorie(rs.getString(COL_CATEGORIE));
				dto.setApPkNumber(rs.getInt(COL_PK_NUMBER));
				list.add(dto);
			}

			rs.close();
			pstmt.close();
			conn.close();
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql구문 에러");
		} 


		return null;
	}//end appInfo

	@Override
	public ArrayList<ApplianceDTO> serchByCatogorie(String apName, String catecorie) {
		ArrayList<ApplianceDTO> list = new ArrayList<>();
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(appSerchByCate);
			System.out.println("sql문 : " + appSerch);
			System.out.println("검색text : " + apName);
			pstmt.setString(1, apName);
			pstmt.setString(2, catecorie);
			ResultSet rs = pstmt.executeQuery();

			list = new ArrayList<>();
			while(rs.next()) {
				//ID,이름,가격,제조사,재고순
				ApplianceDTO dto = new ApplianceDTO();
				dto.setApID(rs.getString(COL_ID));
				dto.setApName(rs.getString(COL_NAME));
				dto.setApPrice(rs.getInt(COL_PRICE));
				dto.setApMfr(rs.getString(COL_MFR));
				dto.setApStock(rs.getInt(COL_STOCK));
				dto.setOptionName(rs.getString(COL_OPTION));
				dto.setCategorie(rs.getString(COL_CATEGORIE));
				dto.setApPkNumber(rs.getInt(COL_PK_NUMBER));
				list.add(dto);
			}

			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql문 에러 :" + appSelect);
		}catch (Exception e) {
			e.printStackTrace();
		
		}

		return list;	
	}

	@Override
	public ArrayList<ApplianceDTO> mainTableShow() {
		System.out.println("메인테이블 SQL문 확인" + appMainTable);
		ArrayList<ApplianceDTO> list = new ArrayList<>();
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(appMainTable);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(appSelect);
			
			while(rs.next()) {
				ApplianceDTO dto = new ApplianceDTO();
				dto.setApID(rs.getString(COL_ID));
				dto.setApName(rs.getString(COL_NAME));
				dto.setApPrice(rs.getInt(COL_PRICE));
				dto.setApMfr(rs.getString(COL_MFR));
				dto.setApStock(rs.getInt(COL_STOCK));
			
				dto.setCategorie(rs.getString(COL_CATEGORIE));
		
				list.add(dto);
			}

			conn.close();
			rs.close();
			pstmt.close();
			
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql문 에러 :" + appSelect);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("다른 무언가 에러");
		}

		return list;
		
	}

}//end ApplianceDAOImple