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

@WebServlet("/addRoomSlot")
public class AddRoom extends HttpServlet {
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws
			ServletException, IOException {
			
		 String roomName = request.getParameter("room-name");
		 String capacity = request.getParameter("capacity");

		 
		if(roomName == null || capacity == null) {
			//error
			System.out.println("Error adding, values null");
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/Creation Menu.jsp");
			rd.forward(request, response);
		}else {
			
			//Test prints
			System.out.println(roomName);
			System.out.println(capacity);
			
			
			//ADDING ROOM TO DATABASE
			Database_Init_Interface dbi = new Database_Init_Interface();
			dbi.pushRoomDocument(roomName, capacity);
			
			//SHOWING THE LSIT ON THE WEBSITE
			request.setAttribute("timeSlots", dbi.getTimeSlotList());
			request.setAttribute("rooms", dbi.getRoomList());
			request.setAttribute("sessions", dbi.getSessionList());

			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Creation Menu.jsp");
			rd.forward(request, response);
			
		}
		
	
	}

}
