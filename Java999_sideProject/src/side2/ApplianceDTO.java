package side2;

public class ApplianceDTO {
	private int apID;
	private String apName;
	private String apMfr;
	private String apInfo;
	private int mainPrice;



	public ApplianceDTO() {}

	public ApplianceDTO(int apID, String apName, String apMfr, String apInfon, int mainPrice) {
		this.apID = apID;
		this.apName = apName;
		this.apMfr = apMfr;
		this.apInfo = apInfo;
		this.mainPrice = mainPrice;
	}
	
	public ApplianceDTO(int apID, String apName, String apMfr, String apInfo) {
		this.apID = apID;
		this.apName = apName;
		this.apMfr = apMfr;
		this.apInfo = apInfo;
		
	}
	
	public ApplianceDTO( String apName, String apMfr, String apInfo) {
		this.apName = apName;
		this.apMfr = apMfr;
		this.apInfo = apInfo;
	}
	
	public ApplianceDTO( String apName, String apMfr, String apInfo, int mainPrice) {
		this.apName = apName;
		this.apMfr = apMfr;
		this.apInfo = apInfo;
		this.mainPrice = mainPrice;
	}

	public int getApID() {
		return apID;
	}

	public void setApID(int apID) {
		this.apID = apID;
	}

	public String getApName() {
		return apName;
	}

	public void setApName(String apName) {
		this.apName = apName;
	}


	public String getApMfr() {
		return apMfr;
	}

	public void setApMfr(String apMfr) {
		this.apMfr = apMfr;
	}

	public String getApInfo() {
		return apInfo;
	}

	public void setApInfo(String apInfo) {
		this.apInfo = apInfo;
	}

	public int getMainPrice() {
		return mainPrice;
	}

	public void setMainPrice(int mainPrice) {
		this.mainPrice = mainPrice;
	}

	@Override
	public String toString() {
		return "ApplianceDTO [apID=" + apID + ", apName=" + apName + ", apMfr=" + apMfr + ", apInfo=" + apInfo
				+ ", mainPrice=" + mainPrice + "]";
	}





}