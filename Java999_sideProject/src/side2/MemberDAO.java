package side2;

import java.util.ArrayList;



public interface MemberDAO {

	public abstract int insert(MemberDTO dto);

	public abstract ArrayList<MemberDTO> select();

	public abstract MemberDTO currentUserInfo(String id);

	public abstract ArrayList<MemberDTO> serch(String id);

	public abstract int update(MemberDTO dto);

	public abstract int delete(String id);

	public abstract int memberGrade(MemberDTO dto);

	public abstract int memberChangeAdminToUser(MemberDTO dto);
	


}