package com.mongodb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mongodb.client.MongoClient;
import com.mongodb.models.Room;
import com.mongodb.models.Session;
import com.mongodb.models.TimeSlot;
import com.mongodb.utilities.Util;
import com.mongodb.DBInterface.*;

public class MainServlet extends HttpServlet {
	
	public MainServlet() {
		
	}
	
	@Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)

	        throws ServletException, IOException {
		Database_Init_Interface dbi = new Database_Init_Interface();
		
		//SHOWING THE LSIT ON THE WEBSITE
		request.setAttribute("timeSlots", dbi.getTimeSlotList());
		request.setAttribute("rooms", dbi.getRoomList());
		request.setAttribute("sessions", dbi.getSessionList());
        
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Creation Menu.jsp");
		rd.forward(request, response);
		
	       
	    }
}
