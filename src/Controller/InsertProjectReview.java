package Controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Connect.DBConnection;

@WebServlet("/InsertProjectReview")
public class InsertProjectReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertProjectReview() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String traineeId = request.getParameter("id");
		String mentorId = (String)session.getAttribute("mentorID");
		String project_type = request.getParameter("projecttype");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String dateofreview = formatter.format(date);
		String comments = request.getParameter("comment");
		
		try {
			Connection connection = DBConnection.getConnection();
			CallableStatement statement = connection.prepareCall("{call insertprojectreview(?,?,?,?,?)}");
			statement.setString(1, traineeId);
			statement.setString(2, mentorId);
			statement.setString(3, project_type);
			statement.setString(4, dateofreview);
			statement.setString(5, comments);
			statement.execute();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		response.sendRedirect("projects.jsp");
	}
}
