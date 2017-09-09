package com.wsmanagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
	private static MongoConnection instance = null;
	private final String user;
	private final String password;
	private final String address;
	private final String dbName;
	private MongoClientURI connectionString= null;
	private MongoClient mongoClient = null;
	private MongoDatabase database = null;

	private MongoConnection() {
		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("wsmanagement.properties");
		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user = prop.getProperty("DBUSER");
		password = prop.getProperty("DBPASSWORD");
		address = prop.getProperty("DBADDRESS");
		dbName = prop.getProperty("DATABASE");
		System.out.println(address);
		connectionString = new MongoClientURI(
				"mongodb://" + user + ":" + password + "@" + address + "/" + dbName);
		mongoClient = new MongoClient(connectionString);
		database = mongoClient.getDatabase(dbName);
	}
	
	public static MongoConnection getInstance() {
		if(instance == null) {
			instance = new MongoConnection();
		}
		return instance;
	}

	public MongoCollection<Document> getConnection(String type) {
		if (type == "events") {
			return database.getCollection("events");
		} else if (type == "tickets") {
			return database.getCollection("tickets");
		} else if (type == "weatherdata") {
			return database.getCollection("weatherdata");
		}
		return null;
	}
	
	public void closeConnection() {
		mongoClient.close();
	}

}
