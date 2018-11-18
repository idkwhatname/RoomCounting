package com.mongodb.DBInterface;

import java.sql.Time;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.models.*;

public class DatabaseInterface {

	private String DB_NAME = "";
	private MongoDatabase db;

	public DatabaseInterface(String url , int port){
		db = getConnection(url , port).getDatabase(DB_NAME);
	}

	public DatabaseInterface(){
		this("localhost" , 27017);
	}

	public MongoDatabase getDB(){
		return db;
	}

	private static MongoClient getConnection(String url , int port_num) {
        
        MongoClient mongoClntObj = new MongoClient(url, port_num);
        return mongoClntObj;
	}
	
	public List<Room> getRoomList(){

		MongoCollection<Document> collection = db.getCollection("Room");
		List<Room> rooms = new ArrayList<Room>();
		MongoCursor<Document> cursor = collection.find().iterator();
		while(cursor.hasNext()) {
			Document doc = cursor.next();
			Room r = ModelConverter.toRoom(doc);
			rooms.add(r);
		}
		
		
		return rooms;

	}


	public List<TimeSlot> getTimeSlotList() {
		
		MongoCollection<Document> collection = db.getCollection("TimeSlot");
		List<TimeSlot> timeslots = new ArrayList<TimeSlot>();
		MongoCursor<Document> cursor = collection.find().iterator();
		while(cursor.hasNext()) {
			Document doc = cursor.next();
			TimeSlot t = ModelConverter.toTimeSlot(doc);
			timeslots.add(t);
		}
		
		
		return timeslots;
	}

	public List<Session> getSessionList() {
		
		MongoCollection<Document> collection = db.getCollection("Session");
		List<Session> sessions = new ArrayList<Session>();
		MongoCursor<Document> cursor = collection.find().iterator();
		while(cursor.hasNext()) {
			Document doc = cursor.next();
			Session s = ModelConverter.toSession(doc);
			sessions.add(s);
		}
		
		
		return sessions;
	}
	
	
}
