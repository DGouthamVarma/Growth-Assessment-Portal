<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Growth Assessment Portal</title>
	<link rel="icon" href="Resources/favicon.ico">
	<link rel="stylesheet" type="text/css" href="traineeprogress.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,700" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0/dist/Chart.min.js"></script>
</head>
<body>
	<div class="header">
		<img class="site-icon" src="Resources/gap.svg" alt="Icon" width="60px" height="60px">
		<div id="teamdetails">
		<h3>Team: <%=" "+(String)session.getAttribute("teamName") %></h3>
		<h3>Mentor: <%=" "+(String)session.getAttribute("mentorName") %></h3>
		<h3>Trainee: <%=" "+(String)session.getAttribute("traineeName") %></h3>
		</div>
		<div id="navigation-bar">
			<a href="logout" class="nav-buttons" onclick=(logoutMessage())>Logout</a>
		</div>
		<div class="profile-div">
			<img src="MentorImage.jsp?id=<%=session.getAttribute("mentorID") %>" width="40px" height="40px" alt="user-image" onerror="this.src='Resources/user.svg'">
			<h3>${sessionScope.currentMentor }</h3>
		</div>
	</div>
	<div id="progress-div" style="display: block;">
		<div class="selection-div">
			<div>
				<img src="Resources/goal.svg" width="60px" height="60px">
				<a href="#graph" style="width:160px;height:30px;"><button onclick="callGraph('fullProgress','Overall Growth')" class="overall">My Overall Growth</button></a>
			</div>
			<div class="sections">
				<label>Technology Skills</label><hr/>
				<a onclick="callGraph('logicalthinking','Logical Thinking')" class="select" href="#graph">Logical thinking</a><span style="color:red">*</span><br>
				<a onclick="callGraph('codingstandards','Coding Standards')" class="select" href="#graph">Coding Standards</a><span style="color:red">*</span><br>
				<a onclick="callGraph('bitbucketusage','Bit Bucket Usage')" class="select" href="#graph">Bit bucket usage</a><span style="color:red">*</span>
			</div>
			<div class="section-line" style="height:120px;margin-top:10px;"></div>
			<div class="sections">
				<label>Work Ethics</label><hr/>
				<a onclick="callGraph('dresscode','Dress Code')" class="select" href="#graph">Dress code</a><br>
				<a onclick="callGraph('attendance','Attendance')" class="select" href="#graph">Attendance</a><span style="color:red">*</span><br>
				<a onclick="callGraph('timesheet','Time Sheet')" class="select" href="#graph">Time Sheet</a><span style="color:red">*</span><br>
				<a onclick="callGraph('teambonding','Team Bonding')" class="select" href="#graph">Team Bonding</a><span style="color:red">*</span>
			</div>
			<div class="sections-cf">
				<label>21st Century fluencies</label><hr/>
				<a onclick='callGraph("othersperspective","Thinking from other person perspective")' class="select" href="#graph">Thinking from other's perspective</a><br>
				<a onclick="callGraph('grammar','Grammar')" class="select" href="#graph">Grammar</a><br>
				<a onclick="callGraph('verbalcommunication','Verbal Communication')" class="select" href="#graph">Verbal Communication</a><br>
				<a onclick="callGraph('understandingwritteninfo','Understanding Written Info')" class="select" href="#graph">Understanding written info</a><br>
				<a onclick="callGraph('questioningability','Questioning Ability')" class="select" href="#graph">Questioning ability</a><br>
				<a onclick="callGraph('emailwriting','Email Writing')" class="select" href="#graph">Email writing</a>
			</div>
			<div class="section-line" style="height:150px;"></div>
			<div class="sections" style="width:33%">
				<label>Weekly Reviews</label><hr/>
				<a onclick="callGraph('assignments','Assignments')" class="select" href="#graph">Assignments</a><br>
				<a onclick="callGraph('assessments','Assessments')" class="select" href="#graph">Assessments</a>
				<a onclick="callGraph('marks','Assessment Marks')" class="select" href="#mentorgraph">Assessment Marks</a><span style="color:red">**</span>
			</div>
			<div style="width:100%;position:absolute;margin-top:315px;">
			<p style="color:red;font-size:12px;"><span style="color:red">*</span> - Mentor doesn't give ratings for this skill. Mentor graph will not show data.</p>
			<p style="color:red;font-size:12px;"><span style="color:red">**</span> - Only mentor updates the assessment scores. Trainee graph will not show data.</p>
			</div>
		</div>
		<div id="graph">
			<h3>Trainee Self-Rating Chart</h3>
			<canvas id="myChart" width="600" height="450"></canvas>
		</div>
		<div id="mentorgraph">
			<h3>Mentor Rating Chart</h3>
			<canvas id="mentorChart" width="600" height="450"></canvas>
		</div>
	</div>
	<script>
	function callGraph(selectedSkill, labelText){
		$('#myChart').remove();
		$('#graph').append('<canvas id="myChart" width="500" height="350"></canvas>');
		$.ajax({
			url : "getdata?id=<%=session.getAttribute("CurrentTraineeId")%>&category="+selectedSkill,
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
								label : "point",
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
									return !item.text.includes('point');
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
								},
								scaleLabel: {
								    display: true,
								    labelString: 'Week',
								    fontSize : 20,
								    fontColor : "#1976d2"
								}
							}],
							yAxes : [{
								ticks : {
									beginAtZero : true,
									fontColor : "#004ba0"
								},
								scaleLabel: {
								    display: true,
								    labelString: 'Rating',
								    fontSize : 20,
								    fontColor : "#1976d2"
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
			url : "getmentordata?id=<%=session.getAttribute("CurrentTraineeId")%>&category="+selectedSkill,
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
									label : "point",
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
										return !item.text.includes('point');
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
									},
									scaleLabel: {
									    display: true,
									    labelString: 'Week, Topics',
									    fontSize : 20,
									    fontColor : "#1976d2"
									}
								}],
								yAxes : [{
									ticks : {
										beginAtZero : true,
										fontColor : "#004ba0"
									},
									scaleLabel: {
									    display: true,
									    labelString: 'Marks',
									    fontSize : 20,
									    fontColor : "#1976d2"
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
							labels :["1","2","3","4","5",
								"6","7","8",
								"9","10","11","12"],
							datasets : [
								{
									label: labelText,
									data : graph.rating,
									backgroundColor : "rgba(54, 162, 235, 0.8)",
									borderColor : "rgba(54, 162, 235, 1)",
								},
								{
									data : graph.rating,
									label : "point",
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
										return !item.text.includes('point');
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
									},
									scaleLabel: {
									    display: true,
									    labelString: 'Week',
									    fontSize : 20,
									    fontColor : "#1976d2"
									}
								}],
								yAxes : [{
									ticks : {
										beginAtZero : true,
										fontColor : "#004ba0"
									},
									scaleLabel: {
									    display: true,
									    labelString: 'Rating',
									    fontSize : 20,
									    fontColor : "#1976d2"
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
	</script>
</body>
</html>