package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBean.Sensor;

public class UnregistrationServiceImp {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public UnregistrationServiceImp(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	private String sensorID = request.getParameter("sensorID");
	private Sensor sensor;
	
	public void unregister(){
		if((sensor = (Sensor)HibernateUtil.get(Sensor.class, sensorID))!=null){
			HibernateUtil.delete(sensor);
			System.out.println("注销成功!!!!!!!!!");
		}
		else{
			System.out.println("DB中不存在该Sensor!!!!");
		}
	}
	
	public void deleteData(){
		//TODO HQL
	}
}
