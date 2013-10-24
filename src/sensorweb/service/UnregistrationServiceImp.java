package sensorweb.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sensorweb.javaBean.Sensor;
import sensorweb.server.TCPServer;


public class UnregistrationServiceImp {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	public UnregistrationServiceImp(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	
	private String sensorID;
	private Sensor sensor;
	
	public void unregister(){
		sensorID = request.getParameter("sensorID");
		System.out.println(sensorID);
		if((sensor = (Sensor)HibernateUtil.get(Sensor.class, sensorID))!=null){
			HibernateUtil.delete(sensor);
			TCPServer.getInstance().getSensorIDs().remove(sensorID);
			//��ע����Sensor��TCPServer�ı������ɾ��
			System.out.println("SensorIDs"+TCPServer.getInstance().getSensorIDs());
			System.out.println("ע���ɹ�!!!!!!!!!");
		}
		else{
			System.out.println("DB�в����ڸ�Sensor!!!!");
		}
	}
	
	public void deleteData(){
		//TODO HQL
	}
}
