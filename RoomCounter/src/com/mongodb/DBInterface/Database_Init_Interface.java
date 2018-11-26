package com.mongodb.DBInterface;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database_Init_Interface extends DatabaseEditableInterface{
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

	public boolean pushRoomDocument(String roomName, String capacity) {

		int id = nextRoomId;
		nextRoomId++;

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
	
	
	

}
