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
		<a class="homelink" href="index.jsp">Go back to Home</a>
	</div>
	<div id="adminlogin">
		<img src="Resources/admin.svg" alt="admin icon" width="60px" height="60px">
		<h2>Welcome Admin</h2>
		<form action="admin" method="post">
			<input id="username" type="text" name="username" class="form-control" placeholder="Username"><br>
			<input id="password" type="password" name="password" class="form-control" placeholder="Password"><br>
			<input type="submit" name="submit" class="login-button" value="Login">
		</form>
	</div>
</body>
</html>