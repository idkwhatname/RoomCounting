package com.mongodb.servlets;

import java.io.IOException;
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

@WebServlet("/addTimeSlot")
public class AddTime extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws
			ServletException, IOException {
			
		 String startTime = request.getParameter("start");
		 String endTime = request.getParameter("end");

		 
		if(startTime == null || endTime == null) {
			//error
			System.out.println("Error adding, values null");
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/Creation Menu.jsp");
			rd.forward(request, response);
		}else {
			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			
			//Test prints
			System.out.println(startTime);
			System.out.println(endTime);
			
			
			//ADDING THE TIME SLOT TO THE DATABASE
			
			Database_Init_Interface dbi = new Database_Init_Interface();
			dbi.pushTimeslotDocument(startTime, endTime);
			
			
			//SHOWING THE LSIT ON THE WEBSITE
			request.setAttribute("timeSlots", dbi.getTimeSlotList());
			request.setAttribute("rooms", dbi.getRoomList());
			request.setAttribute("sessions", dbi.getSessionList());
	        

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Creation Menu.jsp");
			rd.forward(request, response);
			
		}
		
	
	}

}
