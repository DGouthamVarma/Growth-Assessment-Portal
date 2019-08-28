package DAO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import Bean.LoginBean;
import Connect.DBConnection;

public class TraineeDetailsDAO {
	public ArrayList<Object> getTraineeDetails(LoginBean loginBean) {
		String userEmail = loginBean.getUserEmail();
		
		ArrayList<Object> traineeDetails = new ArrayList<Object>();
		
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement traineeDetailsStatement = con.prepareStatement(
					"select Trainee_ID, Team_ID from trainee where trainee_email = (?)");
			traineeDetailsStatement.setString(1, userEmail);
			ResultSet traineeDetailsSet = traineeDetailsStatement.executeQuery();
			while(traineeDetailsSet.next()) {
				traineeDetails.add(traineeDetailsSet.getString("trainee_id"));
				traineeDetails.add(traineeDetailsSet.getString("team_id"));
			}
			PreparedStatement traineeTeamDetails = con.prepareStatement(
					"select teamname from team where team_id = (?)");
			traineeTeamDetails.setString(1, (String)traineeDetails.get(1));
			ResultSet traineeTeamSet = traineeTeamDetails.executeQuery();
			while(traineeTeamSet.next()) {
				traineeDetails.add(traineeTeamSet.getString("teamname"));
			}
			
			PreparedStatement traineeMentorDetails = con.prepareStatement(
					"select mentor.mentor_name from trainee inner join team on trainee.team_id = team.team_id inner join mentor on mentor.mentor_id = team.mentor_id where trainee_id =(?)");
			traineeMentorDetails.setString(1, (String)traineeDetails.get(0));
			ResultSet traineeMentorSet = traineeMentorDetails.executeQuery();
			while(traineeMentorSet.next()) {
				traineeDetails.add(traineeMentorSet.getString("mentor_name"));
			}
		}
		
		catch(Exception gettingDetailsError) {
			System.out.println(gettingDetailsError);
			System.out.println("Error in fetching trainee details from database");
		}
		return traineeDetails;
		
	}
}
