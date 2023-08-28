package side2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

public class OptionDAOImple implements OptionDAO {
	
	private static OptionDAOImple instance = null;
	
	public OptionDAOImple() {}
	
	public static OptionDAOImple getInstance() {
		if (instance == null) {
			instance = new OptionDAOImple();
		}
		return instance;
	}
	
	
	
	private static final String TABLE_NAME = "APP_OPTION";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "side3";
	private static final String PW = "123";
	
	private static final String COL_ID = "OPTION_ID";
	private static final String COL_NAME = "OPTION_NAME";
	private static final String COL_PRICE = "PRICE";
	private static final String COL_STOCK = "STOCK";
	private static final String COL_AP_ID = "AP_ID";
	private static final String COL_POSITION = "POSITION";
	
	private static final String OPTION_INSERT = "INSERT INTO " + TABLE_NAME +
												" VALUES (?,?,?,?,?,?)";
	private static final String OPTION_BY_APID = "SELECT * FROM " + TABLE_NAME
												+ " WHERE " + COL_AP_ID + " = ?"
												+ " ORDER BY " + COL_POSITION;
	
	private static final String OPTION_UPDATE = "UPDATE " + TABLE_NAME + " SET " 
												+ COL_NAME + " = ?, "
												+ COL_PRICE + " = ?, "
												+ COL_STOCK + " = ?"
												+ " WHERE " + COL_ID +" = ?";
											
	//1네임 2가격 3 재고 4 where optionid
	
	ArrayList<OptionDTO> list = new ArrayList<>();
	@Override
	public int insert(OptionDTO dto) {
		System.out.println("optionDAO 실행확인");
		int result = -1;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(OPTION_INSERT);
			pstmt.setString(1, dto.getOptionId());
			pstmt.setString(2, dto.getApName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, dto.getStock());
			pstmt.setInt(5, dto.getApId());
			pstmt.setInt(6, dto.getPosition());
			
			result = pstmt.executeUpdate();
			System.out.println("insert 쿼리문 이후");
			
			conn.close();
			pstmt.close();
			
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ArrayList<OptionDTO> select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(OptionDTO dto) {
		int result = -1;
		try {
			//1네임 2가격 3 재고 4 where optionid
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(OPTION_UPDATE);
			System.out.println("업데이트 sql 문 확인 : " + OPTION_UPDATE);
			pstmt.setString(1, dto.getApName());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setInt(3, dto.getStock());
			pstmt.setString(4, dto.getOptionId());
			
			result = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

	@Override
	public int delete(String optionId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<OptionDTO> serchByApId(int apId) {
		
			try {
				DriverManager.registerDriver(new OracleDriver());
				Connection conn = DriverManager.getConnection(URL, USER, PW);
				PreparedStatement pstmt = conn.prepareStatement(OPTION_BY_APID);
				
				pstmt.setInt(1, apId);
				
				ResultSet rs = pstmt.executeQuery();
				list = new ArrayList<>();
				while(rs.next()) {
					OptionDTO dto = new OptionDTO();
					dto.setOptionId(rs.getString(COL_ID));
					dto.setApName(rs.getString(COL_NAME));
					dto.setPrice(rs.getInt(COL_PRICE));
					dto.setStock(rs.getInt(COL_STOCK));
					dto.setPosition(rs.getInt(COL_POSITION));
					list.add(dto);
				}
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				

		return list;
	}

}
