package sensorweb.service;

import sensorweb.javaBean.Data;

public class InsertServiceImp {
	
	public void insert(Data data){
			HibernateUtil.add(data);
	}
}
