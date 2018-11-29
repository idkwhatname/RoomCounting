package com.mongodb.DBInterface;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database_Report_Interface extends DatabaseInterface {

	private String DB_NAME = "Database";
	private MongoDatabase db;

	public Database_Report_Interface(String url, int port) {
		db = getConnection(url, port).getDatabase(DB_NAME);
	}

	public Database_Report_Interface() {
		this("localhost", 27017);
	}

	private static MongoClient getConnection(String url, int port_num) {

		MongoClient mongoClntObj = new MongoClient(url, port_num);
		return mongoClntObj;
	}

	public boolean updateSessionCounts(String sessionID, String countType, String count) {

		Document doc = new Document("_id",new ObjectId(sessionID));
		MongoCollection<Document> collection = db.getCollection("Sessions");
		try {

			if (count != null) {
				if (countType.equalsIgnoreCase("beginning"))
					collection.updateOne(new BasicDBObject(doc),
							new BasicDBObject("$set", new BasicDBObject("beginCount", count)));
				if (countType.equalsIgnoreCase("middle"))
					collection.updateOne(new BasicDBObject(doc),
							new BasicDBObject("$set", new BasicDBObject("middleCount", count)));
				if (countType.equalsIgnoreCase("end"))
					collection.updateOne(new BasicDBObject(doc),
							new BasicDBObject("$set", new BasicDBObject("endCount", count)));
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean updateSessionCounts(String roomName, String timeSlotId, String countType, String count,
			String collectionName) {

		Document doc = new Document("_id",new ObjectId(timeSlotId));
		MongoCollection<Document> collection = db.getCollection(collectionName);
		try {

			if (count != null) {
				if (countType.equalsIgnoreCase("beginning"))
					collection.updateOne(new BasicDBObject(doc),
							new BasicDBObject("$set", new BasicDBObject("beginCount", count)));
				if (countType.equalsIgnoreCase("middle"))
					collection.updateOne(new BasicDBObject(doc),
							new BasicDBObject("$set", new BasicDBObject("middleCount", count)));
				if (countType.equalsIgnoreCase("end"))
					collection.updateOne(new BasicDBObject(doc),
							new BasicDBObject("$set", new BasicDBObject("endCount", count)));
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}
}