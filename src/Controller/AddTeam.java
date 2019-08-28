package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.AddTeamBean;
import DAO.AddTeamDAO;

/**
 * Servlet implementation class AddTeam
 */
@WebServlet("/AddTeam")
public class AddTeam extends HttpServlet {
	private static final long serialVersionUID = 1L;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeam() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teamName = request.getParameter("teamname");
		String batchStartDate = request.getParameter("startdate");
		String mentor = request.getParameter("mentor");
		
		AddTeamBean addTeamBean = new AddTeamBean();
		addTeamBean.setTeamName(teamName);
		addTeamBean.setBatchStartDate(batchStartDate);
		addTeamBean.setMentor(mentor);
		
		AddTeamDAO addTeamDao = new AddTeamDAO();
		addTeamDao.addTeam(addTeamBean);
		
		response.sendRedirect("adminpage.jsp");
	}
}
