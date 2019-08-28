package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.TraineeRatingBean;
import DAO.TraineeRatingDAO;

@WebServlet("/TraineeSelfRating")
public class TraineeSelfRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TraineeSelfRating() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String traineeId =(String) session.getAttribute("traineeID");
		String logicalThinking = request.getParameter("logicalthinking");
		String codingStandards = request.getParameter("codingstandards");
		String bitBucket = request.getParameter("bitbucket");
		String assignments = request.getParameter("assignments");
		String assessments = request.getParameter("assessments");
		String otherPerson = request.getParameter("otherperson");
		String grammar = request.getParameter("grammar");
		String conversation = request.getParameter("conversation");
		String writtenInfo = request.getParameter("writteninfo");
		String questioning = request.getParameter("questioning");
		String emailWriting = request.getParameter("emailwriting");
		String dressCode = request.getParameter("dresscode");
		String attendance = request.getParameter("attendance");
		String timesheet = request.getParameter("timesheet");
		String teambonding = request.getParameter("teambonding");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = new Date();
		String date = dateFormat.format(todayDate);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String weeks = request.getParameter("week");
		int week = Integer.parseInt(weeks);
		try {
			if(logicalThinking.equals("") || codingStandards.equals("") || bitBucket.equals("") || assignments.equals("") || assessments.equals("") || otherPerson.equals("") ||
					grammar.equals("") || conversation.equals("") || writtenInfo.equals("") ||questioning.equals("") ||emailWriting.equals("") ||
					dressCode.equals("") || attendance.equals("") || timesheet.equals("") || teambonding.equals("")) {
				out.write("<h2 style=color:red;>Please fill all the fields!</h2>");		
			}
			else if(Integer.parseInt(logicalThinking)>5 || Integer.parseInt(codingStandards)>5 || Integer.parseInt(bitBucket)>5 || 
						Integer.parseInt(assignments)>5 || Integer.parseInt(assessments)>5 || Integer.parseInt(otherPerson)>5 ||
						Integer.parseInt(grammar)>5 || Integer.parseInt(conversation)>5 || Integer.parseInt(writtenInfo)>5 ||Integer.parseInt(questioning)>5 ||
						Integer.parseInt(emailWriting)>5 || Integer.parseInt(dressCode)>5 || Integer.parseInt(attendance)>5 || Integer.parseInt(timesheet)>5 || Integer.parseInt(teambonding)>5 ||
						Integer.parseInt(logicalThinking)<0 || Integer.parseInt(codingStandards)<0 || Integer.parseInt(bitBucket)<0 || 
						Integer.parseInt(assignments)<0 || Integer.parseInt(assessments)<0 || Integer.parseInt(otherPerson)<0 ||
						Integer.parseInt(grammar)<0 || Integer.parseInt(conversation)<0 || Integer.parseInt(writtenInfo)<0 ||Integer.parseInt(questioning)<0 ||
						Integer.parseInt(emailWriting)<0 || Integer.parseInt(dressCode)<0 || Integer.parseInt(attendance)<0 || Integer.parseInt(timesheet)<0 || Integer.parseInt(teambonding)<0) {
					out.write("<h2 style=color:red;>Ratings should be only in the range 0 and 5 (inclusive)</h2>");
				}
			else {
				TraineeRatingBean selfRatingBean = new TraineeRatingBean();
				selfRatingBean.setAssessments(Integer.parseInt(assessments));
				selfRatingBean.setAssignments(Integer.parseInt(assignments));
				selfRatingBean.setAttendance(Integer.parseInt(attendance));
				selfRatingBean.setBitBucket(Integer.parseInt(bitBucket));
				selfRatingBean.setCodingStandards(Integer.parseInt(codingStandards));
				selfRatingBean.setConversation(Integer.parseInt(conversation));
				selfRatingBean.setDressCode(Integer.parseInt(dressCode));
				selfRatingBean.setEmailWriting(Integer.parseInt(emailWriting));
				selfRatingBean.setGrammar(Integer.parseInt(grammar));
				selfRatingBean.setLogicalThinking(Integer.parseInt(logicalThinking));
				selfRatingBean.setOtherPerson(Integer.parseInt(otherPerson));
				selfRatingBean.setQuestioning(Integer.parseInt(questioning));
				selfRatingBean.setTeambonding(Integer.parseInt(teambonding));
				selfRatingBean.setTimesheet(Integer.parseInt(timesheet));
				selfRatingBean.setWrittenInfo(Integer.parseInt(writtenInfo));
				
				TraineeRatingDAO insertRating = new TraineeRatingDAO();
				String insertStatus = insertRating.insertRatingInDB(selfRatingBean, date, week, traineeId);
				if(insertStatus.equals("yes")) {
					out.write("<h2 style=color:green;>Your self ratings for this week are updated.</h2>");
				}
				else if(insertStatus.equals("Already")) {
					out.write("<h2 style=color:red;>You already updated for this week. Please check the week you selected.</h2>");
				}
				else {
					out.write("<h2 style=color:red;>Your values are not updated. Please try again after some time.</h2>");
				}
			}
		}
		catch(NumberFormatException charactersError) {
			out.write("<h2 style=color:red;>Only numbers are allowed. Please refer the image on right side for metrics. </h2>");
		}
	}

}
