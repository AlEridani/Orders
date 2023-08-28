package side2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;


import oracle.jdbc.driver.OracleDriver;

public class MemberDAOImple implements MemberDAO {

	private static final String TABLE_NAME = "MEMBER";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "side3";
	private static final String PW = "123";
	private static final String COL_ID = "MEMBER_ID";
	private static final String COL_PW = "PW";
	private static final String COL_NAME = "NAME";
	private static final String COL_EMAIL = "EMAIL";
	private static final String COL_PHONE = "PHONE";
	private static final String COL_GRADE = "MANAGE_GRADE";

	// 1 ID
	// 2.PW
	// 3.NAME
	// 4.EMAIL
	// 5.PHONE
	private static String memberInsert = "INSERT INTO " + TABLE_NAME + "(" + COL_ID + ", " + COL_PW + ", " + COL_NAME
			+ ", " + COL_EMAIL + ", " + COL_PHONE + ")" + " VALUES (?, ?, ?, ?, ?)";

	private static String memberSelect = "SELECT * FROM " + TABLE_NAME;

	private static String currentUserInfo = "SELECT * FROM " + TABLE_NAME + " WHERE MEMBER_ID = ?";

	private static String memberDelete = "DELETE FROM " + TABLE_NAME + " WHERE MEMBER_ID = ?";

	private static String memberSerch = "SELECT * FROM " + TABLE_NAME + " WHERE MEMBER_ID LIKE ?";

	private static String memberChangeAdmin = "UPDATE " + TABLE_NAME + " SET MANAGE_GRADE = 'ADMIN' WHERE "
											    + COL_ID + " = ?";

	private static String memberAdminToUser = "UPDATE " + TABLE_NAME + " SET MANAGE_GRADE = 'USER' WHERE "
											 + COL_ID + " = ?";

	private static String memberUpdate = "UPDATE " + TABLE_NAME + " SET "
										+ COL_PW + " = ?, "
										+ COL_NAME + " = ?, "
										+ COL_EMAIL + " = ?, "
										+ COL_PHONE + " = ? "
										+ "WHERE " + COL_ID + "= ?";
	


	private static MemberDAOImple instance = null;

	// 2. private 생성자
	private MemberDAOImple() {

	}

	// 3. public static 메소드 - 인스턴스를 리턴하는 메소드 구현
	public static MemberDAOImple getInstance() {
		if (instance == null) {
			instance = new MemberDAOImple();
		}
		return instance;
	}

	ArrayList<MemberDTO> list = new ArrayList<>();

	@Override
	public int insert(MemberDTO dto) {
		int result = -1;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(memberInsert);
			String password = new String(dto.getPw());
			System.out.println(memberInsert);

			pstmt.setString(1, dto.getMemberID());
			pstmt.setString(2, password);
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhone());

			result = pstmt.executeUpdate();

			System.out.println("데이터 등록 성공");

			conn.close();
			pstmt.close();
			
			return result; 
		}catch (SQLIntegrityConstraintViolationException e){
			System.out.println("아이디 중복");
		} catch (NullPointerException e) {
			e.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}

		return result;
	}

	@Override
	public ArrayList<MemberDTO> select() {

		try {

			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(memberSelect);

			ResultSet rs = pstmt.executeQuery();
			list = new ArrayList<>();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setMemberID(rs.getString(COL_ID));
				dto.setPw(rs.getString(COL_PW).toCharArray());
				dto.setName(rs.getString(COL_NAME));
				dto.setEmail(rs.getString(COL_EMAIL));
				dto.setPhone(rs.getString(COL_PHONE));
				dto.setMemberGrade(rs.getString(COL_GRADE));
				list.add(dto);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("전체출력 실패");
		}

		return list;
	}

	@Override
	public MemberDTO currentUserInfo(String id) {

		 MemberDTO dto = null;
		try {

			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL, USER, PW);
			PreparedStatement pstmt = conn.prepareStatement(currentUserInfo);

			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				dto = new MemberDTO();

				dto.setMemberID(rs.getString(1));
				dto.setPw(rs.getString(2).toCharArray());
				dto.setName(rs.getString(3));
				dto.setEmail(rs.getString(4));
				dto.setPhone(rs.getString(5));
				dto.setMemberGrade(rs.getString(6));


			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


		return dto;
	}

	@Override
	public int update(MemberDTO dto) {
		int result = -1;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL,USER,PW);
			PreparedStatement pstmt = conn.prepareStatement(memberUpdate);
			//인자 5개 pw,name,email,phone,id순
			String password = new String(dto.getPw());
	
			pstmt.setString(1, password);
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getPhone());
			pstmt.setString(5, dto.getMemberID());

			result = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
			return result;
		} catch (Exception e) {
			System.out.println("수정실패");
			e.printStackTrace();
		}


		return result;
	}

	@Override
	public int delete(String id) {
		int result = -1;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL,USER,PW);
			PreparedStatement pstmt = conn.prepareStatement(memberDelete);
			System.out.println(memberDelete);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int memberGrade(MemberDTO dto) {
		int result =-1;
		System.out.println(memberChangeAdmin);
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL,USER,PW);
			PreparedStatement pstmt = conn.prepareStatement(memberChangeAdmin);
			pstmt.setString(1, dto.getMemberID());

			result = pstmt.executeUpdate();

			conn.close();
			pstmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("어드민권한 변경 실패");
		}

		return result;
	}


	@Override
	public ArrayList<MemberDTO> serch(String id) {
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL,USER,PW);
			PreparedStatement pstmt = conn.prepareStatement(memberSerch);
			System.out.println(memberSerch);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			list = new ArrayList<>();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setMemberID(rs.getString(COL_ID));
				dto.setPw(rs.getString(COL_PW).toCharArray());
				dto.setName(rs.getString(COL_NAME));
				dto.setEmail(rs.getString(COL_EMAIL));
				dto.setPhone(rs.getString(COL_PHONE));
				dto.setMemberGrade(rs.getString(COL_GRADE));
				list.add(dto);
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return list;

	}

	@Override
	public int memberChangeAdminToUser(MemberDTO dto) {
		int result =-1;
		System.out.println(memberChangeAdmin);
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection conn = DriverManager.getConnection(URL,USER,PW);
			PreparedStatement pstmt = conn.prepareStatement(memberAdminToUser);
			pstmt.setString(1, dto.getMemberID());

			result = pstmt.executeUpdate();

			conn.close();
			pstmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("어드민 권한 변경 실패");
		}

		return result;
	}


}