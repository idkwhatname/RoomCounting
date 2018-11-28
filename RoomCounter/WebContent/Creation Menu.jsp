<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Initialization UI</title>
<link rel="stylesheet" href="css/bootstrap4.min.css">
<link rel="stylesheet" href="css/wireframe-theme.min.css">
<link rel="stylesheet" href="css/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"
	type="text/javascript"></script>
<!--  <script src="dataFunctions.js" type="text/javascript"></script>-->
</head>
<body>
<!--Title-->
<div class="row">
	<div class="col-xs-12 col-sm-12 col-lg-12 offset-lg-2">
		<h1 class="heading-1">Initialize Database</h1>
	</div>
	<div class="col-xs-12 col-sm-12 col-lg-12 offset-lg-2">
		<span class="text-element small-heading">Name: </span> <input
			name="name" type="text" form="timeSlotForm roomForm SessionForm"
			required>
	</div>
</div>

<br>
<br>

         <!--  URLs for form methods -->
         	
         	
         	<c:url value="/modifyTimeSlot" var="modifyTimeSlotsURL"></c:url>
         	<c:url value="/modifyRoomSlot" var="modifyRoomSlotsURL"></c:url>
         	<c:url value="/modifySession" var="modifySessionURL"></c:url>
         	
         

<div class="row">

	<!--first column-->
	<div
		class="col-xs-12 col-sm-12 col-md-12 col-lg-8 offset-lg-2 column-2">
		<!-- time slot form -->
		<div class="container-fluid container-2">
			<form class="forms" id="timeSlotForm" action='<c:out value="${modifyTimeSlotsURL}"></c:out>'
				method="POST">
				<h4 class="large-heading">
					<span class="heading-text-1">Add Time Slot:</span>
				</h4>
				<span class="text-element small-heading">Start Time:<br></span>
				<input name="start" type="time"> <span
					class="text-element small-heading">End Time:<br></span> <input
					name="end" type="time"> <br> <br>
				<button type="submit" name="myButton" class="btn" value="Submit">Submit</button>

			<div class="current-db-container">
				<div id="curTimeSlots" class="current-db" style="height: 225px;">
				<select name="timeSelect" id="timeSelect" class="dynam-select" multiple>
				<c:forEach items="${requestScope.timeSlots}" var="timeSlots">
                <option value="${timeSlots.timeSlotID}">Start Time: ${timeSlots.startTime}, End Time: ${timeSlots.endTime}</option> <br>           
                </c:forEach>
                </select>
				</div>
				<br>
				<button class="btn" name="myButton" type="submit" value="modify">Modify</button>
				<button class="btn" name="myButton" type="submit" value="delete">Delete</button>
			</div>
			</form>

			
		</div>


		<!-- room form -->
		<div class="container-fluid container-3">
			<form class="forms" id="roomForm" action='<c:out value="${modifyRoomSlotsURL}"></c:out>' method="POST">
				<h4 class="large-heading">
					<span class="heading-text-1">Add a Room:</span>
				</h4>
				<span class="text-element small-heading">Room Name:<br></span>
				<input name="room-name" type="text"> <span
					class="text-element small-heading">Capacity:<br></span> <input
					name="capacity" type="number" class="input-1"> <br> <br>
				<button type="submit" class="btn" name="myButton" value="Submit">Submit</button>
			<div class="current-db-container">
				<div id="curRooms" class="current-db" style="height: 225px;">
				<select name="roomSelect" id="roomSelect" class="dynam-select" multiple>
                <c:forEach items="${requestScope.rooms}" var="room">
                <option value="${room.roomID}">Room Name: ${room.name}, Capacity: ${room.capacity} </option><br>          
                </c:forEach>
                </select>
				</div> 	

				<br>
				<button class="btn" name="myButton" type="submit" value="modify">Modify</button>
				<button class="btn" name="myButton" type="submit" value="delete">Delete</button>
			</div>
			</form>
		</div>
	</div>

	<!--second column-->
	<div
		class="col-xs-12 column-1 col-md-12 col-lg-8 offset-lg-2 col-sm-12">

		<!-- session form -->
		<div class="container-fluid container-1">
		
			<form class="forms" id="SessionForm" action='<c:out value="${modifySessionURL}"></c:out>' method="POST">
				
				<h4 class="large-heading">Add Session:</h4>
				<span class="text-element small-heading">Session Name:<br></span>
				<input name="session-name" type="text"> <span
					class="text-element small-heading">Session Number:</span> <input
					name="session-number" type="number"> <span
					class="text-element small-heading">Speaker:<br></span> <input
					name="speaker" type="text"> <span
					class="text-element small-heading">Date:<br></span> <input
					name="date" type="date"> <span
					class="text-element small-heading">Time Slot:&nbsp;</span> 
					
					<select class="select drop-down-1" name="timeSlotSelect" id="timeSlotSelect">
					
						<c:forEach items="${requestScope.timeSlots}" var="timeSlots">
               			<option value="${timeSlots.timeSlotID}">Start Time: ${timeSlots.startTime}, End Time: ${timeSlots.endTime}</option> <br>           
                		</c:forEach>
                		
					</select> 
					
					<span class="text-element small-heading">Room:&nbsp;</span> 
					<select class="room" name="roomSelect" id="roomSelect">
					
					<c:forEach items="${requestScope.rooms}" var="room">
                	<option value="${room.roomID}">Room Name: ${room.name}, Capacity: ${room.capacity}</option><br>          
               		</c:forEach>
               		 
					</select> <br> <br>
				<button class="btn" name="myButton" type="submit" value="Submit">Submit</button>

			<div class="current-db-container">
				<div id="curSessions" class="current-db" style="height: 425px;">
				<select name="sessionSelect" id="sessionSelect" class="dynam-select" multiple>
				<c:forEach items="${requestScope.sessions}" var="session">
               	<option value="${session.sessionID}">Session Name: ${session.sessionName}, Speaker: ${session.speaker}</option> <br>           
                </c:forEach>
                </select>
                
				</div>
				<br>
				<button class="btn" name="myButton" type="submit" value="modify">Modify</button>
				<button class="btn" name="myButton" type="submit" value="delete">Delete</button>
			</div>
			</form>
		</div>
	</div>

</div>
</body>
</html>