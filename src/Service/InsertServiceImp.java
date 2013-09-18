package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBean.Data;
import JavaBean.Sensor;

public class InsertServiceImp {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public InsertServiceImp(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public void insert(Data data){
		if(HibernateUtil.get(Sensor.class, data.getSensor().getSensorID())!=null){
			
		}
	}
}
