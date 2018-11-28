package com.mongodb.DBInterface;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database_Init_Interface extends DatabaseUpdateSessionInterface{
	private MongoDatabase db;
	private int nextRoomId = 0;
	private int nextTimeId = 0;

	public Database_Init_Interface(String url , int port) {
		super(url , port);
		db = super.getDB();
	}

	public Database_Init_Interface(){
		super();
		db = super.getDB();
	}

	private boolean pushDocument(String collectionName, Document doc ){
		MongoCollection<Document> coll = db.getCollection(collectionName);
		try{
			coll.insertOne(doc);
		} catch (Exception e){
			return false;
		}
		return true;
		
	}

	public boolean pushSessionDocument(String sessionName, String id, String speaker, String roomId,
			String timeSlotId) {


		// if (!isNewRoom(id, sessionColl)) {
		// 	return false;
		// }

		Document newSessionDoc = new Document("sessionID", id).append("sessionName", sessionName)
				.append("speakerName", speaker).append("roomId", roomId).append("timeSlotId", timeSlotId);

		return pushDocument("Session" , newSessionDoc);
	}
<<<<<<< HEAD
	
	public boolean pushTimeslotDocument(String startTime , String endTime) {
		
		MongoCollection<Document> timeSlotColl = db.getCollection("TimeSlots");
=======
>>>>>>> master

	public boolean pushRoomDocument(String roomName, String capacity) {

<<<<<<< HEAD
        Document newTimeSlotDoc = new Document(
				"startTime" , startTime
			).append(
				"endTime" , endTime
			);
=======
		int id = nextRoomId;
		nextRoomId++;
>>>>>>> master

		// check and convert ints

		// if (!isNewRoom(roomName, roomColl)) {
		// 	return false;
		// }

		Document newRoomDoc = new Document("roomID", id).append("roomName", roomName).append("capacity", capacity);

		return pushDocument("Room" , newRoomDoc);
	}

	public boolean pushTimeslotDocument(String startTime, String endTime) {

		int id = nextTimeId;
		nextTimeId++;

		// if (!isNewTimeSlot(startTime_int, endTime_int, timeSlotColl)) {
		// 	return false;
		// }

		Document newTimeSlotDoc = new Document("timeSlotId", id).append("startTime", startTime).append("capacity",
				endTime);

		return pushDocument("TimeSlot" , newTimeSlotDoc);
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
			if(name != null && name != "") {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("roomName", name)));
			}
			if(capacity != null && capacity != "") {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("capacity", capacity)));
			}

		}catch (Exception e) {
			return false;
		}
		return true;
	}
	private boolean updateTime(Document doc,String startTime,String endTime, String collectionName) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		try {
			if(startTime != null && startTime != "") {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("startTime", startTime)));
			}
			if(endTime != null && endTime != "") {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("endTime", endTime)));
			}

		}catch (Exception e) {
			return false;
		}
		return true;
	}
	private boolean updateSession(Document doc,String sessionName, String sessionID, String speakerName, String linkedRoomID, String linkedTimeID, String collectionName) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		try {
			if(sessionName != null && sessionName != "") {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("sessionName", sessionName)));
			}
			if(speakerName != null && speakerName != "") {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("speakerName", speakerName)));
			}
			if(sessionID != null && sessionID != "") {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("sessionID", sessionID)));
			}
			if(linkedRoomID != null && linkedRoomID != "") {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("roomId", linkedRoomID)));
			}
			if(linkedTimeID != null && linkedTimeID !=  "") {
				collection.updateOne(
					    new BasicDBObject(doc),
					    new BasicDBObject("$set", new BasicDBObject("timeSlotId", linkedTimeID)));
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

	public boolean updateSession(String sessionObjectID, String sessionName, String sessionID, String speakerName, String linkedRoomID, String linkedTimeID) {
		return updateSession(new Document("_id", new ObjectId(sessionObjectID)), sessionName, sessionID, speakerName, linkedRoomID, linkedTimeID,  "Sessions" );
	}
	public boolean updateTime(String timeId,String startTime, String endTime) {
		return updateTime(new Document("_id", new ObjectId(timeId)), startTime, endTime, "TimeSlots" );
	}

}
