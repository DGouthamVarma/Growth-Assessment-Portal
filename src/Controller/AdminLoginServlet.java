package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.GetDataForAdminDAO;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession adminSession = request.getSession();
		
		GetDataForAdminDAO getDataForAdmin = new GetDataForAdminDAO();
		int numberOfMentors = getDataForAdmin.getCountOfMentors();
		
		ArrayList<String> mentorNames = getDataForAdmin.getMentorNames();
		
		int numberOfTeams = getDataForAdmin.getCountOfTeams();
		ArrayList<String> teamNames = getDataForAdmin.getTeamNames();
		
		if(userName.equals("admin") && password.equals("admin")) {
			adminSession.setAttribute("adminname", userName);
			adminSession.setAttribute("numberOfMentors", numberOfMentors);
			adminSession.setAttribute("numberOfTeams", numberOfMentors);
			int mentorCount = 0;
			for(int i=1; i<=numberOfMentors; i++) {
				adminSession.setAttribute("Mentor"+Integer.toString(i), mentorNames.get(mentorCount));
				adminSession.setAttribute("MentorID"+Integer.toString(i), mentorNames.get(mentorCount+1));
				mentorCount += 2;
			}
			int count = 0;
			for(int i=1; i<=numberOfTeams; i++) {
				adminSession.setAttribute("TeamName"+Integer.toString(i), teamNames.get(count));
				adminSession.setAttribute("TeamID"+Integer.toString(i), teamNames.get(count+1));
				count += 2;
			}
			response.sendRedirect("adminpage.jsp");
		}
	}

}
