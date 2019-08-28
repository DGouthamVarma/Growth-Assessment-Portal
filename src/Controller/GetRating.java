package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Connect.DBConnection;

/**
 * Servlet implementation class GetRating
 */
@WebServlet("/GetRating")
public class GetRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public GetRating() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Gson gsonObj = new Gson();
		String traineeid = request.getParameter("id");
		session.setAttribute("currentTraineeGraph", traineeid);
		String skill = request.getParameter("category");
		String getGrowth = null;
		if(skill.equals("fullProgress")) {
			getGrowth = "logicalthinking+codingstandards+bitbucketusage+othersperspective+"
					+ "grammar+verbalcommunication+understandingwritteninfo+questioningability"
					+ "+emailwriting+dresscode+attendance+timesheet+teambonding+"
					+ "assignments+assessments";
		}
		else {
			getGrowth = skill;
		}
		Map<Object,Object> map = null;
		List<Object> list = new ArrayList<Object>();
		String dataPoints = null;
		response.setContentType("application/json");
		PrintWriter out = response.getWriter(); 
		PreparedStatement statement = null;
		try{
			Connection connection = DBConnection.getConnection();
			statement = connection.prepareStatement("select "+getGrowth+" as total from traineerating where trainee_id = (?)");
			statement.setString(1, traineeid);
			int yVal;
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				yVal = resultSet.getInt("total");
				map = new HashMap<Object,Object>();  
				map.put("total", yVal); 
				list.add(map);
				dataPoints = gsonObj.toJson(list);
			}
			if(dataPoints!=null) {
				System.out.println(dataPoints);
				out.write(dataPoints);
			}
			else {
				out.write("There is no data in database to display. Please update");
			}
			connection.close();
		}
		catch(SQLException e){
			System.out.println(e);
			dataPoints = null;
		}
	}
}
