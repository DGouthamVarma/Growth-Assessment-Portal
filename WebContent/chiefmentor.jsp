<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Growth Assessment Portal</title>
	<link rel="icon" href="Resources/favicon.ico">
	<link rel="stylesheet" type="text/css" href="chiefmentor.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700" rel="stylesheet">
</head>
<body>
	<div class="header">
		<img class="site-icon" src="Resources/gap.svg" alt="Icon" width="60px" height="60px">
		<form name="searchform">
		<input type="text" onkeyup="searchData()" name="search" placeholder="Search for team" class="search">
		</form>
		<div id="navigation-bar">
			<a href="logout" class="nav-buttons" onclick=(logoutMessage())>Logout</a>
			<a href="javascript:showDiv(active,'settings-div')" class="nav-buttons">Settings</a>
			<% if ("mentor".equals(session.getAttribute("mentorPassword"))) 
			{
			%>
			<a href="javascript:showDiv(active,'change-password-div')" class="nav-buttons">Change Password</a>
			<%
			}
			%>
			<a href="javascript:showDiv(active,'trainees-div')" class="nav-buttons">Teams</a>	
		</div>
		<div class="profile-div">
			<img src="MentorImage.jsp?id=<%=session.getAttribute("mentorID") %>" width="40px" height="40px" alt="user-image" onerror="this.src='Resources/user.svg'">
			<h3>${sessionScope.currentMentor }</h3>
		</div>
		<span id="searchlocation"></span>
	</div>
	<div id="trainees-div" style="display: block;">
		<%for(int i=1; i <=(Integer)session.getAttribute("numberOfCurrent"); i++){ %>
		<h3 style="float:left;margin-left:10%;">Team: <%=(String)session.getAttribute("TeamName"+Integer.toString(i)) %></h3>
		<h3 style="float:left;">Mentor: <%=(String)session.getAttribute("MentorName"+Integer.toString(i)) %></h3>
		<h3><a target="_blank" href="GetProjects?team=<%=(String)session.getAttribute("TeamID"+Integer.toString(i))%>">Review Projects</a></h3>
		<div id="trainees-list">
		<%for(int j=1;j<=(Integer)session.getAttribute("numberOfTrainees"+Integer.toString(i));j++){ %>
			<div class="card">
				<img class="profile-img" src="Image.jsp?id=<%=session.getAttribute("TraineeId"+Integer.toString(i)+Integer.toString(j)) %>" alt="profile picture" onerror="this.src='Resources/traineepic.svg'">
				<h3><%=(String)session.getAttribute("TraineeName"+Integer.toString(i)+Integer.toString(j)) %></h3>
				<a target="_blank" href="traineeProgress?id=<%=(String)session.getAttribute("TraineeId"+Integer.toString(i)+Integer.toString(j))%>"><button>View Progress</button></a>
			</div>
			<%} %>
		</div>
		<div class="sectionline"></div>
		<%}%>
	</div>
	<div id="change-password-div" style="display:none;">
		<div id="change-password">
			<form action="chiefupdate?category=password" method="post">
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
	<div id="settings-div" style="display: none;">
	<div id="settings">
			<div id="profilepic">
			<form method="post" action="mentorImageUpload?role=tech" enctype="multipart/form-data">
				<img id="updateimage" src="MentorImage.jsp?id=<%=session.getAttribute("mentorID") %>" alt="trainee image" onerror="this.src='Resources/traineepic.svg'">
				<input type="file" id="profileimage" class="choosepic">
				<button class="settingsbutton">Update Profile Picture</button>
				</form>
			</div>
			<div id="update-menu">
				<h4>Click to Update</h4>
				<div><button onclick="showUpdate(activeUpdate,'nameupdate')" class="settingsmenu">Name</button></div>
				<div><button onclick="showUpdate(activeUpdate,'emailupdate')" class="settingsmenu">Email</button></div>
				<div><button onclick="showUpdate(activeUpdate,'passwordupdate')" class="settingsmenu">Password</button></div>
				<div><button onclick="showUpdate(activeUpdate,'genderupdate')" class="settingsmenu">Gender</button></div>
			</div>
			<div id="nameupdate" style="display:none;">
				<form action="chiefupdate?category=name" method="post">
					<label for="updatename">Username</label>
					<input type="text" name="updatename" value="" id="updatename">
					<button class="updatebutton">Update</button>
				</form>
			</div>
			<div id="emailupdate" style="display:none;">
				<form action="chiefupdate?category=email" method="post">
					<label id="updateemail">Email</label><br>
					<input type="email" name="updateemail" value="" id="updateemail">
					<button class="updatebutton">Update</button>
				</form>
			</div>
			<div id="passwordupdate" style="display:none;">
				<form action="chiefupdate?category=password" method="post">
					<label for="oldpassword">Enter Old Password</label>
					<input type="password" name="oldpassword" id="oldpassword"><br>
					<label for="newpassword">Enter New Password</label>
					<input type="password" name="newpassword" id="newpassword"><br>
					<label for="confirmnewpassword">Confirm Password</label>
					<input type="password" name="confirmnewpassword" id="confirmnewpassword">
					<button onclick="return passwordMatch()" class="updatebutton">Update</button>
				</form>
			</div>
			<div id="genderupdate" style="display:none;">
				<form action="chiefupdate?category=gender" method="post">
					<label for="updategender">Gender</label><br>
					<input type="radio" name="updategender" id="updategender" value="Male">Male<br>
					<input type="radio" name="updategender" id="updategender" value="Female">Female<br>
					<button class="updatebutton">Update</button>
				</form>
				
			</div>
		</div>		
	</div>
	
	<script type="text/javascript">
	$(document).on('click',function(){
		$('#searchlocation').html('');
	});
	var request;
	function searchData(){
		var searchtext = document.searchform.search.value;
		var url = "search.jsp?text="+searchtext;
		if(window.XMLHttpRequest){
			request = new XMLHttpRequest();
		}
		else if(window.ActiveXObject){
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
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
		$(function(){
		$('#profileimage').change( function(e) {
			var uploadedImage = URL.createObjectURL(e.target.files[0]);
			$('#updateimage').attr('src', uploadedImage);
		});
	});
		var active = 'trainees-div';
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