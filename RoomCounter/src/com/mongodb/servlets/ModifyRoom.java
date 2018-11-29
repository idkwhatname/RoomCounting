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
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.models.Room;
import com.mongodb.models.Session;
import com.mongodb.models.TimeSlot;
import com.mongodb.models.ModelConverter;

import com.mongodb.DBInterface.*;

import com.mongodb.utilities.Util;

@WebServlet("/modifyRoomSlot")
public class ModifyRoom extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7681273591309361743L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String button = request.getParameter("myButton");
		 String roomSelect = request.getParameter("roomSelect");
		 
		 String roomName = request.getParameter("room-name");
		 String capacity = request.getParameter("capacity");

		 
			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			
			//Test prints
			System.out.println(roomName);
			System.out.println(capacity);
			
			Database_Init_Interface dbi = new Database_Init_Interface();

			// This was deleted by Kevin's branch-- I've commented it out while resolving conflicts.
			//dbi.pushRoomDocument(roomName, capacity);			
			//BUTTON IF STATEMENT
			if(button.equals("Submit")) {
				if(roomName != "" || capacity != "") {
					//ADDING ROOM TO DATABASE
					dbi.pushRoomDocument(roomName, capacity);
				}
			}
			else if(button.equals("delete")) {
				if(roomSelect != null) {
					//DELETING ROOM FROM DATABASE
					dbi.deleteRoom(roomSelect);	
				}
	
			}else if(button.equals("modify")) {
				if(roomSelect != null) {
				//MODIFY ROOM FROM DATABASE			
				dbi.updateRoom(roomSelect, roomName, capacity);
				
				}
			}
			

			
			//SHOWING THE LSIT ON THE WEBSITE
			request.setAttribute("timeSlots", dbi.getTimeSlotList());
			request.setAttribute("rooms", dbi.getRoomList());
			request.setAttribute("sessions", dbi.getSessionList());
	        
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Creation Menu.jsp");
			rd.forward(request, response);
			
		
		
	
	}

}
