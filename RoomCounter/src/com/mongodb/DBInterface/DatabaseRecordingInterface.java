package com.mongodb.DBInterface;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseRecordingInterface extends DatabaseEditableInterface {
	private MongoDatabase db;

	public DatabaseRecordingInterface(String url , int port) {
		super(url , port);
		db = super.getDB();
	}

	public DatabaseRecordingInterface(){
		super();
		db = super.getDB();
	}
}
