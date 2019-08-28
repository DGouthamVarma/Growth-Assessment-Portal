<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>

    
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<title>Growth Assessment Portal</title>
	<link rel="icon" href="Resources/favicon.ico">
	<link rel="stylesheet" type="text/css" href="techmentor.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700" rel="stylesheet">
</head>
<body>
	<div class="header">
		<img class="site-icon" src="Resources/gap.svg" alt="Icon" width="60px" height="60px">
		<form name="searchform">
		<input type="text" onkeyup="searchData()" name="search" placeholder="Search by Trainee" class="search">
		</form>
		<div class="profile-div">
			<h4>${sessionScope.currentMentor}</h4>
			<img src="MentorImage.jsp?id=<%=session.getAttribute("mentorID") %>" width="40px" height="40px" alt="user-image" onerror="this.src='Resources/user.svg'">
		</div>
		<div id="searchlocation"></div>
	</div>
	<div class="navigator">
		<div class="nav-buttons-div">
			<a href="javascript:showDiv(active,'trainees-div')"><button class="nav-buttons">Trainee Progress</button></a>
			<a href="javascript:showDiv(active,'ratings-div')"><button class="nav-buttons">Weekly Rating</button></a>
			<a target="_blank" href="GetProjects?team=<%=(String)session.getAttribute("mentorTeamID")%>"><button class="nav-buttons">Project Reviews</button></a>
			<a href="javascript:showDiv(active,'settings-div')"><button class="nav-buttons">Settings</button></a>
			<% if ("mentor".equals(session.getAttribute("mentorPassword"))) 
			{
			%>
			<a href="javascript:showDiv(active,'change-password-div')"><button class="nav-buttons">Change Password</button></a>
			<%
			}
			%>
			<a href="<%=request.getContextPath()%>/logout"><button onclick=(logoutMessage()) class="nav-buttons">Logout</button></a>
		</div>
	</div>
	<div id="trainees-div" style="display: none;">
		<h3 style="margin-left:5%;color:#004ba0">Your Current Batch: ${sessionScope.mentorTeam}</h3>
		<div id="trainees-list">
			<% for(int i=1; i<= (Integer)session.getAttribute("numberOfTrainees");i++) { %>
			<div class="card">
				<img class="profile-img" src="Image.jsp?id=<%=session.getAttribute("TraineeId"+Integer.toString(i)) %>" alt="profile picture" onerror="this.src='Resources/traineepic.svg'">
				<h3><%=(String)session.getAttribute("Trainee"+Integer.toString(i)) %></h3>
				<a href="traineeProgress?id=<%=session.getAttribute("TraineeId"+Integer.toString(i)) %>" target="_blank"><button>View Progress</button></a>
			</div>
			<%} %>
		</div>
		<h3 style="color:#f57f17;margin-left:120px;margin-top:20px;">Click on 'View Progress' in the trainee profile cards to view individual progress</h3>
	</div>
	<div id="change-password-div" style="display:none;">
		<div id="change-password">
			<form action="mentorupdate?category=password" method="post">
				<label for="oldpassword">Enter Old Password</label>
				<input type="password" name="oldpassword" id="oldpassword"><br>
				<label for="newpassword">Enter New Password</label>
				<input type="password" name="newpassword" id="newpassword"><br>
				<label for="confirmnewpassword">Confirm Password</label>
				<input type="password" name="confirmnewpassword" id="confirmnewpassword">
				<button class="updatebutton">Update</button>
			</form>
		</div>
	</div>
	<div id="ratings-div" style="display:block;">
		<div id="mentor-weekly-ratings">
			<div id="messageformentor" style="text-align:center;"></div>
			<form class="weekly-rating" action="mentorrating" method="post">
				<fieldset>
					<legend>Weekly Rating</legend>
					<div id="batchname">
						<h3>${sessionScope.mentorTeam}</h3>
					</div>
					<select name="week" style="width: 80px" class="week" required>
						<option value="">Week</option>
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
						<option>6</option>
						<option>7</option>
						<option>8</option>
						<option>9</option>
						<option>10</option>
						<option>11</option>
						<option>12</option>
					</select>
					<select name="trainee" style="width: 120px" class="trainee" required>
						<option value="">Trainee</option>
						<% for(int i=1; i<= (Integer)session.getAttribute("numberOfTrainees");i++) { %>
						<option value="<%=(String)session.getAttribute("TraineeId"+Integer.toString(i))%>"><%=(String)session.getAttribute("Trainee"+Integer.toString(i)) %></option>
						<%} %>
					</select><br>
					<table class="mentor-rating-input">
						<tr>
							<td><label for="assignments">Technical Assignments</label></td>
							<td><input type="text" name="assignment" id="assignments" required></td>
						</tr>
						<tr>
							<td><label for="assessments">Technical Assessments</label></td>
							<td><input type="text" name="assessment" id="assessments"></td>
						</tr>
						<tr>
							<td><label for="dress">Dress Code</label></td>
							<td><input type="text" name="dresscode" id="dress" required></td>
						</tr>
					</table>
					<table class="mentor-mark-input">
						<tr>
							<td><label for="assessment-mark">Assessment Mark (out of 15)</label></td>
							<td><input type="text" name="assessmentmark" id="assessment-mark"></td>
						</tr>
						<tr>
							<td><label for="topic">Topic</label></td>
							<td><input type="text" name="topic" id="topic" style="width:150px;"></td>
						</tr>
					</table>
					<button name="publish" class="publish-button">Publish</button>
				</fieldset>
			</form>
			<img src="Resources/Asset 3.png" alt="effort meter" width="175px;">
			<p style="color:#f57f17;margin-left:25%">* Please refer to the image on the right side for the metrics</p>
		</div>
	</div>
	<div id="settings-div" style="display: none;">
	<div id="settings">
			<div id="profilepic">
				<form method="post" action="mentorImageUpload?role=tech" enctype="multipart/form-data">
				<img id="updateimage" src="MentorImage.jsp?id=<%=session.getAttribute("mentorID") %>" alt="Mentor image" onerror="this.src='Resources/traineepic.svg'">
				<input name="profilephoto" type="file" id="profileimage" class="choosepic">
				<button class="settingsbutton">Update Profile Picture</button>
				</form>
			</div>
			<div id="update-menu">
				<div><button onclick="showUpdate(activeUpdate,'nameupdate')" class="settingsmenu">Change Name</button></div>
				<div><button onclick="showUpdate(activeUpdate,'emailupdate')" class="settingsmenu">Change Email</button></div>
				<div><button onclick="showUpdate(activeUpdate,'passwordupdate')" class="settingsmenu">Change Password</button></div>
			</div>
			<div id="nameupdate" style="display:none;">
				<form action="mentorupdate?category=name" method="post">
					<label for="updatename">Username</label>
					<input type="text" name="updatename" value="" id="updatename">
					<button class="updatebutton">Update</button>
				</form>
			</div>
			<div id="emailupdate" style="display:none;">
				<form action="mentorupdate?category=email" method="post">
					<label id="updateemail">Email</label><br>
					<input type="email" name="updateemail" value="" id="updateemail">
					<button class="updatebutton">Update</button>
				</form>
			</div>
			<div id="passwordupdate" style="display:none;">
				<form action="mentorupdate?category=password" method="post">
					<label for="oldpassword">Enter Old Password</label>
					<input type="password" name="oldpassword" id="oldpassword"><br>
					<label for="newpassword">Enter New Password</label>
					<input type="password" name="newpassword" id="newpassword"><br>
					<label for="confirmnewpassword">Confirm Password</label>
					<input type="password" name="confirmnewpassword" id="confirmnewpassword">
					<button onclick="return passwordMatch()" class="updatebutton">Update</button>
				</form>
			</div>
		</div>		
	</div>
	<script type="text/javascript">
	$(document).on('click',function(){
		$('#searchlocation').html('');
	});
	var request = new XMLHttpRequest();
	function searchData(){
		var searchtext = document.searchform.search.value;
		var url = "search.jsp?text="+searchtext;
		try{
			request.onreadystatechange = function(){
				if(request.readyState == 4){
					var text = request.responseText;
					document.getElementById('searchlocation').innerHTML = text;
				}
			}
			request.open("GET",url,true);
			request.send();
		}
		catch(Exception){
			alert("Unable to connect");
		}
	}
	
	
	var weeklyratingform = $(".weekly-rating");
	weeklyratingform.submit(function(event){
		event.preventDefault();
		$.ajax({
			url : 'mentorrating',
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
		var active = 'ratings-div';
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