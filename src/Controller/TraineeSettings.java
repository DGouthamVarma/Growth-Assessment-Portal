package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.SettingsBean;
import DAO.TraineeUpdateDAO;

/**
 * Servlet implementation class TraineeSettings
 */
@WebServlet("/TraineeSettings")
public class TraineeSettings extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TraineeSettings() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I am inside servlet");
		HttpSession session = request.getSession();
		String fromUrl = request.getParameter("category");
		System.out.println(fromUrl);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		/*String fromAttribute =(String) session.getAttribute("category");
		System.out.println(fromAttribute);*/
		/*System.out.println(fromUrl);*/
		String currentTraineeID = (String) session.getAttribute("traineeID");
		SettingsBean updateTrainee = new SettingsBean();
		TraineeUpdateDAO traineeUpdate = new TraineeUpdateDAO();
		if(fromUrl.equals("name")) {
			String newName = request.getParameter("updatename");
			System.out.println(newName);
			updateTrainee.setNewName(newName);
			traineeUpdate.updateTrainee(updateTrainee, currentTraineeID, fromUrl);
			out.write("<h4 style=color:green;>Your name is updated</h4>");
		}
		else if(fromUrl.equals("email")) {
			String newEmail = request.getParameter("updateemail");
			updateTrainee.setNewEmail(newEmail);
			traineeUpdate.updateTrainee(updateTrainee, currentTraineeID, fromUrl);
			out.write("<h4 style=color:green;>Your email is updated</h4>");
		}
		else if(fromUrl.equals("password")) {
			String oldPassword = request.getParameter("oldpassword");
			String newPassword = request.getParameter("newpassword");
			String confirmPassword = request.getParameter("confirmnewpassword");
			boolean isOldPassCorrect = traineeUpdate.getDetails(currentTraineeID, oldPassword); 
			if(isOldPassCorrect) {
				if(newPassword.equals(oldPassword)) {
					out.write("<h4 style=color:red;>Your new password and old password are same</h4>");
				}
				else if(newPassword.equals(confirmPassword)) {
					updateTrainee.setNewPassword(newPassword);;
					updateTrainee.setOldPassword(oldPassword);
					traineeUpdate.updateTrainee(updateTrainee, currentTraineeID, fromUrl); 
				    out.write("<h4 style=color:green;>Your password is updated</h4>");
				}
				else {
					out.write("<h4 style=color:red;>New password and Confirm password should match</h4>");
				}
			}
			else {
				out.write("<h4 style=color:red;>Entered old password is invalid</h4>");
			}
		}
		else if(fromUrl.equals("gender")) {
			String gender = request.getParameter("updategender");
			System.out.println(gender);
			updateTrainee.setGender(gender);
			traineeUpdate.updateTrainee(updateTrainee, currentTraineeID, fromUrl);
			out.write("<h4 style=color:green;>Your gender is updated</h4>");
		}
	}

}
