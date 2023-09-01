package side1;

import java.util.ArrayList;

public interface ApplianceDAO {

	public abstract int appInsert(ApplianceDTO dto);

	public abstract ArrayList<ApplianceDTO> select ();

	public abstract ArrayList<ApplianceDTO> serch(String apName);
	
	public abstract ArrayList<ApplianceDTO> serchByCatogorie(String apName, String catecorie);
	
	public abstract ArrayList<ApplianceDTO> appPurchaseShow(String apName);

	public abstract ApplianceDTO appInfo(int index);

	public abstract int appUpdate(ApplianceDTO dto);//테이블 클릭으로 인덱스 찾기

	public abstract int appDelete(int index);
	
	public abstract ArrayList<ApplianceDTO> mainTableShow();
}