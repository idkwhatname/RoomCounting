package com.mongodb.DBInterface;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database_Init_Interface extends DatabaseInterface{
	
	public Database_Init_Interface(String url , int port) {
		db = getConnection(url , port).getDatabase(DB_NAME);
	}
	
	public Database_Init_Interface(){
		this("localhost" , 27017);
	}
	
	public boolean pushSessionDocument(String sessionName , String id , String speaker , String roomId , String timeSlotId) {

		MongoCollection<Document> sessionColl = db.getCollection("Sessions");

		//check and convert ints
		/*
		if(!isNewRoom(id , sessionColl)){
			return false;
		}
		*/

        Document newSessionDoc = new Document("sessionID" , id).append(
				"sessionName" , sessionName
			).append(
				"speakerName" , speaker
			).append(
				"roomId" , roomId
			).append(
				"timeSlotId" , timeSlotId
			);

		
		sessionColl.insertOne(newSessionDoc);
		
		return true;
	}
	
	public boolean pushRoomDocument(String roomName , String id , String capacity) {
		
		MongoCollection<Document> roomColl = db.getCollection("Rooms");

		//check and convert ints

		/*
		if(!isNewRoom(roomName , roomColl)){
			return false;
		}
		*/

        Document newRoomDoc = new Document("roomID" , id).append(
				"roomName" , roomName
			).append(
				"capacity" , capacity
			);

		roomColl.insertOne(newRoomDoc);
		
		return true;
	}
	
	public boolean pushTimeslotDocument(String id , String startTime , String endTime) {
		
		MongoCollection<Document> timeSlotColl = db.getCollection("TimeSlots");

		//check and convert to epoch
		
		/*
		if(!isNewTimeSlot(startTime_int , endTime_int , timeSlotColl)){
			return false;
		}
		*/

        Document newTimeSlotDoc = new Document("timeSlotId" , id).append(
				"startTime" , startTime
			).append(
				"endTime" , endTime
			);

        timeSlotColl.insertOne(newTimeSlotDoc);
		
		return true;
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

}
