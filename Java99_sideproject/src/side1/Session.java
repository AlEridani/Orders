package side1;

public class Session {

	private MemberDTO dto;

	private static Session instance = null;

	private Session() {
	}

	public static Session getInstance() {
		if (instance == null) {
			instance = new Session();
		}
		return instance;
	}

	public MemberDTO getDto() {
		return dto;
	}

	public String getGrade() {
		String grade = dto.getMemberGrade();
		return grade;

	}

	public void setDto(MemberDTO dto) {
		this.dto = dto;
	}

	public void setDto(String id, String memberGrade) {
		this.dto = new MemberDTO(id, memberGrade);

	}

}