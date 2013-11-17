package sensorweb.service;

import sensorweb.javaBean.Data;
import sensorweb.util.MongoUtil;

public class InsertServiceImp {
	
	public void insert(Data data){
			MongoUtil.add(data);
	}
}
