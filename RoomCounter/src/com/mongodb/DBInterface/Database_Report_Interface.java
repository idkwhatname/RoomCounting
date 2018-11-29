package com.mongodb.DBInterface;

import org.bson.Document;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;

public class Database_Report_Interface extends DatabaseInterface{
	
	public Database_Report_Interface(String url , int port) {
		db = getConnection(url , port).getDatabase(DB_NAME);
	}
	
	public Database_Report_Interface(){
		this("localhost" , 27017);
	}
		
	public boolean updateSessionCounts(String sessionName, String countType, String count, String collectionName) {
		
		Document doc = new Document();
		MongoCollection<Document> collection = db.getCollection(collectionName);
		try {

			if (count != null) {
				if(countType.equalsIgnoreCase("beginning"))
					collection.updateOne(new BasicDBObject(doc), new BasicDBObject("$set", new BasicDBObject("beginCount", count)));
				if(countType.equalsIgnoreCase("middle"))
					collection.updateOne(new BasicDBObject(doc), new BasicDBObject("$set", new BasicDBObject("middleCount", count)));
				if(countType.equalsIgnoreCase("end"))
					collection.updateOne(new BasicDBObject(doc), new BasicDBObject("$set", new BasicDBObject("endCount", count)));
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}	
	
	public boolean updateSessionCounts(String roomName, String timeSlotId, String countType, String count, String collectionName) {
		
		Document doc = new Document();
		MongoCollection<Document> collection = db.getCollection(collectionName);
		try {

			if (count != null) {
				if(countType.equalsIgnoreCase("beginning"))
					collection.updateOne(new BasicDBObject(doc), new BasicDBObject("$set", new BasicDBObject("beginCount", count)));
				if(countType.equalsIgnoreCase("middle"))
					collection.updateOne(new BasicDBObject(doc), new BasicDBObject("$set", new BasicDBObject("middleCount", count)));
				if(countType.equalsIgnoreCase("end"))
					collection.updateOne(new BasicDBObject(doc), new BasicDBObject("$set", new BasicDBObject("endCount", count)));
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}	
}