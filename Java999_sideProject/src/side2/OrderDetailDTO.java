package side2;

import java.util.HashMap;
import java.util.Map;

public class OrderDetailDTO {
	private int detailId;
	private int orderNumber;
	private String optionId;
	private int quantity;
	private int price;
	
    private Map<OptionDTO, Integer> orderOptions = new HashMap<>();

	public Map<OptionDTO, Integer> getOrderOptions() {
		return orderOptions;
	}
	public void setOrderOptions(Map<OptionDTO, Integer> orderOptions) {
		this.orderOptions = orderOptions;
	}
	@Override
	public String toString() {
		return "OrderDetailDTO [detailId=" + detailId + ", orderNumber=" + orderNumber + ", optionId=" + optionId
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public OrderDetailDTO(int detailId, int orderNumber, String optionId, int quantity, int price) {
		this.detailId = detailId;
		this.orderNumber = orderNumber;
		this.optionId = optionId;
		this.quantity = quantity;
		this.price = price;
	}
	
	public OrderDetailDTO(int orderNumber, String optionId, int quantity, int price) {
		this.orderNumber = orderNumber;
		this.optionId = optionId;
		this.quantity = quantity;
		this.price = price;
	}
	
	
	
	public OrderDetailDTO() {
	}
	
	
}