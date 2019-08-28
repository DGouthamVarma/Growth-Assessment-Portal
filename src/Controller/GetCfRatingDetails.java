package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.GetCfRatingDAO;

/**
 * Servlet implementation class GetCfRatingDetails
 */
@WebServlet("/GetCfRatingDetails")
public class GetCfRatingDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetCfRatingDetails() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String teamId = request.getParameter("teamid");
		HttpSession session = request.getSession();
		GetCfRatingDAO getCfRating = new GetCfRatingDAO();
		int traineeCount = getCfRating.getTraineeCount(teamId);
		ArrayList<Object> teamDetails = getCfRating.getDetails(teamId);
		session.setAttribute("numberOfTrainees", traineeCount);
		session.setAttribute("teamName", teamDetails.get(0));
		int count = 1;
		for(int i = 1; i <=traineeCount;i++) {
			session.setAttribute("traineeId"+Integer.toString(i), teamDetails.get(count));
			session.setAttribute("traineeName"+Integer.toString(i),teamDetails.get(count+1));
			count += 2;
		}
		response.sendRedirect("centuryfluenciesrating.jsp");
	}
}
