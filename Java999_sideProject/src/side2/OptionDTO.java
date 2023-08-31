package side2;

public class OptionDTO {
	private int optionId;
	private String optionName;
	private int price;
	private int stock;
	private int apId;
	private int position;
	private String optionNumber;
	

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public OptionDTO(int optionId, String optionName, int price, int stock, int apId, int position,
			String optionNumber) {
		this.optionId = optionId;
		this.optionName = optionName;
		this.price = price;
		this.stock = stock;
		this.apId = apId;
		this.position = position;
		this.optionNumber = optionNumber;
	}
	
	public OptionDTO(String optionNumber, String optionName, int price, int stock ) {
		this.optionNumber = optionNumber;
		this.optionName = optionName;
		this.price = price;
		this.stock = stock;
	
	
	}
	
	public OptionDTO(String optionName, int price, int stock, int apId, int position,
			String optionNumber) {
	
		this.optionName = optionName;
		this.price = price;
		this.stock = stock;
		this.apId = apId;
		this.position = position;
		this.optionNumber = optionNumber;
	}

	public String getOptionNumber() {
		return optionNumber;
	}

	public void setOptionNumber(String optionNumber) {
		this.optionNumber = optionNumber;
	}

	public OptionDTO() {
	}

	public OptionDTO(int optionId, String apName, int price, int stock, int apId, int position) {
		this.optionId = optionId;
		this.optionName = apName;
		this.price = price;
		this.stock = stock;
		this.apId = apId;
		this.position = position;
	}

	public OptionDTO(int optionId, String apName, int price, int stock) {
		this.optionId = optionId;
		this.optionName = apName;
		this.price = price;
		this.stock = stock;

	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getApName() {
		return optionName;
	}

	public void setApName(String apName) {
		this.optionName = apName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getApId() {
		return apId;
	}

	public void setApId(int apId) {
		this.apId = apId;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override

	// 옵션1 제품명+ 제품코드 + 재고 + 가격
	public String toString() {
		return "옵션" + position + " " + optionName + " " + optionId + " (" + price + "원)";
	}

	public long getTotalPrice() {
		return price * stock;
	}

}
