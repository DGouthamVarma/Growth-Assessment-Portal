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

@WebServlet("/TraineeProgressView")
public class TraineeProgressView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TraineeProgressView() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String traineeId = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("CurrentTraineeId", traineeId);
		
		GetTraineeDAO getTraineeDao = new GetTraineeDAO();
		ArrayList<Object> teamDetails = getTraineeDao.getTeamDetails(traineeId);
		session.setAttribute("teamName", teamDetails.get(0));
		session.setAttribute("traineeName", teamDetails.get(1));
		session.setAttribute("mentorName", teamDetails.get(2));
		
		
		response.sendRedirect("traineeprogress.jsp");
	}

}
