package com.wsmanagement;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/Management")
public class WeatherDataService {
	WeatherDataDao wdd = new WeatherDataDao();
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";
	
	@POST
	@Path("/add_data")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createEvent(@FormParam("outsideTemp") float outsideTemp, @FormParam("insideTemp") float insideTemp,
			@Context HttpServletResponse servletResponse)
					throws IOException {
		WeatherData wd = new WeatherData(outsideTemp, insideTemp);
		int result = 0;
		result = wdd.addData(wd);
		if (result == 1) {
			return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	}
	
	@GET
	@Path("/view")
	@Produces(MediaType.TEXT_HTML)
	public String getTickets() {
		WeatherData wd = wdd.getWeatherData();
		return "<h1>"+wd.getOutTemp()
				+ "<br>"+ wd.getInTemp()+ "</h1>";
	}
}
