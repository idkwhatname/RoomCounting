<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Initialization UI</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/main.css">

	
</head>

<!--Title-->
<body>

         <!--  URLs for form methods -->
         	
         	
         	<c:url value="/modifyTimeSlot" var="modifyTimeSlotsURL"></c:url>
         	<c:url value="/modifyRoomSlot" var="modifyRoomSlotsURL"></c:url>
         	<c:url value="/modifySession" var="modifySessionURL"></c:url>

<div class="container">
  <h2>Initialize Database</h2>
  
 <ul class="nav nav-tabs">
    <li class="active"><a data-toggle="tab" href="#roomnav">Add Room</a></li>
    <li><a data-toggle="tab" href="#timeslotnav">Add TimeSlot</a></li>
    <li><a data-toggle="tab" href="#sessionnav">Add Session</a></li>
  </ul>

  <div class="tab-content">
    <div id="timeslotnav" class="tab-pane fade">
      <div class="container-fluid container-1">
			<form class="forms col-xs-12 col-sm-6" id="timeSlotForm" action='<c:out value="${modifyTimeSlotsURL}"></c:out>'
				method="POST">
				<h4 class="large-heading">
					<span class="heading-text-1">Add Time Slot:</span>
				</h4>
				<span class="text-element small-heading">Start Time:</span>
				<input name="start" type="text"> 
				<br>
				<br>
				<span class="text-element small-heading">End Time:</span> 
				<input name="end" type="text">
				<br>
				<br>
				<button type="submit" name="myButton" class="btn" value="Submit">Submit</button>
			</form>
			<div class="current-db-container col-xs-12 col-sm-6">
				
				<select name="timeSelect" id="timeSelect" class="dynam-select current-db" multiple>
				<c:forEach items="${requestScope.timeSlots}" var="timeSlots">
                <option value="${timeSlots.timeSlotID}">Start Time: ${timeSlots.startTime}, End Time: ${timeSlots.endTime}</option> <br>           
                </c:forEach>
                </select>
				<br>
				<button class="btn" name="myButton" type="submit" value="modify">Modify</button>
				<button class="btn" name="myButton" type="submit" value="delete">Delete</button>
			</div>
		</div>
    </div>
    <div id="roomnav" class="tab-pane fade in active">
      <div class="container-fluid container-3">
			  <form class="col-xs-12 col-sm-6 forms" id="roomForm" method="POST" action='<c:out value="${modifyRoomSlotsURL}"></c:out>'>
				<h4 class="large-heading">
					<span class="heading-text-1">Add Room:</span>
				</h4>
				<span class="text-element small-heading">Room Name:</span>
				<input name="room-name" type="text"> 
				<br>
				<br>
				<span class="text-element small-heading">Capacity:</span> 
				<input name="capacity" type="text">
				<br>
				<br>
				<button type="submit" name="myButton" class="btn" value="Submit">Submit</button>
			</form>
			<div class="current-db-container col-xs-12 col-sm-6">
				<select name="roomSelect" id="roomSelect" class="dynam-select current-db" multiple>
                <c:forEach items="${requestScope.rooms}" var="room">
                <option value="${room.roomID}">Room Name: ${room.name}, Capacity: ${room.capacity} </option><br>          
                </c:forEach>
                </select>
				<br>
				<button class="btn" name="myButton" type="submit" value="modify">Modify</button>
				<button class="btn" name="myButton" type="submit" value="delete">Delete</button>
			</div>
		</div>
    </div>
    <div id="sessionnav" class="tab-pane fade">
      <div class="container-fluid container-1">
			<form class="forms col-xs-12 col-sm-6" id="SessionForm" method="POST"
				action='<c:out value="${modifySessionURL}"></c:out>'>
				<h4 class="large-heading">Add Session:</h4>
				<span class="text-element small-heading">Session Name:</span>
				<input name="session-name" type="text"> 
				<br>
				<br>
				<span class="text-element small-heading">Session Number:</span> 
				<input name="session-number" type="number"> 
				<br>
				<br>
				<span class="text-element small-heading">Speaker:</span> 
				<input name="speaker" type="text"> 
				<br>
				<br>
				<span class="text-element small-heading">Date:</span> 
				<input name="date" type="date"> 
				<br>
				<br>
				<span class="text-element small-heading">Time Slot:</span> 
				<select class="select drop-down-1" name="timeSlotSelect" id="timeSlotSelect">
					
						<c:forEach items="${requestScope.timeSlots}" var="timeSlots">
               			<option value="${timeSlots.timeSlotID}">Start Time: ${timeSlots.startTime}, End Time: ${timeSlots.endTime}</option> <br>           
                		</c:forEach>
                		
					</select> 
				<br>
				<br>
				<span class="text-element small-heading">Room:</span> 
				<select class="room" name="roomSelect" id="roomSelect">
					
					<c:forEach items="${requestScope.rooms}" var="room">
                	<option value="${room.roomID}">Room Name: ${room.name}, Capacity: ${room.capacity}</option><br>          
               		</c:forEach>
               		 
					</select> <br> <br>
				<button type="submit" name="myButton" class="btn" value="Submit">Submit</button>
			</form>
			<div class="current-db-container col-xs-12 col-sm-6">
				<select name="sessionSelect" id="sessionSelect" class="dynam-select current-db" multiple>
				<c:forEach items="${requestScope.sessions}" var="session">
               	<option value="${session.sessionID}">Session Name: ${session.sessionName}, Speaker: ${session.speaker}</option> <br>           
                </c:forEach>
                </select>
				<button class="btn" name="myButton" type="submit" value="modify">Modify</button>
				<button class="btn" name="myButton" type="submit" value="delete">Delete</button>
			</div>
		</div>
  </div>
</div>
</div>


</body>
</html>