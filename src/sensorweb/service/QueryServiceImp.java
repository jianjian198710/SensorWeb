package sensorweb.service;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sensorweb.javaBean.Data;
import sensorweb.util.MongoUtil;

import com.google.code.morphia.query.Query;

public class QueryServiceImp {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	private LinkedList<Data> datalist = new LinkedList<>();
	
	public QueryServiceImp(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public QueryServiceImp(){
		
	}
	
	public void getAllData(HttpServletRequest request,HttpServletResponse respons){
		Query<Data> datas = MongoUtil.ds.createQuery(Data.class).order("-timeStamp");
		for(Data data:datas.fetch()){
			this.getDatalist().add(data);
		}		
		System.out.println("所有的Data: "+getDatalist());
		request.setAttribute("AllDatas", getDatalist());
		
		HttpSession session = request.getSession();
		session.setAttribute("AllDatas", this.getDatalist());
	}
	
	public synchronized void getNewData(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		LinkedList<Data> oldDatas = (LinkedList<Data>)session.getAttribute("AllDatas");
		
		int DataCount = oldDatas.size();
		//获取当前DB Data数量
		int newDataCount = (int) (MongoUtil.ds.createQuery(Data.class).countAll()-DataCount);
		session.setAttribute("NewDataCount", newDataCount);
		if(newDataCount==0){
			System.out.println("没有更新!!!");
		}else{
			System.out.println("Data更新数: "+newDataCount);
		}
	}
	
	public LinkedList<Data> getDatalist() {
		return datalist;
	}

	public void setDatalist(LinkedList<Data> datalist) {
		this.datalist = datalist;
	}
}
