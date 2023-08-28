package side2;

public class OptionDTO {
	private String optionId;
	private String optionName;
	private int price;
	private int stock;
	private int apId;
	private int position;
	



	public OptionDTO() {}
	
	
	public OptionDTO(String optionId, String apName, int price, int stock, int apId,int position) {
		this.optionId = optionId;
		this.optionName = apName;
		this.price = price;
		this.stock = stock;
		this.apId = apId;
		this.position = position;
	}
	
	public OptionDTO(String optionId, String apName, int price, int stock) {
		this.optionId = optionId;
		this.optionName = apName;
		this.price = price;
		this.stock = stock;

	}


	public String getOptionId() {
		return optionId;
	}


	public void setOptionId(String optionId) {
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
	
	//옵션1 제품명+ 제품코드 + 재고 + 가격
	public String toString() {
		return "옵션" + position + " " + optionName + " " + optionId + " (" + price + "원)";
	}
	
	

}
