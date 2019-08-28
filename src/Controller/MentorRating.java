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

import Bean.MentorRatingBean;
import DAO.MentorRatingDAO;

/**
 * Servlet implementation class MentorRating
 */
@WebServlet("/MentorRating")
public class MentorRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MentorRating() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String mentor_id = (String) session.getAttribute("mentorID");
		int assessment =Integer.parseInt(request.getParameter("assessment"));
		int assessment_mark = Integer.parseInt(request.getParameter("assessmentmark"));
		int assignment = Integer.parseInt(request.getParameter("assignment"));
		int dress_code = Integer.parseInt(request.getParameter("dresscode"));
		String trainee_id = request.getParameter("trainee");
		String topic = request.getParameter("topic");
		int week = Integer.parseInt(request.getParameter("week"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date todayDate = new Date();
		String date = dateFormat.format(todayDate);
		MentorRatingBean mentorRatings = new MentorRatingBean();
		mentorRatings.setAssessment(assessment);
		mentorRatings.setAssessment_mark(assessment_mark);
		mentorRatings.setDress_code(dress_code);
		mentorRatings.setWeek(week);
		mentorRatings.setTrainee_id(trainee_id);
		mentorRatings.setAssignment(assignment);
		mentorRatings.setTopic(topic);
		MentorRatingDAO insertMentorRating = new MentorRatingDAO();
		String status = insertMentorRating.insertRatings(mentorRatings, mentor_id, date);
		
		if(status.equals("yes")) {
			out.write("<h2 style=color:green;>Your ratings for trainee are updated for this week.</h2>");
		}
		else if(status.equals("Already")) {
			out.write("<h2 style=color:red;>You already updated for this week. Please check the week you selected.</h2>");
		}
		else {
			out.write("<h2 style=color:red;>Your values are not updated. Please try again after some time.</h2>");
		}
	}
}
