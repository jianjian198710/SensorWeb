package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JavaBean.Sensor;

public class ServiceImp {
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	public ServiceImp(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	Sensor sensor = new Sensor();
	public void fillData(){
		String sensorID = request.getParameter("sensorID");
		String description = request.getParameter("description");
		String keyword = request.getParameter("keyword");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String samplingTime = request.getParameter("samplingTime");
		String easting = request.getParameter("easting");
		String northing = request.getParameter("northing");
		String altitude = request.getParameter("altitude");
				
	}
}
