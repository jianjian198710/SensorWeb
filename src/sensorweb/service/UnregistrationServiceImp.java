package sensorweb.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		if((sensor = MongoUtil.ds.createQuery(Sensor.class).field("senosrID").equal(sensorID).get())!=null){
			MongoUtil.delete(sensor);
			TCPServer.getInstance().getSensorIDs().remove(sensorID);
			//将注销的Sensor从TCPServer的保存表中删除
			System.out.println("TCPServer中保存的Sensor列表为: "+TCPServer.getInstance().getSensorIDs());
			System.out.println("注销成功!!!!!!!!!");
		}
		else{
			System.out.println("DB中不存在该Sensor!!!!");
		}
	}
	
	public void deleteData(Sensor sensor){
		//Not necessary 删注册表时会自动删关联的insertion表
		//在MongoDB中is necessary 必须手动删除datas集合中的相关数据
		//HibernateUtil.getSession().createQuery("from insertion where sensorid="+sensor.getSensorID());
	}
}
