package com.wsmanagement;

import java.io.Serializable;
import java.time.Instant;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "weatherData")
public class WeatherData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private float outTemp;
	private float inTemp;
	private Instant time;
	
	public WeatherData() {}
	
	public WeatherData(float outTemp, float inTemp)
	{
		this.outTemp = outTemp;
		this.inTemp = inTemp;
		this.time = Instant.now();
	}

	public float getOutTemp() {
		return outTemp;
	}

	@XmlElement
	public void setOutTemp(float outTemp) {
		this.outTemp = outTemp;
	}

	public float getInTemp() {
		return inTemp;
	}

	@XmlElement
	public void setInTemp(float inTemp) {
		this.inTemp = inTemp;
	}
	
	public Instant getTime()
	{
		return time;
	}
	
	@XmlElement
	public void setTime(Instant time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(inTemp);
		result = prime * result + Float.floatToIntBits(outTemp);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeatherData other = (WeatherData) obj;
		if (Float.floatToIntBits(inTemp) != Float.floatToIntBits(other.inTemp))
			return false;
		if (Float.floatToIntBits(outTemp) != Float.floatToIntBits(other.outTemp))
			return false;
		return true;
	}
}
