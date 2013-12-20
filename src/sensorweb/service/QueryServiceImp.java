package sensorweb.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sensorweb.javaBean.Data;
import sensorweb.util.MongoUtil;

import com.google.code.morphia.query.Query;

public class QueryServiceImp {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	private static final int DISPLAY_NUM = 20;
	
	
	public QueryServiceImp(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public QueryServiceImp(){}
	
	public void getAllData(HttpServletRequest request,HttpServletResponse respons){
		Query<Data> datas = MongoUtil.ds.createQuery(Data.class).order("-timeStamp");
		HttpSession session = request.getSession();
		session.setAttribute("AllDatas", datas.asList());
		
		//只显示前30条数据
		session.setAttribute("currentPageDatas",datas.limit(DISPLAY_NUM).asList());
		
		//显示MaxPage
		int pageTotalNumber;
		int dataTotalNumber = (int)datas.countAll();
		if(dataTotalNumber % DISPLAY_NUM==0){
			pageTotalNumber = dataTotalNumber/DISPLAY_NUM;
		}else{
			pageTotalNumber = dataTotalNumber/DISPLAY_NUM+1;
		}
		session.setAttribute("pageTotalNumber", pageTotalNumber);
		session.setAttribute("currentPageNumber", 1);
	}
	
	public synchronized void getNewData(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<Data> oldDatas = (ArrayList<Data>)session.getAttribute("AllDatas");
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
	
	/*
	 * 分页 每页显示20条Data
	 */
	public void pageDisplay(HttpServletRequest request,HttpServletResponse response,int page){
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<Data> oldDatas = (ArrayList<Data>)session.getAttribute("AllDatas");
		//获取当前Data记录的所有数量
		int dataTotalNumber = oldDatas.size();
		//每页显示20条
		int pageTotalNumber;
		if(dataTotalNumber % DISPLAY_NUM==0){
			pageTotalNumber = dataTotalNumber/DISPLAY_NUM;
		}else{
			pageTotalNumber = dataTotalNumber/DISPLAY_NUM+1;
		}
		//输入页数大于范围的话 直接跳转到第一页
		if(page<=0||page>pageTotalNumber){
			page=1;
		}
		session.setAttribute("pageTotalNumber", pageTotalNumber);
		session.setAttribute("currentPageNumber", page);
		//指定页面的Data数据
		ArrayList<Data> currentPageDatas = new ArrayList<Data>();
		if(page>0&&page<pageTotalNumber){
			for(int i=(page-1)*DISPLAY_NUM;i<page*DISPLAY_NUM;i++){
				currentPageDatas.add(oldDatas.get(i));
			}
			//最后一页的特殊情况
		}else if(page==pageTotalNumber){
			for(int i=(page-1)*DISPLAY_NUM;i<dataTotalNumber;i++){
				currentPageDatas.add(oldDatas.get(i));
			}
			//输入范围错误 直接跳转到第一页
		}else{
			for(int i=0;i<20;i++){
				currentPageDatas.add(oldDatas.get(i));
			}
		}
		session.setAttribute("currentPageDatas", currentPageDatas);
	}
}
