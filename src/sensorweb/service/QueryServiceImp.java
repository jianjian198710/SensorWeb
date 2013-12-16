package sensorweb.service;

import java.util.ArrayList;
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
	
	private static final int DISPLAY_NUM = 20;
	
	
	private ArrayList<Data> datalist = new ArrayList<>();
	
	public QueryServiceImp(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public QueryServiceImp(){}
	
	public void getAllData(HttpServletRequest request,HttpServletResponse respons){
		Query<Data> datas = MongoUtil.ds.createQuery(Data.class).order("-timeStamp");
		for(Data data:datas.fetch()){
			this.getDatalist().add(data);
		}		
		System.out.println("���е�Data: "+getDatalist());
		request.setAttribute("AllDatas", getDatalist());
		
		HttpSession session = request.getSession();
		session.setAttribute("AllDatas", this.getDatalist());
		
		//ֻ��ʾǰ30������
		session.setAttribute("currentPageDatas",datas.limit(DISPLAY_NUM).asList());
		
		//��ʾMaxPage
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
		
		//��ȡ��ǰDB Data����
		int newDataCount = (int) (MongoUtil.ds.createQuery(Data.class).countAll()-DataCount);
		session.setAttribute("NewDataCount", newDataCount);
		if(newDataCount==0){
			System.out.println("û�и���!!!");
		}else{
			System.out.println("Data������: "+newDataCount);
		}
	}
	
	public void pageDisplay(HttpServletRequest request,HttpServletResponse response,int page){
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<Data> oldDatas = (ArrayList<Data>)session.getAttribute("AllDatas");
		//��ȡ��ǰData��¼����������
		int dataTotalNumber = oldDatas.size();
		//ÿҳ��ʾ20��
		int pageTotalNumber;
		if(dataTotalNumber % DISPLAY_NUM==0){
			pageTotalNumber = dataTotalNumber/DISPLAY_NUM;
		}else{
			pageTotalNumber = dataTotalNumber/DISPLAY_NUM+1;
		}
		session.setAttribute("pageTotalNumber", pageTotalNumber);
		session.setAttribute("currentPageNumber", page);
		//ָ��ҳ���Data����
		ArrayList<Data> currentPageDatas = new ArrayList<Data>();
		if(page>0&&page<pageTotalNumber){
			for(int i=(page-1)*DISPLAY_NUM;i<page*DISPLAY_NUM;i++){
				currentPageDatas.add(oldDatas.get(i));
			}
			//���һҳ���������
		}else if(page==pageTotalNumber){
			for(int i=(page-1)*DISPLAY_NUM;i<dataTotalNumber;i++){
				currentPageDatas.add(oldDatas.get(i));
			}
		}
		session.setAttribute("currentPageDatas", currentPageDatas);
	}
	
	public ArrayList<Data> getDatalist() {
		return datalist;
	}

	public void setDatalist(ArrayList<Data> datalist) {
		this.datalist = datalist;
	}
}
