<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Growth Assessment Portal</title>
	<link rel="icon" href="Resources/favicon.ico">
	<link rel="stylesheet" type="text/css" href="projects.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700" rel="stylesheet">
</head>
<body>
	<div class="header">
		<img class="site-icon" src="Resources/gap.svg" alt="Icon" width="60px" height="60px">
		<div id="navigation-bar">
			<a href="logout" class="nav-buttons" onclick=(logoutMessage())>Logout</a>
		</div>
		<div class="profile-div">
			<img src="MentorImage.jsp?id=<%=session.getAttribute("mentorID") %>" width="40px" height="40px" alt="user-image" onerror="this.src='Resources/user.svg'">
			<h3>${sessionScope.currentMentor}</h3>
		</div>
	</div>
	<div id="teamdetails">
		<h3>Team: <%=session.getAttribute("teamname") %></h3>
		<h3>Mentor: <%=session.getAttribute("mentorname") %></h3>
	</div>
	<% for(int i = 1; i<=(Integer)session.getAttribute("numberofwebprojects");i++){ %>
	<div class="traineeprojects">
		<h3>Trainee Name: <%=session.getAttribute("TraineeWebName"+Integer.toString(i)) %></h3>
		<div id="webproject" style="display: block;">
			<div id="comments">
				<form method="post" name="projectform" id="commentform" action="projectreview?id=<%=(String)session.getAttribute("TraineeId"+Integer.toString(i))%>">
					<select name="projecttype">
						<option value="">Choose project type</option>
						<option value="web">Web Project</option>
						<option value="desktop">Desktop Project</option>
					</select>
					<textarea rows="6" cols="33" name="comment" form="commentform" placeholder="Write your reviews here"></textarea>
					<input type="submit" name="comment" value="Publish">
				</form>
			</div>
		</div>
	</div>
	<%} %>
</body>
</html>