<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Growth Assessment Portal</title>
	<link rel="icon" href="Resources/favicon.ico">
	<link rel="stylesheet" type="text/css" href="projectreviews.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
</head>
<body>
	<div class="header">
		<img class="site-icon" src="Resources/gap.svg" alt="Icon" width="60px" height="60px">
		<div class="search">
			<h3>Team : ${sessionScope.traineeTeam}</h3>
			<h3>Mentor : ${sessionScope.traineeMentorName}</h3>
		</div>
		<div class="profile-div">
			<h4>${sessionScope.currentTrainee}</h4>
			<img src="Image.jsp?id=<%=session.getAttribute("traineeID") %>" width="50px" height="50px" alt="user-image" onerror="this.src='Resources/user.svg'">
		</div>
	</div>
	<div class="project">
		<h2>Project Reviews</h2>
		<%for(int i = 1; i<=(Integer)session.getAttribute("numberOfComments");i++){ %>
		<div class="reviews">
			<h4 class="heading">Mentor: <span style="margin-left:10px;color:#004ba0"><%=(String)session.getAttribute("MentorName"+Integer.toString(i)) %></span></h4>
			<h4 class="heading">Project Type:<span style="margin-left:10px;color:#004ba0"><%=(String)session.getAttribute("ProjectType"+Integer.toString(i)) %></span></h4>
			<h4 class="heading">Date:<span style="margin-left:10px;color:#004ba0"> <%=(String)session.getAttribute("Date"+Integer.toString(i)) %></span></h4>
			<h4 class="heading">Comment:<br> <span style="color:#004ba0;"><%=(String)session.getAttribute("Comment"+Integer.toString(i)) %></span></h4>
		</div>
		<%} %>
	</div>
</body>
</html>