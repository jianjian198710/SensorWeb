package sensorweb.util;

import java.net.UnknownHostException;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

public class MongoUtil{

	static Morphia morphia;
	static Mongo mongo;
	public static Datastore ds;

	
    private MongoUtil() {   
    }   
  
    static {   
		morphia = new Morphia();
		try {
			mongo = new Mongo();
		} catch(UnknownHostException | MongoException e) {
			e.printStackTrace();
		}
		ds = morphia.createDatastore(mongo, "sensorweb");
    }   
    
    public static synchronized void add(Object entity){
    	ds.save(entity, WriteConcern.SAFE);
    }
    
    public static synchronized void delete(Object entity){
    	ds.delete(entity, WriteConcern.SAFE);
    }
}
