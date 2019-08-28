package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.SettingsBean;
import DAO.MentorUpdateDAO;
import DAO.TraineeUpdateDAO;

/**
 * Servlet implementation class MentorSettings
 */
@WebServlet("/MentorSettings")
public class MentorSettings extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MentorSettings() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String fromUrl = request.getParameter("category");
		String currentMentorID = (String) session.getAttribute("mentorID");
		
		System.out.println(currentMentorID);
		
		SettingsBean updateMentor = new SettingsBean();
		MentorUpdateDAO mentorUpdate = new MentorUpdateDAO();
		if(fromUrl.equals("name")) {
			String newName = request.getParameter("updatename");		
			updateMentor.setNewName(newName);
			mentorUpdate.updateMentor(updateMentor, currentMentorID, fromUrl);
			session.setAttribute("currentMentor", newName);
			response.sendRedirect("techmentor.jsp");
		}
		
		else if(fromUrl.equals("email")) {
			String newEmail = request.getParameter("updateemail");
			updateMentor.setNewEmail(newEmail);
			mentorUpdate.updateMentor(updateMentor, currentMentorID, fromUrl);
			response.sendRedirect("techmentor.jsp");
		}
		else if(fromUrl.equals("password")) {
			String oldPassword = request.getParameter("oldpassword");
			String newPassword = request.getParameter("newpassword");
			String confirmPassword = request.getParameter("confirmnewpassword");
			if(newPassword.equals(confirmPassword)) {
				updateMentor.setNewPassword(newPassword);;
				updateMentor.setOldPassword(oldPassword);
				mentorUpdate.updateMentor(updateMentor, currentMentorID, fromUrl);
				response.sendRedirect("techmentor.jsp");
			}
			else {
				/*response.sendRedirect("index.jsp");*/
			}
		}
		else if(fromUrl.equals("gender")) {
			String gender = request.getParameter("updategender");
			updateMentor.setGender(gender);
			mentorUpdate.updateMentor(updateMentor, currentMentorID, fromUrl);
			response.sendRedirect("techmentor.jsp");
		}

	}

}
