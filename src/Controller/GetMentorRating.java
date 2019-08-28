package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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

import com.google.gson.Gson;

import Connect.DBConnection;

@WebServlet("/GetMentorRating")
public class GetMentorRating extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetMentorRating() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gsonObj = new Gson();
		String traineeid = request.getParameter("id");
		String skill = request.getParameter("category");
		Map<Object,Object> map = null;
		List<Object> list = new ArrayList<Object>();
		String dataPoints = null;
		response.setContentType("application/json");
		PrintWriter out = response.getWriter(); 
		PreparedStatement statement = null;
		try{
			Connection connection = DBConnection.getConnection();
			if(skill.equals("marks")) {
				statement = connection.prepareStatement("select marks, topic from assessment where trainee_id = (?)");
				statement.setString(1, traineeid);
				int xVal;
				String yVal;
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()) {
					xVal = resultSet.getInt("marks");
					yVal = resultSet.getString("topic");
					map = new HashMap<Object,Object>();
					map.put("marks", xVal);
					map.put("topics", yVal);
					list.add(map);
					dataPoints = gsonObj.toJson(list);
				}
			}
			else if(skill.equals("othersperspective") || skill.equals("grammar") || skill.equals("verbalcommunication") || skill.equals("understandingwritteninfo")
					|| skill.equals("questioningability") || skill.equals("emailwriting")) {
				statement = connection.prepareStatement("select "+skill+" as total from centuryfluenciesmentorrating where trainee_id = (?)");
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
				System.out.println(list);
				System.out.println(dataPoints);
				
			}
			else {
				statement = connection.prepareStatement("select "+skill+" as total from technicalmentorrating where trainee_id = (?)");
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
				System.out.println(list);
				System.out.println(dataPoints);
			}
			
			if(dataPoints!=null) {
				out.write(dataPoints);
			}
			else {
				out.write("no data");
			}
			connection.close();
		}
		catch(SQLException e){
			System.out.println(e);
			dataPoints = null;
		}
	}

}
