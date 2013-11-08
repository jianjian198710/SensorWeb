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
		if((sensor = (Sensor)HibernateUtil.get(Sensor.class, sensorID))!=null){
			HibernateUtil.delete(sensor);
			TCPServer.getInstance().getSensorIDs().remove(sensorID);
			//��ע����Sensor��TCPServer�ı������ɾ��
			System.out.println("TCPServer�б����Sensor�б�Ϊ: "+TCPServer.getInstance().getSensorIDs());
			System.out.println("ע���ɹ�!!!!!!!!!");
		}
		else{
			System.out.println("DB�в����ڸ�Sensor!!!!");
		}
	}
	
	public void deleteData(Sensor sensor){
		//Not necessary ɾע���ʱ���Զ�ɾ������insertion��
		HibernateUtil.getSession().createQuery("from insertion where sensorid="+sensor.getSensorID());
	}
}
