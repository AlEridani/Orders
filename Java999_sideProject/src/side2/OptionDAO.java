package side2;

import java.util.ArrayList;

public interface OptionDAO {
	
	public abstract int insert (OptionDTO dto);
	
	public abstract ArrayList<OptionDTO> select ();
	
	public abstract int update(OptionDTO dto);
	
	public abstract int delete (String optionId);
	
	//apid가 같은 모든것 찾는거
	public abstract ArrayList<OptionDTO> serchByApId(int apId);

}
