package side1;

public class ApplianceDTO {
	private String apID;
	private String apName;
	private int apPrice;
	private String apMfr;
	private int apStock;
	private String optionName;
	private String categorie;
	private int apPkNumber;

	public int getApPkNumber() {
		return apPkNumber;
	}

	public void setApPkNumber(int apPkNumber) {
		this.apPkNumber = apPkNumber;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public ApplianceDTO(String apID, String apName, int apPrice, String apMfr, int apStock, String optionName,
			String categorie,int apPkNumber) {
		this.apID = apID;
		this.apName = apName;
		this.apPrice = apPrice;
		this.apMfr = apMfr;
		this.apStock = apStock;
		this.optionName = optionName;
		this.categorie =categorie;
		this.apPkNumber = apPkNumber;
	}

	public ApplianceDTO() {}

	public ApplianceDTO(String apID, String apName, int apPrice, String apMfr, int apStock) {
		this.apID = apID;
		this.apName = apName;
		this.apPrice = apPrice;
		this.apMfr = apMfr;
		this.apStock = apStock;
	}

	public String getApID() {
		return apID;
	}

	public void setApID(String apID) {
		this.apID = apID;
	}

	public String getApName() {
		return apName;
	}

	public void setApName(String apName) {
		this.apName = apName;
	}

	public int getApPrice() {
		return apPrice;
	}

	public void setApPrice(int apPrice) {
		this.apPrice = apPrice;
	}

	public String getApMfr() {
		return apMfr;
	}

	public void setApMfr(String apMfr) {
		this.apMfr = apMfr;
	}

	public int getApStock() {
		return apStock;
	}

	public void setApStock(int apStock) {
		this.apStock = apStock;
	}

	@Override
	public String toString() {
		return "ApplianceDTO [apID=" + apID + ", apName=" + apName + ", apPrice=" + apPrice + ", apMfr=" + apMfr
				+ ", apStock=" + apStock + "]";
	}

}