package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Connect.DBConnection;

@WebServlet("/GetProjectReview")
public class GetProjectReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetProjectReview() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String traineeId = request.getParameter("id");
		HttpSession session = request.getSession();
		int numberOfComments = 0;
		ArrayList<Object> listOfComments = new ArrayList<Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement count = connection.prepareStatement("select count(*) from projectreview where trainee_id = (?)");
			count.setString(1, traineeId);
			ResultSet countSet = count.executeQuery();
			while(countSet.next()) {
				numberOfComments = countSet.getInt(1);
			}
			PreparedStatement statement = connection.prepareStatement("select mentor_name, project_type, dateofreview, comments from projectreview inner join mentor on mentor.mentor_id = projectreview.mentor_id where trainee_id = (?)");
			statement.setString(1, traineeId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				listOfComments.add(resultSet.getString(1));
				listOfComments.add(resultSet.getString(2));
				listOfComments.add(formatter.format(resultSet.getDate(3)));
				listOfComments.add(resultSet.getString(4));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		session.setAttribute("numberOfComments", numberOfComments);
		int index = 0;
		for(int i=1;i<=numberOfComments;i++) {
			session.setAttribute("MentorName"+Integer.toString(i), listOfComments.get(index));
			session.setAttribute("ProjectType"+Integer.toString(i), listOfComments.get(index+1));
			session.setAttribute("Date"+Integer.toString(i), listOfComments.get(index+2));
			session.setAttribute("Comment"+Integer.toString(i), listOfComments.get(index+3));
			index += 4;
		}
		response.sendRedirect("projectreviews.jsp");
	}

}
