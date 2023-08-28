package side2;

public class MemberDTO {
	private String memberID;
	private char[] pw;
	private String name;
	private String email;
	private String phone;
	private String memberGrade;

	public MemberDTO() {}

	public MemberDTO(String memberID, char[] pw, String name, String email, String phone) {
		this.memberID = memberID;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public MemberDTO(String memberID, String memberGrade) {
		this.memberID = memberID;
		this.memberGrade = memberGrade;
	}// 초기 비회원 상태 설정용 맴버 권한 변경용

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public char[] getPw() {
		return pw;
	}

	public void setPw(char[] pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

}