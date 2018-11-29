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

@WebServlet("/modifyTimeSlot")
public class ModifyTime extends HttpServlet {
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws
			ServletException, IOException {
		

			
		 String startTime = request.getParameter("start");
		 String endTime = request.getParameter("end");
		 
		 
		 String button = request.getParameter("myButton");
		 String timeSelect = request.getParameter("timeSelect");

		 
			MongoClient mongo = (MongoClient) request.getServletContext()
					.getAttribute("MONGO_CLIENT");
			
			//Test prints
			
			//ADDING THE TIME SLOT TO THE DATABASE
			
			Database_Init_Interface dbi = new Database_Init_Interface();
			
			if(button.equals("Submit")) {
				//ADDING SESSION TO DATABASE
				if(startTime != "" && endTime != "")

		        dbi.pushTimeslotDocument(startTime, endTime);
				
			}else if(button.equals("delete")) {
				if(timeSelect != null) {
					//DELETING SESSION FROM DATABASE
					dbi.deleteTimeSlot(timeSelect);	
				}
	
			}else if(button.equals("modify")) {
				if(timeSelect != null) {
				//MODIFY SESSION FROM DATABASE
				
				dbi.updateTime(timeSelect, startTime, endTime);
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
