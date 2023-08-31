package side1;

import java.util.ArrayList;

public interface PurchaseDAO {

	public abstract int purchase(PurchaseDTO dto);

	public abstract ArrayList<PurchaseDTO> purchaseRecord(String id);// 세션 아이디를받아서 해당 주문 기록만 가져와야되네

}