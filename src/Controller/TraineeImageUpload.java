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

import DAO.ProfilePicUploadDAO;

/**
 * Servlet implementation class TraineeImageUpload
 */
@WebServlet("/TraineeImageUpload")
@MultipartConfig(maxFileSize = 20000000)
public class TraineeImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TraineeImageUpload() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String traineeId = (String)session.getAttribute("traineeID");
		InputStream inputStream = null;
		Part imagePart = request.getPart("profilephoto");
		if (imagePart != null) {
            System.out.println(imagePart.getName());
            System.out.println(imagePart.getSize());
            System.out.println(imagePart.getContentType());
            inputStream = imagePart.getInputStream();
        }
		
		ProfilePicUploadDAO profilePicUpload = new ProfilePicUploadDAO();
		profilePicUpload.insertImage(inputStream, traineeId);
		
		response.sendRedirect("trainee.jsp");
		
	}

}
