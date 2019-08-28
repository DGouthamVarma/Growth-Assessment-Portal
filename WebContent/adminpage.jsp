<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Growth Assessment Portal</title>
	<link rel="icon" href="Resources/favicon.ico">
	<link rel="stylesheet" type="text/css" href="tsgapadmin.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700" rel="stylesheet">
</head>
<body>
	<div class="header">
		<img class="site-icon" src="Resources/gap.svg" alt="Icon" width="50px" height="50px">
		<h3>Growth Assessment Portal</h3>
		<a href="adminlogoutsession"><button id="logoutbutton">Logout</button></a>
	</div>
		<div class="insertdiv">
			<h2>Add Team</h2><hr>
			<div class="adddetails">
				<form action="newteam" method="post">
					<input class="input" type="text" name="teamname" placeholder="Enter Team Name" required><br>
					<label for="startdate">Batch start date</label><br>
					<input id="startdate" class="date" type="date" name="startdate" required><br>
					<select name="mentor" required>
						<option>Select Mentor</option>
						<% for(int i = 1; i<=(Integer)session.getAttribute("numberOfMentors"); i++) {%>
						<option value="<%= session.getAttribute("MentorID"+Integer.toString(i)) %>"><%= session.getAttribute("Mentor"+Integer.toString(i)) %></option>
						<%} %>
					</select><br>
					<input style="margin-top:82.5px;margin-left:75px;" type="submit" name="submit" value="Add" style="margin-left: 70px; margin-top: 30px;" class="login-button">
				</form>
			</div>
		</div>
		<div class="insertdiv">
			<h2>Add Mentor</h2><hr>
			<div class="adddetails">
				<form action="newmentor" method="post">
					<input class="input" type="text" name="mentorname" placeholder="Enter Mentor Name" required>
					<input class="input" type="email" name="mentoremail" placeholder="Enter Mentor Email" required>
					<select name="mentorrole" style="margin-top: 5px; margin-bottom: 20px;" required>
						<option>--Mentor Role--</option>
						<option>Chief</option>
						<option>Technical</option>
						<option>21st century fluencies</option>
					</select><br>
					<label>Gender</label><br>
					<input class="gender" type="radio" name="gender" value="male" required>Male
					<input class="gender" type="radio" name="gender" value="female">Female
					<input type="submit" name="submit" value="Add" style="margin-left: 70px; margin-top: 40px;" class="login-button">
				</form>
			</div>
		</div>
		<div class="insertdiv">
			<h2>Add Trainee</h2><hr>
			<div class="adddetails">
				<form action="newtrainee" method="post">
					<input class="input" type="text" name="traineename" placeholder="Enter Trainee Name" required><br>
					<input class="input" type="email" name="traineeemail" placeholder="Enter Trainee Email" required>
					<label for="join">Date of joining</label><br>
					<input id="joindate" class="date" type="date" name="joindate" required>
					<select name="team" style="width: 100px; margin-top: 5px; margin-bottom: 10px;" required>
						<option>Select Team</option>
						<% for(int i =1; i<=(Integer)session.getAttribute("numberOfTeams"); i++) {%>
						<% String optionValue = (String)session.getAttribute("TeamName"+Integer.toString(i)); %>
						<option value="<%= (String)session.getAttribute("TeamID"+Integer.toString(i))%>"><%= optionValue %></option>
						<%} %>
					</select>
					<label>Gender</label><br>
					<input class="gender" type="radio" name="gender" value="male" required>Male
					<input class="gender" type="radio" name="gender" value="female">Female
					<input type="submit" name="submit" value="Add" style="margin-left: 70px; margin-top: 25px;" class="login-button">
				</form>
			</div>
		</div>
</body>
</html>