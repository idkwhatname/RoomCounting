package com.mongodb.DBInterface;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;
import com.mongodb.models.*;

public class DatabaseEditableInterface extends DatabaseInterface {

	
	private String DB_NAME = "Database";
	private MongoDatabase db;
	
	public DatabaseEditableInterface(String url , int port) {
		db = getConnection(url , port).getDatabase(DB_NAME);
	}
	
	public DatabaseEditableInterface(){
		this("localhost" , 27017);
	}
	
	private static MongoClient getConnection(String url , int port_num) {
        
        MongoClient mongoClntObj = new MongoClient(url, port_num);
        return mongoClntObj;
	}
	
	public MongoDatabase getDB(){
		return db;
	}
	
	private boolean deleteDocument(Document doc , String collectionName) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		try {
			collection.deleteOne(doc);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private boolean updateRoom(Document doc,String name,String capacity, String collectionName) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		try {
			if(name != null) {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("roomName", name)));
			}
			if(capacity != null) {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("capacity", capacity)));
			}

		}catch (Exception e) {
			return false;
		}
		return true;
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
	
	public boolean deleteSession(String sessionId) {
		return deleteDocument(new Document("_id", new ObjectId(sessionId)) , "Sessions");
	}
	
	public boolean deleteRoom(String roomId) {
		return deleteDocument(new Document("_id", new ObjectId(roomId)) , "Rooms");
	}
	
	public boolean deleteTimeSlot(String timeSlotId) {
		return deleteDocument(new Document("_id", new ObjectId(timeSlotId)), "TimeSlots");
	}
	
	
	public boolean updateRoom(String roomId,String name, String capactity) {
		return updateRoom(new Document("_id", new ObjectId(roomId)), name, capactity, "Rooms" );
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
