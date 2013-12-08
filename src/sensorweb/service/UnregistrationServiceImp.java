package sensorweb.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sensorweb.javaBean.Data;
import sensorweb.javaBean.Sensor;
import sensorweb.server.TCPServer;
import sensorweb.util.MongoUtil;


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
		if((sensor = MongoUtil.ds.createQuery(Sensor.class).field("_id").equal(sensorID).get())!=null){
			MongoUtil.delete(sensor);
			deleteData(sensorID);
			TCPServer.getInstance().getSensorStatus().put(sensorID, false);
			//��ע����Sensor��TCPServer�ı������ɾ��
			System.out.println("TCPServer�б����Sensor�б�Ϊ: "+TCPServer.getInstance().getSensorStatus());
			System.out.println("ע���ɹ�!!!!!!!!!");
		}
		else{
			System.out.println("DB�в����ڸ�Sensor!!!!");
		}
	}
	
	public void deleteData(String sensorID){
		//Not necessary ɾע���ʱ���Զ�ɾ������insertion��
		//��MongoDB��is necessary �����ֶ�ɾ��datas�����е��������
		//HibernateUtil.getSession().createQuery("from insertion where sensorid="+sensor.getSensorID());
		//��Sensor��Reference��ɾ��
		//BasicDBObject data = new BasicDBObject();
		//data.put("sensor.$id",sensorID);
		//MongoUtil.datas.remove(data);
		MongoUtil.ds.delete(MongoUtil.ds.createQuery(Data.class).field("sensorId").equal(sensorID));
	}
}
