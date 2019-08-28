package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.AddTraineeBean;
import DAO.AddTraineeDAO;

@WebServlet("/AddTrainee")
public class AddTrainee extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public AddTrainee() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String traineeName = request.getParameter("traineename");
		String dateOfJoining = request.getParameter("joindate");
		String traineeGender = request.getParameter("gender");
		String team = request.getParameter("team");
		String traineeEmail = request.getParameter("traineeemail");
		
		AddTraineeBean addTraineeBean = new AddTraineeBean();
		addTraineeBean.setTraineeName(traineeName);
		addTraineeBean.setTeam(team);
		addTraineeBean.setDateOfJoining(dateOfJoining);
		addTraineeBean.setTraineeGender(traineeGender);
		addTraineeBean.setTraineeEmail(traineeEmail);
		AddTraineeDAO addTraineeDao = new AddTraineeDAO();
		addTraineeDao.addTrainee(addTraineeBean);
		
		response.sendRedirect("adminpage.jsp");
		
	}

}
