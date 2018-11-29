<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Query</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/main.css">
</head>

<body>
<div class="container col-md-10 col-md-offset-1 col-lg-6 col-lg-offset-3">
  <h2>Query Database</h2>
	
  <div class="container-fluid container-1">
    <form class="forms col-xs-12 col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2" >
      <h4 class="large-heading"> <span class="heading-text-1">Add Time Slot:</span> </h4>
		<br>
      <span class="text-element small-heading">Session Name:</span>
      <select class="room">
 				<c:forEach items="${requestScope.sessions}" var="sessions">
               <option value="${sessions.sessionID}">Session Name: ${sessions.sessionName}, Speaker: ${session.speaker}</option> <br>
               </c:forEach>
      </select>
      <br>
      <br>
      <span class="text-element small-heading">Room Name:</span>
      <select class="room">
        <c:forEach items="${requestScope.rooms}" var="rooms">
           <option value="${room.roomID}">Room Name: ${room.name}, Capacity: ${room.capacity}</option><br>        
           </c:forEach>
      </select>
      <br>
      <br>
      <span class="text-element small-heading">Time Slot:</span>
      <select
					class="room">
      <c:forEach items="${requestScope.timeSlots}" var="timeSlots">
           <option value="${timeSlots.timeSlotID}">Start Time: ${timeSlots.startTime}, End Time: ${timeSlots.endTime}</option> <br>
           </c:forEach>
      </select>
	  <br>
      <br>
      <button type="button" class="btn">Submit</button>
    </form>
  </div>
</div>
</body>
</html>
