<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<title>Growth Assessment Portal</title>
	<link rel="icon" href="Resources/favicon.ico">
	<link rel="stylesheet" type="text/css" href="trainee.css">
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
	<div class="navigator">
		<div class="nav-buttons-div">
			<a href="javascript:showDiv(active,'progress-div')"><button onclick="callGraph('fullProgress','Overall Growth')" id="myProgress" class="nav-buttons">My Progress</button></a>
			<a target="_blank" href="getReviews?id=<%=session.getAttribute("traineeID")%>"><button id="projects" class="nav-buttons">My Project Reviews</button></a>
			<a href="javascript:showDiv(active,'ratings-div')"><button class="nav-buttons">Self-Rate</button></a>
			<a href="javascript:showDiv(active,'settings-div')"><button class="nav-buttons">Settings</button></a>
			<% if ("trainee".equals(session.getAttribute("traineePassword"))) 
			{
			%>
			<a href="javascript:showDiv(active,'change-password-div')"><button class="nav-buttons">Change Password</button></a>
			<%
			}
			%>
			<a href="logout"><button onclick="logoutMessage()" class="nav-buttons">Logout</button></a>	
		</div>
	</div>
	<div id="change-password-div" style="display:none;">
		<div id="change-password">
			<form action="settings?category=password" method="post">
				<label for="oldpassword">Enter Old Password</label>
				<input type="password" name="oldpassword" id="oldpasswordcp"><br>
				<label for="newpassword">Enter New Password</label>
				<input type="password" name="newpassword" id="newpasswordcp"><br>
				<label for="confirmnewpassword">Confirm Password</label>
				<input type="password" name="confirmnewpassword" id="confirmnewpasswordcp">
				<button class="updatebutton">Update</button>
			</form>
		</div>
	</div>
	<div id="projects-div" style="display:none;">
	<!-- <div id="confirmmessage" style="text-align:center;"></div>
		<div class="project">
			<h3>Add Project Details</h3>
			<form id="projectform" action="fillprojects" method="post"> 
				<label>Choose Project type: </label>
				<select name="projecttype" required>
					<option value="">Project type</option>
					<option value="desktop">Desktop</option>
					<option value="web">Web</option>
				</select><br>
				<label for="domain">Domain: </label>
				<input style="margin-left:50px;" type="text" name="domain" id="domain" placeholder="Enter domain" required><br>
				<label for="projecttitle">Project Title:</label>
				<input type = "text" name="projecttitle" id="projecttitle" placeholder="Enter Project title" required>
				<button class="projectsubmit">Submit</button>
			</form>
			<div id="docs">
			<label>Upload Project Specification Document below </label>
			<input type="file" class="fileupload"><br>
			<label>Upload Technical Design Document below </label>
			<input type="file" class="fileupload">
			<h5>(* Technical design document should have ER diagram, Use case diagram and Database design)</h5>
			</div>
		</div> -->
		<div class="project">
			<h3 style="margin-left:42.5%;">Project Reviews</h3>
		</div>
	</div>
	<div id="progress-div" style="display: none;">
		<div class="selection-div">
			<div>
				<img src="Resources/goal.svg" width="60px" height="60px">
				<a href="#" style="width:160px;height:30px;"><button onclick="callGraph('fullProgress','Overall Growth')" class="overall">My Overall Growth</button></a>
			</div>
			<div class="sections">
				<label>Technology Skills</label><hr/>
				<a onclick="callGraph('logicalthinking','Logical Thinking')" class="select" href="#">Logical thinking</a><span style="color:red">*</span><br>
				<a onclick="callGraph('codingstandards','Coding Standards')" class="select" href="#">Coding Standards</a><span style="color:red">*</span><br>
				<a onclick="callGraph('bitbucketusage','Bit Bucket Usage')" class="select" href="#">Bit bucket usage</a><span style="color:red">*</span>
			</div>
			<div class="section-line" style="height:120px;margin-top:10px;"></div>
			<div class="sections">
				<label>Work Ethics</label><hr/>
				<a onclick="callGraph('dresscode','Dress Code')" class="select" href="#">Dress code</a><br>
				<a onclick="callGraph('attendance','Attendance')" class="select" href="#">Attendance</a><span style="color:red">*</span><br>
				<a onclick="callGraph('timesheet','Time Sheet')" class="select" href="#">Time Sheet</a><span style="color:red">*</span><br>
				<a onclick="callGraph('teambonding','Team Bonding')" class="select" href="#">Team Bonding</a><span style="color:red">*</span>
			</div>
			<div class="sections-cf">
				<label>21st Century fluencies</label><hr/>
				<a onclick='callGraph("othersperspective","Thinking from other person perspective")' class="select" href="#">Thinking from other's perspective</a><br>
				<a onclick="callGraph('grammar','Grammar')" class="select" href="#">Grammar</a><br>
				<a onclick="callGraph('verbalcommunication','Verbal Communication')" class="select" href="#">Verbal Communication</a><br>
				<a onclick="callGraph('understandingwritteninfo','Understanding Written Info')" class="select" href="#">Understanding written info</a><br>
				<a onclick="callGraph('questioningability','Questioning Ability')" class="select" href="#">Questioning ability</a><br>
				<a onclick="callGraph('emailwriting','Email Writing')" class="select" href="#">Email writing</a>
			</div>
			<div class="section-line"></div>
			<div class="sections" style="width:33%">
				<label>Weekly Reviews</label><hr/>
				<a onclick="callGraph('assignments','Assignments')" class="select" href="#">Assignments</a><br>
				<a onclick="callGraph('assessments','Assessments')" class="select" href="#">Assessments</a>
				<a onclick="callGraph('marks','Assessment Marks')" class="select" href="#">Assessment Marks</a><span style="color:red">**</span>
			</div>
			<div style="width:100%;position:absolute;margin-top:315px;">
			<p style="color:#f57f17;font-size:12px;"><span style="color:red">*</span> - Mentor doesn't give ratings for this skill. Mentor graph will not show data.</p>
			<p style="color:#f57f17;font-size:12px;"><span style="color:red">**</span> - Only mentor updates the assessment scores. Trainee graph will not show data.</p>
			</div>
		</div>
		<div id="graph">
			<h3>My Self-Rating Chart</h3>
			<canvas id="myChart" width="500" height="350"></canvas>
		</div>
		<div id="mentorgraph">
			<h3>Mentor Rating Chart</h3>
			<canvas id="mentorChart" width="500" height="350"></canvas>
		</div>
	</div>
	<div id="ratings-div">
		<div id="trainee-self-rating">
			<div id="instructions">
			<p>* Please fill all the values in the three columns (Technology, 21st Century fluencies and Work ethics) and select the week before you press 'Enter'.</p>
			<p>* Please refer to the Image on the right for the metrics.</p>
			</div>
			<div id="messagefortrainee" style="text-align:center;"></div>
			<form name="selfratingform" id="self-rating" action="selfrating" method="post">
				<fieldset>
					<legend><h3>Growth Chart</h3></legend>
					<div id="skills-div">
						<a href="javascript:showSkill(activeskill,'technologyskill')">Technology Skills</a>
						<a href="javascript:showSkill(activeskill,'fluenciesskill')">21st Century Fluencies</a>
						<a href="javascript:showSkill(activeskill,'worksskill')">Work Ethics</a>
				  	</div>
					<select name="week" style="width: 110px" class="week" required>
						<option value="">Select Week</option>
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
					<div id="technologyskill">
						<table class="technology-skills">
						<h4>Technology Skills</h4><hr/>
						<tr>
							<td><label for="logicalthinking">Logical thinking</label></td>
							<td><input type="text" name="logicalthinking" id="logicalthinking" required></td>
						</tr>
						<tr>
							<td><label for="codingstandards">Usage of coding standards</label></td>
							<td><input type="text" name="codingstandards" id="codingstandards" required></td>
						</tr>
						<tr>
							<td><label for="bitbucket">Usage of BitBucket</label></td>
							<td><input type="text" name="bitbucket" id="bitbucket" required></td>
						</tr>
						<tr>
							<td><label for="assignments">Assignments</label></td>
							<td><input type="text" name="assignments" id="assignments" required></td>
						</tr>
						<tr>
							<td><label for="assessments">Assessments</label></td>
							<td><input type="text" name="assessments" id="assessments" required></td>
						</tr>
					</table>
					</div>
					
					<div style="display: none;" id="fluenciesskill">
						<table class="century-fluencies">
						<h4>21st Century Fluencies</h4><hr/>
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
					
					<div style="display: none;" id="worksskill">
						<table class="work-ethics">
						<h4>Work Ethics</h4><hr/>
						<tr>
							<td><label for="dresscode">Dress code</label></td>
							<td><input type="text" name="dresscode" id="dresscode" required></td>
						</tr>
						<tr>
							<td><label for="attendance">Attendance</label></td>
							<td><input type="text" name="attendance" id="attendance" required></td>
						</tr>
						<tr>
							<td><label for="timesheet">Timesheet</label></td>
							<td><input type="text" name="timesheet" id="timesheet" required></td>
						</tr>
						<tr>
							<td><label for="teambonding">Team bonding</label></td>
							<td><input type="text" name="teambonding" id="teambonding" required></td>
						</tr>
						</table>
					</div>
					<button name="enter" class="enter-button">Enter</button>
				</fieldset>
			</form>
			<div class="effortmeter">
				<img src="Resources/Asset 3.png" alt="effort meter" width="180px;">
			</div>
		</div>
	</div>
	<div id="settings-div" style="display: none;">	
	<div id="settings">
			<div id="profilepic">
				<form method="post" action="imageUpload" enctype="multipart/form-data">
					<img id="updateimage" src="Image.jsp?id=<%=session.getAttribute("traineeID") %>" alt="trainee image" onerror="this.src='Resources/traineepic.svg'">
					<input name="profilephoto" type="file" id="profileimage" class="choosepic">
					<button class="settingsbutton">Update Profile Picture</button>
				</form>
			</div>
			<div id="update-menu">
				<div><a href="javascript:showUpdate(activeUpdate,'nameupdate')"><button onclick="setMessage()" class="settingsmenu">Change Name</button></a></div>
				<div><a href="javascript:showUpdate(activeUpdate,'emailupdate')"><button onclick="setMessage()" class="settingsmenu">Change Email</button></a></div>
				<div><a href="javascript:showUpdate(activeUpdate,'passwordupdate')"><button onclick="setMessage()" class="settingsmenu">Change Password</button></a></div>
			</div>
			<div id="message" style="color:black"></div>
			<div id="nameupdate" style="display:none;">
				<form action="settings?cateogry=name" method="post" id="nameupdateform">
					<label for="updatename">Username</label>
					<input type="text" name="updatename" id="updatename">
					<button class="updatebutton">Update</button>
				</form>
			</div>
			<div id="emailupdate" style="display:none;">
				<form action="settings?category=email" method="post" id="emailupdateform">
					<label id="updateemail">Email</label><br>
					<input type="email" name="updateemail" id="updateemail">
					<button class="updatebutton">Update</button>
				</form>
			</div>
			<div id="passwordupdate" style="display:none;">
				<form id="passwordupdateform" method="post" action="settings?category=password">
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
	</div>
	<script>
	function setMessage(){
		document.getElementById("message").innerHTML = "";
	}
	$(document).on('click',function(){
		$("#message").html('');
	});
	function callGraph(selectedSkill, labelText){
		$('#myChart').remove();
		$('#graph').append('<canvas id="myChart" width="500" height="350"></canvas>');
		$.ajax({
			url : "getdata?id=<%=session.getAttribute("traineeID")%>&category="+selectedSkill,
			type : "GET",
			success : function(data){
				var total = {
						rating : []
				};
				var len = data.length;
				for(var i = 0; i < len; i++){
					total.rating.push(data[i].total);
				}
				var ctx = $("#myChart");
				var data = {
						labels : ["1","2","3","4","5","6","7","8","9","10","11","12"],
						datasets : [
							{
								label: labelText,
								data : total.rating,
								backgroundColor : "rgba(54, 162, 235, 0.8)",
								borderColor : "rgba(54, 162, 235, 1)",
							},
							{
								data : total.rating,
								label : "linegraph",
								backgroundColor : "rgba(75, 192, 192, 1)",
								borderColor : "rgba(75, 192, 192, 1)",
								type : "line",
								fill : false,
								lineTension : 0,
								pointRadius : 5
							}
						]
				}
				var options = {
						title : {
							display : true,
							position : "top",
							text : labelText,
							fontSize : 18,
							fontColor : "#004ba0"
						},
						legend : {
							display : true,
							position : "bottom",
							labels : {
								filter : function(item, chart){
									return !item.text.includes('linegraph');
								},
								fontColor : "#004ba0"
							}
						},
						scales : {
							xAxes : [{
								barPercentage : 0.5,
								gridLines : {
									display : false
								},
								ticks : {
									fontColor : "#004ba0"
								}
							}],
							yAxes : [{
								ticks : {
									beginAtZero : true,
									fontColor : "#004ba0"
								}
							}]
						}
				};
				var chart = new Chart(ctx, {
					type : "bar",
					data : data,
					options : options
				});
			}
		});
		$('#mentorChart').remove();
		$('#mentorgraph').append('<canvas id="mentorChart" width="500" height="350"></canvas>');
		$.ajax({
			url : "getmentordata?id=<%=session.getAttribute("traineeID")%>&category="+selectedSkill,
			type : "GET",
			success : function(data){
				if(selectedSkill=="marks"){
					var assessmentmark = {
							rating : [],
							topic : []
					};
					var len = data.length;
					for(var i = 0; i < len; i++){
						assessmentmark.rating.push(data[i].marks);
						assessmentmark.topic.push(data[i].topics);
					}
					var ctx = $("#mentorChart");
					var data = {
							labels :["Week 1\n"+assessmentmark.topic[0],"Week 2\n"+assessmentmark.topic[1],"Week 3\n"+assessmentmark.topic[2],"Week 4\n"+assessmentmark.topic[3],"Week 5\n"+assessmentmark.topic[4],
								"Week 6\n"+assessmentmark.topic[5],"Week 7\n"+assessmentmark.topic[6],"Week 8\n"+assessmentmark.topic[7],
								"Week 9\n"+assessmentmark.topic[8],"Week 10\n"+assessmentmark.topic[9],"Week 11\n"+assessmentmark.topic[10],"Week 12\n"+assessmentmark.topic[11]],
							datasets : [
								{
									label: labelText,
									data : assessmentmark.rating,
									backgroundColor : "rgba(54, 162, 235, 0.8)",
									borderColor : "rgba(54, 162, 235, 1)",
								},
								{
									data : assessmentmark.rating,
									label : "linegraph",
									backgroundColor : "rgba(75, 192, 192, 1)",
									borderColor : "rgba(75, 192, 192, 1)",
									type : "line",
									fill : false,
									lineTension : 0,
									pointRadius : 5
								}
							]
					}
					var options = {
							
							title : {
								display : true,
								position : "top",
								text : labelText,
								fontSize : 18,
								fontColor : "#004ba0"
							},
							legend : {
								display : true,
								position : "bottom",
								labels : {
									filter : function(item, chart){
										return !item.text.includes('linegraph');
									},
									fontColor : "#004ba0"
								}
							},
							scales : {
								xAxes : [{
									barPercentage : 0.5,
									gridLines : {
										display : false
									},
									ticks : {
										fontColor : "#004ba0"
									}
								}],
								yAxes : [{
									ticks : {
										beginAtZero : true,
										fontColor : "#004ba0"
									}
								}]
							}
					};
					var chart = new Chart(ctx, {
						type : "bar",
						data : data,
						options : options,
						plugins : [{
							beforeInit : function(chart){
								chart.data.labels.forEach(function(e,i,a){
									if(/\n/.test(e)){
										a[i] = e.split(/\n/);
									}
								});
							}
						}]
					});
				}
				else{
					var graph = {
							rating : []
					};
					var len = data.length;
					for(var i = 0; i < len; i++){
						graph.rating.push(data[i].total)
					}
					var ctx = $("#mentorChart");
					var data = {
							labels :["Week 1","Week 2","Week 3","Week 4","Week 5",
								"Week 6","Week 7","Week 8",
								"Week 9","Week 10","Week 11","Week 12"],
							datasets : [
								{
									label: labelText,
									data : graph.rating,
									backgroundColor : "rgba(54, 162, 235, 0.8)",
									borderColor : "rgba(54, 162, 235, 1)",
								},
								{
									data : graph.rating,
									label : "linegraph",
									backgroundColor : "rgba(75, 192, 192, 1)",
									borderColor : "rgba(75, 192, 192, 1)",
									type : "line",
									fill : false,
									lineTension : 0,
									pointRadius : 5
								}
							]
					}
					var options = {
							
							title : {
								display : true,
								position : "top",
								text : labelText,
								fontSize : 18,
								fontColor : "#004ba0"
							},
							legend : {
								display : true,
								position : "bottom",
								labels : {
									filter : function(item, chart){
										return !item.text.includes('linegraph');
									},
									fontColor : "#004ba0"
								}
							},
							scales : {
								xAxes : [{
									barPercentage : 0.5,
									gridLines : {
										display : false
									},
									ticks : {
										fontColor : "#004ba0"
									}
								}],
								yAxes : [{
									ticks : {
										beginAtZero : true,
										fontColor : "#004ba0"
									}
								}]
							}
					};
					var chart = new Chart(ctx, {
						type : "bar",
						data : data,
						options : options
					});
				}
			}
		});
	}
	var projectdetailsform = $("#projectform");
	projectdetailsform.submit(function(event){
		event.preventDefault();
		$.ajax({
			url : 'fillprojects',
			data : projectdetailsform.serialize(),
			type : 'post',
			success : function(text){
				$('#confirmmessage').html(text);
				$('input').val('');
			}
		});
	});
	var selfratingform = $("#self-rating");
	selfratingform.submit(function(event){
		event.preventDefault();
		$.ajax({
			url : 'selfrating',
			data : selfratingform.serialize(),
			type : 'post',
			success: function(text){
				$('#ratings-div').animate({ scrollTop: 0 }, 0);
				$('#messagefortrainee').html(text);
				if(text.includes("Your values are not updated. Please try again after some time.")
						|| ("You already updated for this week. Please check the week you selected.")
						|| ("Your self ratings for this week are updated.")){
					$('input').val('');
				}
			}
		});
	});
	$(document).on('click',function(){
		$("#messagefortrainee").html('');
	});
	var nameform = $("#nameupdateform");
	nameform.submit(function(event){
		event.preventDefault();
		$.ajax({
			url : 'settings?category=name',
			data : nameform.serialize(),
			type : 'post',
			success: function(text){
				$('#message').html(text);
				$('input').val('');
			}
		});
	}); 
	var genderform = $("#genderupdateform");
	genderform.submit(function(event){
		event.preventDefault();
		$.ajax({
			url : 'settings?category=gender',
			data : genderform.serialize(),
			type : 'post',
			success: function(text){
				$('#message').html(text);
				$('#male').prop("checked",false);
				$('female').prop("checked",false);
			}
		});
	}); 
	var emailform = $("#emailupdateform");
	emailform.submit(function(event){
		event.preventDefault();
		$.ajax({
			url : 'settings?category=email',
			data : emailform.serialize(),
			type : 'post',
			success: function(text){
				$('#message').html(text);
				$('input').val('');
			}
		});
	});
	var passwordform = $("#passwordupdateform");
	passwordform.submit(function(event){
		event.preventDefault();
		$.ajax({
			url : 'settings?category=password',
			data : passwordform.serialize(),
			type : 'post',
			success: function(text){
				$('#message').html(text);
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
		function logoutMessage(){
			alert("You are successfully logged out. Click 'Ok' to go back to home page");
		}
		var active = 'ratings-div';
		var activeskill = 'technologyskill';
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
		function showSkill(div1, div2){
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
   			activeskill = div2;
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