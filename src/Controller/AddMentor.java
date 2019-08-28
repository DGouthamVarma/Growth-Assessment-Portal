package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.AddMentorBean;
import DAO.AddMentorDAO;

/**
 * Servlet implementation class AddMentor
 */
@WebServlet("/AddMentor")
public class AddMentor extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddMentor() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mentorName = request.getParameter("mentorname");
		String mentorRole = request.getParameter("mentorrole");
		String mentorGender = request.getParameter("gender");
		String mentorEmail = request.getParameter("mentoremail");
		
		AddMentorBean addMentorBean = new AddMentorBean();
		addMentorBean.setMentorName(mentorName);
		addMentorBean.setMentorRole(mentorRole);
		addMentorBean.setMentorGender(mentorGender);
		addMentorBean.setMentorEmail(mentorEmail);
		
		AddMentorDAO addMentorDao = new AddMentorDAO();
		addMentorDao.addMentor(addMentorBean);
		
		response.sendRedirect("adminpage.jsp");
	}

}
