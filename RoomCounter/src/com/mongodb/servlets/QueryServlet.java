package com.mongodb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.DBInterface.Database_Init_Interface;
import com.mongodb.client.MongoClient;

@WebServlet("/query")
public class QueryServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -838118330214813277L;

	@Override
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)

	        throws ServletException, IOException {
		MongoClient mongo = (MongoClient) request.getServletContext()
				.getAttribute("MONGO_CLIENT");
		
		Database_Init_Interface dbi = new Database_Init_Interface();
		
		//SHOWING THE LSIT ON THE WEBSITE
		request.setAttribute("timeSlots", dbi.getTimeSlotList());
		request.setAttribute("rooms", dbi.getRoomList());
		request.setAttribute("sessions", dbi.getSessionList());
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Query.jsp");
		rd.forward(request, response);
		
	       
	    }
}
