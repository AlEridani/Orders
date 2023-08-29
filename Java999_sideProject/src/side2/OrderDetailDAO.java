package side2;

import java.util.ArrayList;

public interface OrderDetailDAO {

	public abstract int orderInsert(OrderDetailDTO dto);
	
	public abstract ArrayList<OrderDetailDTO> orderSelect();
}
