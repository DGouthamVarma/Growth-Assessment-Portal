package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.LoginBean;
import DAO.CfMentorDAO;
import DAO.ChiefMentorDetailsDAO;
import DAO.LoginDAO;
import DAO.MentorDetailsDAO;
import DAO.TraineeDetailsDAO;
import DAO.TraineesUnderMentorDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userEmail = request.getParameter("useremail");
		String password = request.getParameter("password");
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUserEmail(userEmail);
		loginBean.setPassword(password);
		
		LoginDAO loginDao = new LoginDAO();
		ArrayList<String> validationResult = new ArrayList<String>();
		validationResult = loginDao.validateUser(loginBean);
		if(validationResult.get(2).equals("Trainee")) {
			TraineeDetailsDAO traineeDetailsDao = new TraineeDetailsDAO();
			session.setAttribute("currentTrainee", validationResult.get(0));
			loginBean.setUserEmail(validationResult.get(1));
			ArrayList<Object> traineeDetails = traineeDetailsDao.getTraineeDetails(loginBean);
			session.setAttribute("traineePassword", password);
			session.setAttribute("traineeID", traineeDetails.get(0));
			session.setAttribute("traineeTeamID", traineeDetails.get(1));
			session.setAttribute("traineeTeam", traineeDetails.get(2));
			session.setAttribute("traineeMentorName", traineeDetails.get(3));
			response.sendRedirect("trainee.jsp");
		}
		else if(validationResult.get(2).equals("Mentor")) {
			MentorDetailsDAO mentorDetailsDao = new MentorDetailsDAO();
			session.setAttribute("currentMentor", validationResult.get(0));
			loginBean.setUserEmail(validationResult.get(1));
			ArrayList<Object> mentorDetails = mentorDetailsDao.getMentorDetails(loginBean);
			TraineesUnderMentorDAO traineeCountDao = new TraineesUnderMentorDAO();
			int countOfTrainees = traineeCountDao.getCountOfTrainees(loginBean);
			session.setAttribute("mentorPassword", password);
			session.setAttribute("mentorID", mentorDetails.get(0));
			session.setAttribute("mentorTeamID", mentorDetails.get(1));
			session.setAttribute("mentorTeam", mentorDetails.get(2));
			int i = 1;
			int count = 0;
			while(i <= countOfTrainees) {
				session.setAttribute("Trainee"+Integer.toString(i), mentorDetails.get(count+3));
				session.setAttribute("TraineeId"+Integer.toString(i), mentorDetails.get(count+4));
				count += 2;
				i+=1;
			}
			session.setAttribute("numberOfTrainees", countOfTrainees);
			response.sendRedirect("techmentor.jsp");
		}
		else if(validationResult.get(2).equals("Chief")) {
			ChiefMentorDetailsDAO chiefMentorDao = new ChiefMentorDetailsDAO();
			session.setAttribute("currentMentor", validationResult.get(0));
			loginBean.setUserEmail(validationResult.get(1));
			
			int numberOfTeams = chiefMentorDao.getCountOfCurrentTeams();
			String mentorID = chiefMentorDao.getMentorID(loginBean);
			session.setAttribute("mentorID", mentorID);
			session.setAttribute("numberOfCurrent", numberOfTeams);
			session.setAttribute("mentorPassword", password);
			ArrayList<Object> chiefMentorDetails = chiefMentorDao.getDetailsForChief();
			int count = 0;
			for(int i =1; i<=numberOfTeams; i++) {
				session.setAttribute("TeamID"+Integer.toString(i), chiefMentorDetails.get(count));
				session.setAttribute("TeamName"+Integer.toString(i), chiefMentorDetails.get(count+1));
				session.setAttribute("MentorName"+Integer.toString(i), chiefMentorDetails.get(count+2));
				int numberOfTrainees = chiefMentorDao.getNumberOfTrainees(chiefMentorDetails.get(count));
				session.setAttribute("numberOfTrainees"+Integer.toString(i), numberOfTrainees);
				ArrayList<Object> traineeNames = chiefMentorDao.getTraineeNames(chiefMentorDetails.get(count));
				int number = 0;
				for(int j = 1;j<=numberOfTrainees;j++) {
					session.setAttribute("TraineeId"+Integer.toString(i)+Integer.toString(j),traineeNames.get(number));
					session.setAttribute("TraineeName"+Integer.toString(i)+Integer.toString(j),traineeNames.get(number+1));
					number += 2;
				}
				count += 3;
			}
			response.sendRedirect("chiefmentor.jsp");
		}
		else if(validationResult.get(2).equals("21st century fluencies")) {
			CfMentorDAO cfMentorDao = new CfMentorDAO();
			session.setAttribute("currentMentor", validationResult.get(0));
			loginBean.setUserEmail(validationResult.get(1));
			
			int numberOfTeams = cfMentorDao.getCountOfCurrentTeams();
			String mentorID = cfMentorDao.getMentorID(loginBean);
			System.out.println(mentorID);
			session.setAttribute("mentorID", mentorID);
			session.setAttribute("numberOfCurrent", numberOfTeams);
			session.setAttribute("mentorPassword", password);
			ArrayList<Object> cfMentorDetails = cfMentorDao.getDetailsForChief();
			int count = 0;
			for(int i =1; i<=numberOfTeams; i++) {
				session.setAttribute("TeamID"+Integer.toString(i), cfMentorDetails.get(count));
				session.setAttribute("TeamName"+Integer.toString(i), cfMentorDetails.get(count+1));
				session.setAttribute("MentorName"+Integer.toString(i), cfMentorDetails.get(count+2));
				int numberOfTrainees = cfMentorDao.getNumberOfTrainees(cfMentorDetails.get(count));
				session.setAttribute("numberOfTrainees"+Integer.toString(i), numberOfTrainees);
				ArrayList<Object> traineeNames = cfMentorDao.getTraineeNames(cfMentorDetails.get(count));
				int number = 0;
				for(int j = 1;j<=numberOfTrainees;j++) {
					session.setAttribute("TraineeId"+Integer.toString(i)+Integer.toString(j),traineeNames.get(number));
					session.setAttribute("TraineeName"+Integer.toString(i)+Integer.toString(j),traineeNames.get(number+1));
					number += 2;
				}
				count += 3;
			}
			response.sendRedirect("cfmentor.jsp");
		}
		else {
			response.sendRedirect("index.jsp");
			System.out.println("User doesnot exist in the database");
		}
	}

}
