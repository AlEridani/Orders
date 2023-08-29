package side2;

import java.util.Date;

public class PurchaseDTO {
	private int orderNumber;
	private String memberID;
	private int apID;
	private String apName;
	private String apMfr;
	private String optionName;
	private String optionID;
	private int orderQunatity;
	private long orderPrice;
	private Date orderDate;

	public PurchaseDTO() {
	}

	public PurchaseDTO(String memberID, String optionID, int orderQunatity, long orderPrice) {
		this.memberID = memberID;
		this.optionID = optionID;
		this.orderQunatity = orderQunatity;
		this.orderPrice = orderPrice;

	}
	public PurchaseDTO(String memberID, int apID) {
		this.orderNumber = orderNumber;
		this.apID = apID;

	}
	

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public int getApID() {
		return apID;
	}

	public void setApID(int apID) {
		this.apID = apID;
	}

	public int getOrderQunatity() {
		return orderQunatity;
	}

	public void setOrderQunatity(int orderQunatity) {
		this.orderQunatity = orderQunatity;
	}

	public long getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(long orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "PurchaseDTO [orderNumber=" + orderNumber + ", memberID=" + memberID + ", apID=" + apID + ", apName="
				+ apName + ", apMfr=" + apMfr + ", orderQunatity=" + orderQunatity + ", orderPrice=" + orderPrice
				+ ", orderDate=" + orderDate + "]";
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
	
	public String getOptionID() {
		return optionID;
	}

	public void setOptionID(String optionID) {
		this.optionID = optionID;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

}