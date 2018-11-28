package com.mongodb.DBInterface;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;
import com.mongodb.models.*;

public class DatabaseUpdateSessionInterface extends DatabaseInterface {

	
	private String DB_NAME = "Database";
	private MongoDatabase db;
	
	public DatabaseUpdateSessionInterface(String url , int port) {
		db = getConnection(url , port).getDatabase(DB_NAME);
	}
	
	public DatabaseUpdateSessionInterface(){
		this("localhost" , 27017);
	}
	
	private static MongoClient getConnection(String url , int port_num) {
        
        MongoClient mongoClntObj = new MongoClient(url, port_num);
        return mongoClntObj;
	}
	
	public MongoDatabase getDB(){
		return db;
	}
	
	
	private boolean updateSession(Document doc,String name,String begCount, String midCount , String endCount) {
		MongoCollection<Document> collection = db.getCollection("Sessions");
		try {
			if(name != null) {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("sessionName", name)));
			}
			if(begCount != null) {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("beginningCount", begCount)));
			}
			if(midCount != null) {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("middleCount", midCount)));
			}
			if(endCount != null) {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("endCount", endCount)));
			}

		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean updateSession(String sessionId , String name,String begCount, String midCount , String endCount) {
		return updateSession(new Document("_id", new ObjectId(sessionId)), name, begCount, midCount , endCount );
	}
	
	public List<TimeSlot> getTimeSlotList() {
		return super.getTimeSlotList();
	}
	
	public List<Session> getSessionList() {
		return super.getSessionList();
	}
	
	public List<Room> getRoomList() {
		return super.getRoomList();
	}
	

}
