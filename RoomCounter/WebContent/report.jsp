<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>

	<head>

			<title>Room Count</title>
			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width,initial-scale=1">
			<link rel="stylesheet" href="css/bootstrap.css">
			<link rel="stylesheet" href="css/bootstrap4.min.css">
			<link rel="stylesheet" href="css/wireframe-theme.min.css">
			<link rel="stylesheet" href="css/main.css">
			<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
			<script src="queryFunctions.js" type="text/javascript"></script>

	</head>
	<body>
         	<c:url value="/ReportUI" var="reportURL"></c:url>

		<div class="container">
			<div class="row">
				<div class="col-lg-12 mx-auto text-center mt-4 mb-4">
					<h2>Search Database</h2>
					<br>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row">
			<div class="col-lg-4 border col-md-4 offset-md-1">
			<table border=2" bordercolor="#000000" width=400><td> 
					<form class="form" method="post"  name="queryDatabase" onsubmit="setTimeout(function(){window.location.reload();},10)">
					<script type="text/javascript">
					
					    function SessionMenu()
					    {
					        if(document.getElementById("session").value!="none")
					        {
					            document.getElementById("room").disabled = true;
					            document.getElementById("time").disabled = true;
					        }
					        else
					        {
					            document.getElementById("room").disabled = false;
					            document.getElementById("time").disabled = false;
					        }                       
					    }    
					    
					    function RoomMenu()
					    {
					        if(document.getElementById("room").value!="none")
					        {
					            document.getElementById("session").disabled = true;
					        }
					        else
					        {
					            document.getElementById("session").disabled = false;
					            document.getElementById("time").disabled = false;
					        }                       
					    }   
					    
					    function TimeMenu()
					    {
					        if(document.getElementById("time").value!="none")
					        {
					            document.getElementById("room").disabled = true;
					            document.getElementById("session").disabled = true;
					        }
					        else
					        {
					            document.getElementById("session").disabled = false;
					        }                       
					    }   
					</script>
					<span class="text-element small-heading">Session Name: </span>
							<select name="sessionName" id="session" onchange="SessionMenu()">
							<option value = "none">Choose a session</option>
							<c:forEach items="${sessions}" var="session">
                			<option value="${session.sessionID}">${session.sessionName}</option>          
               				</c:forEach>
						</select>
			<br /><br/>
						<span class="text-element small-heading">Room Name: </span>
						<select name ="roomName" id="room" onchange="RoomMenu()">
						<option value = "none">Choose a room</option>
					<c:forEach items="${requestScope.rooms}" var="room" >
                	<option value="${room.roomID}">${room.name}</option>         
               		</c:forEach>
						</select>
			<br /><br/>			
						<span class="text-element small-heading">Time: </span>
						<select name = "timeSlotName" id="time" onchange="TimeMenu()">
							<option value = "none">Choose a time</option>
							<c:forEach items="${requestScope.timeslots}" var="timeslot">
                			<option value="${timeslot.timeSlotID}">${timeslot.startTime} to ${timeslot.endTime}</option>          
               				</c:forEach>
						</select>
						</td></table>
			</div>
												
												
			<div class=" col-md-4 col-lg-4 border offset-lg-1">
			<table border=2" bordercolor="#000000" width = 400><td> 
						<h4>Count Period</h4>
						<br/>
						<input class="form-check-input ml-2" type="radio" name="countPeriod" value="beginning">
						<label class="form-check-label ml-4" for="beginning">Beginning:</label>
						<br />
						<input class="form-check-input ml-2" type="radio" name="countPeriod" value="middle">
						<label class="form-check-label ml-4" for="beginning">Middle:</label>
						<br />
						<input class="form-check-input ml-2" type="radio" name="countPeriod" value="end">
						<label class="form-check-label ml-4" for="beginning">End:</label>
						<br />
						<br>
					
						<label class="form-check-label" for="count">Room Count:</label>
					<input type="number" name="count" class="form-control" maxlength="4" onkeyup="this.value=this.value.replace(/[^\d]/,'')" placeholder='Number of occupants in room . . .'/>
						<br>
						<div class="text-center mx-auto"><button class="mt-4 mx-auto" type="submit" onclick="">Submit Count</button></div>
					
									</td></table>
			
			</div>
			
			</div>
		</div>

	</body>

</html>