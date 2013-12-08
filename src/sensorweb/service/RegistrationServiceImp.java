package sensorweb.service;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sensorweb.javaBean.Data;
import sensorweb.javaBean.Sensor;
import sensorweb.server.TCPServer;
import sensorweb.util.MongoUtil;


public class RegistrationServiceImp {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public RegistrationServiceImp(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	private ArrayList<Sensor> sensors = new ArrayList<Sensor>();
	
	public ArrayList<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(ArrayList<Sensor> sensors) {
		this.sensors = sensors;
	}

	Sensor sensor = new Sensor();
	Data data = new Data();
	
	
	public void register() throws IOException,ServletException{
		String sensorID = request.getParameter("sensorID");
		String description = request.getParameter("description");
		String keyword = request.getParameter("keyword");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String samplingTime = request.getParameter("samplingTime");
		String easting = request.getParameter("easting");
		String northing = request.getParameter("northing");
		String altitude = request.getParameter("altitude");
		String observableProperty = request.getParameter("observableProperty");
		String uom = request.getParameter("uom");
		
		//如果DB中不存在该Sensor 插入到注册表中
//		if(HibernateUtil.get(Sensor.class, sensorID)==null){
		if(MongoUtil.ds.createQuery(Sensor.class).field("sensorID").equal(sensorID).get()==null){
			System.out.println("开始注册!");
			sensor.setSensorID(sensorID);
			sensor.setDescription(description);
			sensor.setKeyword(keyword);
			sensor.setBeginTime(beginTime);
			sensor.setEndTime(endTime);
			sensor.setSamplingTime(samplingTime);
			sensor.setEasting(easting);
			sensor.setNorthing(northing);
			sensor.setAltitude(altitude);
			sensor.setObservableProperty(observableProperty);
			sensor.setUom(uom);
			
			System.out.println("SensorID:"+sensor.getSensorID());
			MongoUtil.add(sensor);
			System.out.println("注册成功!");
			
			RequestDispatcher dispathcer = request.getRequestDispatcher("RegistrationSuccess.jsp");
			dispathcer.forward(request, response);
		}else{
			RequestDispatcher dispathcer = request.getRequestDispatcher("AlreadyRegister.jsp");
			dispathcer.forward(request, response);
		}
	}
	
	public ArrayList<Sensor> getAllSensorsFromDB(){
		this.setSensors((ArrayList<Sensor>) MongoUtil.ds.createQuery(Sensor.class).asList());
		return this.getSensors();
	}
	
	public void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		sensors = this.getAllSensorsFromDB();
		HttpSession session = request.getSession();
		session.setAttribute("AllSensors", sensors);
		session.setAttribute("SensorStatus", TCPServer.getInstance().getSensorStatus());
		RequestDispatcher dispatcher= request.getRequestDispatcher("ShowAllSensors.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getAllSensorsFromDB();
		showAll(request,response);
		register();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
}
