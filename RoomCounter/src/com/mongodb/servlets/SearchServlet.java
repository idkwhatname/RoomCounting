package com.mongodb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.DBInterface.Database_Init_Interface;
import com.mongodb.DBInterface.Database_Report_Interface;
import com.mongodb.client.MongoClient;
import com.mongodb.models.Room;
import com.mongodb.models.Session;
import com.mongodb.models.TimeSlot;
import com.mongodb.utilities.Util;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = -1980116968782840913L;
	private List<Session> listSessions;
	private List<Room> listRoom;
	private List<TimeSlot> listTimeSlots;
	private Database_Report_Interface dbi = new Database_Report_Interface();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");

		request.setAttribute("rooms", dbi.getRoomList());
		request.setAttribute("sessions", dbi.getSessionList());
		request.setAttribute("timeslots", dbi.getTimeSlotList());

		// System.out.println(listSessions.get(0).getSessionID());
		RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
		dispatcher.forward(request, response);
	}

	private String getSessionBasedOnRoom(String _roomID) {
		String roomID = "";
		for (int i = 0; i < listSessions.size(); i++) {
			if (listSessions.get(i).getRoom().equals(_roomID))
				roomID = listSessions.get(i).getSessionID();
		}

		return roomID;
	}
	
	private String getSessionBasedOnRoomAndTime(String _roomID, String _timeSlotID) {
		String roomID = "";
		for (int i = 0; i < listSessions.size(); i++) {
			if (listSessions.get(i).getRoom().equals(_roomID))
				roomID = listSessions.get(i).getSessionID();
		}

		return roomID;
	}

	private String getSessionBasedOnTime(String _timeSlotID) {
		String timeSlotID = "";
		for (int i = 0; i < listSessions.size(); i++) {
			if (listSessions.get(i).getTimeSlot().equals(_timeSlotID))
				timeSlotID = listSessions.get(i).getSessionID();
		}

		return timeSlotID;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String count = request.getParameter("count");
		String radio = request.getParameter("countPeriod");
		String sessionName = request.getParameter("sessionName");
		String roomName = request.getParameter("roomName");
		String timeSlotName = request.getParameter("timeSlotName");

		System.out.println(sessionName);
		System.out.println(getSessionBasedOnRoom(roomName));
		System.out.println(getSessionBasedOnRoom(timeSlotName));

		if (sessionName != null) {
			dbi.updateSessionCounts(sessionName , radio , count);
		}	

		else if (timeSlotName != null && roomName != null){
			dbi.updateSessionCounts(dbi.getSessionsFromRoomAndTime(roomName , timeSlotName).get(0).getSessionName() , radio , count);
		}

		System.out.println(count);
	}
}
