package com.mongodb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		
		String button = request.getParameter("myButton");
		
		String timeSelect = request.getParameter("timeSelect");
		String sessionSelect = request.getParameter("sessionSelect");
		String roomSelect = request.getParameter("roomSelect");
		
		
		
		if(button.equals("Submit")) {
			if(timeSelect != "" || sessionSelect != "" || roomSelect != "") {
				//CALL QUERY BASED ON GIVEN SELECTIONS
			}
		}
				
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/QueryUI.jsp");
		rd.forward(request, response);
		
	}

}
