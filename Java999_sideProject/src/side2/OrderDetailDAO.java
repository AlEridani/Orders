package side2;

import java.util.ArrayList;

public interface OrderDetailDAO {

	public abstract int orderInsert(OrderDetailDTO dto);
	
	public abstract ArrayList<OrderDetailDTO> orderSelect(String id);
	

	
	public abstract ArrayList<OrderDetailDTO>lastOrder(String id);
	
	public abstract ArrayList<OrderDetailDTO>optionList(String id, int ordernumber);
}
