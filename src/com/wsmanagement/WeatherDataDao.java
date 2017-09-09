package com.wsmanagement;

import java.time.Instant;
import java.util.List;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

public class WeatherDataDao {
	public int addData(WeatherData newData) {
		saveData(newData);
		return 1;
	}
	
	private void saveData(WeatherData newData)
	{
		MongoCollection<Document> mc = MongoConnection.getInstance().getConnection("weatherdata");
		Document doc = new Document("outsideTemp", newData.getOutTemp()).append("insideTemp", newData.getInTemp()).append("time", newData.getTime().toString());
		mc.insertOne(doc);
	}
	
	public WeatherData getWeatherData() {
		WeatherData wd = null;
		MongoCollection<Document> mc = MongoConnection.getInstance().getConnection("weatherdata");
		Document doc = mc.find().sort(new BasicDBObject("time",-1)).first();
		if(doc!=null)
		{
			//Instant time = doc.get("time", Instant.class);
			double outTemp = doc.get("outsideTemp", Double.class);
			double inTemp = doc.get("insideTemp", Double.class);
			wd = new WeatherData((float)outTemp, (float)inTemp);
			//wd.setTime(time);
		}
		return wd;
	}
}
