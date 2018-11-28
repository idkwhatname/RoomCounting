package com.mongodb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.models.Room;
import com.mongodb.models.Session;
import com.mongodb.models.TimeSlot;
import com.mongodb.utilities.Util;
import com.mongodb.DBInterface.*;

@WebServlet("/modifySession")
public class ModifySession extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws
			ServletException, IOException {
		
		
			
		 String sessionName = request.getParameter("session-name");
		 String sessionID = request.getParameter("session-number");
		 String speakerName = request.getParameter("speaker");
		 String linkedRoomID = request.getParameter("roomSelect");
		 String linkedTimeID = request.getParameter("timeSlotSelect");
		 
		 String button = request.getParameter("myButton");
		 String sessionSelect = request.getParameter("sessionSelect");

			
			if(linkedRoomID == null) {
				linkedRoomID = "";
			}
			if(linkedTimeID == null) {
				linkedTimeID = "";
			}
			
			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			
			//Test prints
			System.out.println(sessionName);
			System.out.println(sessionID);
			System.out.println(speakerName);
			
	        Database_Init_Interface dbi = new Database_Init_Interface();
			
			if(button.equals("Submit")) {
				//ADDING SESSION TO DATABASE
				if(sessionName != "" && sessionID != "" && speakerName != "") {

			        dbi.pushSessionDocument(sessionName, sessionID, speakerName, linkedRoomID, linkedTimeID);
					
				}

			}else if(button.equals("delete")) {
				if(sessionSelect != null) {
					//DELETING SESSION FROM DATABASE
					dbi.deleteSession(sessionSelect);	
				}
	
			}else if(button.equals("modify")) {
				if(sessionSelect != null) {
				//MODIFY SESSION FROM DATABASE
				dbi.updateSession(sessionSelect, sessionName, sessionID, speakerName, linkedRoomID, linkedTimeID);
				}
			}	        
			
			//GETTING ALL THE SESSIONS FROM THE DATBASE
			Util util = new Util(mongo, "Sessions");
			List<Session> AllSessions = util.readAllSessions();
			
			
			//GETTING ALL THE TIME SLOTS FROM THE DATABASE
			Util utilTime = new Util(mongo, "TimeSlots");
			List<TimeSlot> AllTimeSlots = utilTime.readAllTimeSlots();
			
			//GETTING ALL THE ROOMS FROM THE DATBASE
			Util utilRoom = new Util(mongo, "Rooms");
			List<Room> AllRooms = utilRoom.readAllRooms();

			
			//SHOWING THE LIST ON THE WEBSITE
			request.setAttribute("timeSlots", AllTimeSlots);
			request.setAttribute("rooms", AllRooms);
			request.setAttribute("sessions", AllSessions);
	        	        
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Creation Menu.jsp");
			rd.forward(request, response);

	}

}
