package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.GetTraineeDAO;

/**
 * Servlet implementation class GetTraineeList
 */
@WebServlet("/GetTraineeList")
public class GetTraineeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public GetTraineeList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*HttpSession session = request.getSession();
		String teamId = request.getParameter("team");
		GetTraineeDAO getTraineeDao = new GetTraineeDAO();
		ArrayList<Object> teamDetails = getTraineeDao.getTeamDetails(teamId);
		session.setAttribute("TeamName", teamDetails.get(0));
		session.setAttribute("mentorName", teamDetails.get(1));
		ArrayList<Object> traineeDetails = getTraineeDao.getTrainees(teamId);
		int numberOfTrainees =(Integer) traineeDetails.get(traineeDetails.size()-1);
		session.setAttribute("numberOfTrainees", numberOfTrainees);
		int count=0;
		for(int i = 1;i<=numberOfTrainees;i++) {
			session.setAttribute("TraineeID"+Integer.toString(i),traineeDetails.get(count));
			session.setAttribute("TraineeName"+Integer.toString(i),traineeDetails.get(count+1));
			count+=2;
		}
		response.sendRedirect("traineeprogress.jsp");*/
	}
}
