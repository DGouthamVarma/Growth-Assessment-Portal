package Controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import DAO.MentorProfilePicUploadDAO;
import DAO.ProfilePicUploadDAO;

@WebServlet("/MentorImageUpload")
@MultipartConfig(maxFileSize = 20000000)
public class MentorImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MentorImageUpload() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String mentorId = (String)session.getAttribute("mentorID");
		String role = request.getParameter("role");
		InputStream inputStream = null;
		Part imagePart = request.getPart("profilephoto");
		if (imagePart != null) {
            inputStream = imagePart.getInputStream();
        }
		
		MentorProfilePicUploadDAO profilePicUpload = new MentorProfilePicUploadDAO();
		profilePicUpload.insertImage(inputStream, mentorId);
		
		if(role.equals("tech")) {
			response.sendRedirect("techmentor.jsp");
		}
		else if(role.equals("chief")) {
			response.sendRedirect("chiefmentor.jsp");
		}
		else if(role.equals("cf")) {
			response.sendRedirect("cfmentor.jsp");
		}
	}
}
