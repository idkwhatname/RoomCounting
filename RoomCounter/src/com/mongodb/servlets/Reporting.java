package com.mongodb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Reporting
 */
@WebServlet("/Reporting")
public class Reporting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reporting() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 String button = request.getParameter("myButton");
		 String sessionSelect = request.getParameter("sessionSelect");
		 
		 String startCount = request.getParameter("startCount");
		 String middleCount = request.getParameter("middleCount");
		 String endCount = request.getParameter("endCount");
		 
		 
		 if(button.equals("Submit")) {
			 if(sessionSelect != null) {
				 if(startCount != "" || middleCount != "" || endCount != "") {
					 //CALL UPDATE SESSION COUNTS
				 }
			 }			 
		 }
		
		
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/ReportingUI.jsp");
		rd.forward(request, response);
	}

}
