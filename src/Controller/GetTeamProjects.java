package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.GetProjectsDAO;

/**
 * Servlet implementation class GetTeamProjects
 */
@WebServlet("/GetTeamProjects")
public class GetTeamProjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetTeamProjects() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String teamId = request.getParameter("team");
		GetProjectsDAO getProjectsData = new GetProjectsDAO();
		int numberOfWebProjects = getProjectsData.getNumberOfTrainees(teamId);
		System.out.println(numberOfWebProjects);
		session.setAttribute("numberofwebprojects", numberOfWebProjects);
		ArrayList<Object> projectData = getProjectsData.getProjectsInfo(teamId);
		session.setAttribute("teamname",projectData.get(0));
		session.setAttribute("mentorname", projectData.get(1));
		int count = 2;
		for(int i=1;i<=numberOfWebProjects;i++) {
			session.setAttribute("TraineeWebName"+Integer.toString(i), projectData.get(count));
			session.setAttribute("TraineeId"+Integer.toString(i), projectData.get(count + 1));
			count = count + 2;
		}
		response.sendRedirect("projects.jsp");
	}

}
