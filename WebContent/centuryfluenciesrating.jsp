<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Growth Assessment Portal</title>
	<link rel="icon" href="Resources/favicon.ico">
	<link rel="stylesheet" type="text/css" href="centuryfluenciesrating.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700" rel="stylesheet">
</head>
<body>
	<div class="header">
		<img class="site-icon" src="Resources/gap.svg" alt="Icon" width="60px" height="60px">
		<input type="text" name="search" placeholder="Search for team" class="search">
		<div id="navigation-bar">
			<a href="logout" class="nav-buttons" onclick=(logoutMessage())>Logout</a>
			<h4>Team Name: <%=(String)session.getAttribute("teamName") %></h4>
		</div>
		<div class="profile-div">
			<img src="MentorImage.jsp?id=<%=session.getAttribute("mentorID") %>" width="40px" height="40px" alt="user-image" onerror="this.src='Resources/user.svg'">
			<h3>${sessionScope.currentMentor}</h3>
		</div>
	</div>
	<div id="trainee-self-rating">
			<div id="instructions">
			<p>* Please fill all the values in the three columns (Technology, 21st Century fluencies and Work ethics) and select the week before you press 'Enter'.</p>
			<p>* Please refer to the Image on the right for the metrics.</p>
			</div>
			<div id="messageformentor" style="text-align:center;"></div>
			<form name="cfratingform" id="self-rating" action="cfratings" method="post">
				<fieldset>
					<legend><h3>21<sup>st</sup> Century Fluencies</h3></legend>
					<div style="display: block;" id="fluenciesskill">
						<table class="century-fluencies">
						<tr>
							<td><label for="otherperson">Thinking from other person's perspective</label></td>
							<td><input type="text" name="otherperson" id="otherperson" required></td>
						</tr>
						<tr>
							<td><label for="grammar">English Grammar</label></td>
							<td><input type="text" name="grammar" id="grammar" required></td>
						</tr>
						<tr>
							<td><label for="conversation">Verbal Conversation</label></td>
							<td><input type="text" name="conversation" id="conversation" required></td>
						</tr>
						<tr>
							<td><label for="writteninfo">Comprehending written information</label></td>
							<td><input type="text" name="writteninfo" id="writteninfo" required></td>
						</tr>
						<tr>
							<td><label for="questioning">Questioning ability</label></td>
							<td><input type="text" name="questioning" id="questioning" required></td>
						</tr>
						<tr>
							<td><label for="emailwriting">Email writing</label></td>
							<td><input type="text" name="emailwriting" id="emailwriting" required></td>
						</tr>
					</table>
					</div>
					<button name="enter" class="enter-button">Enter</button>
					<select name="week" style="width: 110px;" class="week" required>
						<option value="">Select Week</option>
						<%for(int i=1;i<=12;i++){ %>
						<option><%=i %></option>
						<%} %>
					</select>
					<select name="trainee" style="width:150px;" class="trainee" required>
						<option value="">Select Trainee</option>
						<%for(int i=1;i<=(Integer)session.getAttribute("numberOfTrainees");i++){ %>
						<option value="<%=(String)session.getAttribute("traineeId"+Integer.toString(i)) %>">
						<%=(String)session.getAttribute("traineeName"+Integer.toString(i)) %>
						</option>
						<%} %>
					</select>
					<div class="effortmeter">
						<img src="Resources/Asset 3.png" alt="effort meter" width="175px;">
					</div>
				</fieldset>
			</form>
		</div>
	<script type="text/javascript">
	var weeklyratingform = $("#self-rating");
	weeklyratingform.submit(function(event){
		event.preventDefault();
		$.ajax({
			url : 'cfratings',
			data : weeklyratingform.serialize(),
			type : 'post',
			success: function(text){
				$('#messageformentor').html(text);
				$('input').val('');
			}
		});
	});
		$(function(){
		$('#profileimage').change( function(e) {
			var uploadedImage = URL.createObjectURL(e.target.files[0]);
			$('#updateimage').attr('src', uploadedImage);
		});
	});
		var active = 'teams-div';
		var activeUpdate = 'nameupdate';
		function showUpdate(div1, div2){
			d1 = document.getElementById(div1);
   			d2 = document.getElementById(div2);
   			if(d1 == d2){
   				d2.style.display = "block";
   			}
   			else if( d2.style.display == "none" )
   				{
      				d1.style.display = "none";
      				d2.style.display = "block";
   				}
   			else
   				{
      				d1.style.display = "block";
      				d2.style.display = "none";
   				}
   			activeUpdate = div2;
		}
		function logoutMessage(){
			alert("You are successfully logged out. Click on 'Ok' to go back to home page");
		}
		graphdiv = document.getElementById('graphs');
		function showCharts(){
			graphdiv.style.display = "block";
		}
		function showDiv(div1, div2){
   			d1 = document.getElementById(div1);
   			d2 = document.getElementById(div2);
   			if(d1 == d2){
   				d2.style.display = "block";
   			}
   			else if( d2.style.display == "none" )
   				{
      				d1.style.display = "none";
      				d2.style.display = "block";
   				}
   			else
   				{
      				d1.style.display = "block";
      				d2.style.display = "none";
   				}
   			active = div2;
		}
	</script>
</body>
</html>