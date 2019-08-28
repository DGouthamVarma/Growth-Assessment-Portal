<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<title>Growth Assessment Portal</title>
	<link rel="icon" href="Resources/favicon.ico">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="index.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-sm">
		<div class="container-fluid">
			<ul class="nav navbar navbar-left">
				<li>
					<a href="#" class="navbar-brand">
						<img class="d-inline-block align-bottom" src="Resources/gap.svg" alt="Icon" width="40px" height="40px">
						Growth Assessment Portal
					</a>
				</li>
			</ul>
			<div class="links">
				<a class="nav-links" href="https://www.talentsculptors.com/" target="_blank">Talent Sculptors</a>
				<a class="nav-links" href="tsgapadmin.jsp">Admin</a>
			</div>
		</div>
	</nav>
	<div class="about-container">
		<div class="about-container-mask"></div>
		<div class="row no-gutters">
			<div class="col-sm-3 column">
				<img class="about-image" src="Resources/report.svg" alt="AboutImage" width="250px" height="250px">
			</div>
			<div class="col-sm-6 column">
				<div class="about">
					<h3 class="about-text">Growth Assessment Portal helps Mentors to rate trainees and Trainees to assess their competencies</h3>
					<a href="#features">
					<button>Know more</button>
					</a>
				</div>
			</div>
			<div class="col-sm-3 column">
				<div id="login-form">
					<form action="UserPage" method="post">
					<img src="Resources/team.svg" alt="group" class="form-icon" width="50px" height="50px">
					<div class="field">
						<img src="Resources/usernamesvg.svg" alt="username" class="icon">
        				<input type="text" id="name" class="form-control" name="useremail" required>
        				<label class="form-control-placeholder" for="name">Email</label>
      				</div>
      				<div class="field">
      					<img src="Resources/lock.svg" alt="password" class="icon" width="29px" height="20px">
        				<input name="password" type="password" id="password" class="form-control" required>
        				<label class="form-control-placeholder" for="password">Password</label>
      				</div>
      				<a href="#" data-toggle="modal" data-target="#forgotPassword">Forgot Password</a>
      				<input type="submit" name="submit" value="Login" class="btn btn-warning login-button">
      				</form>
				</div>
			</div>
		</div>
		<div id="forgotPassword" class="modal fade" role="dialog">
  			<div class="modal-dialog">
    			<div class="modal-content">
      				<div class="modal-header">
        		        <h4 class="modal-title">Forgot your Password?</h4>
   					</div>
      				<div class="modal-body">
        				<form action="senddetails?category=password" class="changepassword" method="post">
        					<div style="color:#1976d2;" id="passwordMessage">We will Email you a link to set your new password</div><br>
        					<label for="checkpassword">Please enter your registered email in the field below</label>
        					<input name="emailuserchange" id="checkemail" type="email" placeholder="Enter your registered email" required>
        					<button>Get Password</button><br><br>
        				</form>
        				<div id="messageforuser"></div>
        				<p>Forgot email?</p> 
        				<p>Reach us at "gap.talentsculptors@gmail.com"</p>
      				</div>
      				<div class="modal-footer">
        				<button id="close" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      				</div>
    			</div>
			</div>
		</div>
	</div>
	<div id="features">
		<h2>Trainee's progress in just one click</h2>
		<div class="trainee">
			<img class="feature-image" src="Resources/rate.svg">
			<h5>Trainee Self-Rating</h5>
		</div>
		<div class="mentor">
			<img class="feature-image" src="Resources/review.svg">
			<h5>Mentor Rating</h5>
		</div>
		<div class="chart">
			<img class="feature-image" src="Resources/bar-chart.svg">
			<h5>Growth Charts</h5>
		</div>
		<div class="report">
			<img class="feature-image" src="Resources/favorite.svg">
			<h5>Reports</h5>
		</div>
	</div>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script>
	var senddetailsform = $(".changepassword");
	senddetailsform.submit(function(event){
		event.preventDefault();
		$.ajax({
			url : 'senddetails',
			data : senddetailsform.serialize(),
			type : 'post',
			success: function(text){
				$('#messageforuser').html(text);
				$('input').val('');
			}
		});
	});
	$("#close").click(function(event){
		$("#messageforuser").html('');
	});
	</script>
</body>
</html>