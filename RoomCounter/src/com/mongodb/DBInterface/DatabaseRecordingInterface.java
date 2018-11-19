package com.mongodb.DBInterface;


import java.util.List;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.models.*;

public class DatabaseRecordingInterface extends DatabaseInterface {
	
	private MongoDatabase db;
	
	public DatabaseRecordingInterface(String url , int port) {
		super(url , port);
		db = super.getDB();
	}

	public DatabaseRecordingInterface(){
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

	private boolean pushSessionDocument(String sessionName, String id, String speaker, String roomId,
			String timeSlotId , String countBeg , String countMid , String countEnd) {

		Document newSessionDoc = new Document("sessionID", id).append("sessionName", sessionName)
				.append("speakerName", speaker).append("roomId", roomId).append("timeSlotId", timeSlotId)
				.append("countBeg", countBeg).append("countMid", countMid).append("countEnd", countEnd);

		return pushDocument("Session" , newSessionDoc);
	}
	
	private boolean pushSessionDocument(Session s) {
		return pushSessionDocument(s.getSessionName() , s.getSessionID() , s.getSpeaker() , s.getRoom() , s.getTimeSlot() , s.getCountBeg() , s.getCountMid() , s.getCountEnd());
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

	private boolean deleteSessionById(String sessionId) {
		return deleteDocument(new Document("sessionId", sessionId) , "Session");
	}
	
	private boolean deleteSessionByName(String sessionName) {
		return deleteDocument(new Document("sessionName", sessionName) , "Session");
	}
	
	private boolean deleteSessionByObj(Session session) {
		return deleteDocument(new Document("sessionId" , session.getSessionID()) , "Session");
	}
	
	
	public boolean appendBeginningCount(String sessionId , String count) {
		List<Session> sessionList = super.getSessionList();
		Session sessionToAppend;
		for (int i = 0; i < sessionList.size(); i++) {
			if(sessionId.trim().equals(sessionList.get(i).getSessionID())) {
				sessionToAppend = sessionList.get(i);
				if(!deleteSessionByObj(sessionToAppend)) {
					return false;
				}
				if(!pushSessionDocument(sessionToAppend)) {
					return false;
				}
				return true;
			}
		}
		
		return false;
		
	}

}
