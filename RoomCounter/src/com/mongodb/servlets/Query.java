package com.mongodb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.DBInterface.*;

/**
 * Servlet implementation class Query
 */
@WebServlet("/Query")
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DatabaseInterface dbi = new DatabaseInterface();
		
		String button = request.getParameter("myButton");
		
		String timeSelect = request.getParameter("timeSelect");
		String sessionSelect = request.getParameter("sessionSelect");
		String roomSelect = request.getParameter("roomSelect");
		
    	List<Session> queryResults = new List<Session>();
		
		if(button.equals("Submit")) {
			if(sessionSelect != "") {
				/*
				* Not sure which to use here, wrote a few methods but don't know what gets stored in "select" vars
				*/
				//queryResults = dbi.getSessionsFromName(sessionSelect);
				queryResults = dbi.getSessionsFromNumber(sessionSelect);
			}
			else if (roomSelect != "" || timeSelect != ""){
				queryResults = dbi.getSessionsFromRoomAndTime(roomSelect , timeSelect);
			}


		}
				
		//TODO: send query results to frontend
		
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/QueryUI.jsp");
		rd.forward(request, response);
		
	}

}
