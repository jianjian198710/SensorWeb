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
	private Data data;
	
	public String unregister(){
		sensorID = request.getParameter("sensorID");
		System.out.println(sensorID);
		if((sensor = MongoUtil.ds.createQuery(Sensor.class).field("_id").equal(sensorID).get())!=null){
			MongoUtil.delete(sensor);
			if((data = MongoUtil.ds.createQuery(Data.class).field("_id").equal(sensorID).limit(1).get())!=null){
				deleteData(sensorID);
			}
			TCPServer.getInstance().getSensorStatus().put(sensorID, false);
			//将注销的Sensor从TCPServer的保存表中删除
			System.out.println("TCPServer中保存的Sensor列表为: "+TCPServer.getInstance().getSensorStatus());
			System.out.println("注销成功!!!!!!!!!");
			return "SUCCESS";
		}
		else{
			System.out.println("DB中不存在该Sensor!!!!");
			return "FAILED";
		}
	}
	
	public void deleteData(String sensorID){
		//Not necessary 删注册表时会自动删关联的insertion表
		//在MongoDB中is necessary 必须手动删除datas集合中的相关数据
		//HibernateUtil.getSession().createQuery("from insertion where sensorid="+sensor.getSensorID());
		//用Sensor做Reference的删除
		//BasicDBObject data = new BasicDBObject();
		//data.put("sensor.$id",sensorID);
		//MongoUtil.datas.remove(data);
		MongoUtil.ds.delete(MongoUtil.ds.createQuery(Data.class).field("sensorId").equal(sensorID));
	}

}
