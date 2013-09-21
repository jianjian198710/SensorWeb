package Service;

import JavaBean.Data;

public class InsertServiceImp {
	
	public void insert(Data data){
			HibernateUtil.add(data);
	}
}
