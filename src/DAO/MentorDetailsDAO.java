package DAO;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Bean.LoginBean;
import Connect.DBConnection;
import oracle.sql.BLOB;


public class MentorDetailsDAO {
	
	public ArrayList<Object> getMentorDetails(LoginBean loginBean) {
		String userEmail = loginBean.getUserEmail();
		
		ArrayList<Object> mentorDetails = new ArrayList<Object>();
		
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement mentorDetailsStatement = con.prepareStatement(
					"select Mentor_ID from mentor where mentor_email = (?)");
			mentorDetailsStatement.setString(1, userEmail);
			ResultSet mentorDetailsSet = mentorDetailsStatement.executeQuery();
			while(mentorDetailsSet.next()) {
				mentorDetails.add(mentorDetailsSet.getString(1));
			}
			PreparedStatement mentorTeamDetails = con.prepareStatement(
					"select team_id, teamname from team where mentor_id = (?) and flag =(?)");
			mentorTeamDetails.setString(1, (String)mentorDetails.get(0));
			mentorTeamDetails.setInt(2, 1);
			ResultSet mentorTeamSet = mentorTeamDetails.executeQuery();
			while(mentorTeamSet.next()) {
				mentorDetails.add(mentorTeamSet.getString(1));
				mentorDetails.add(mentorTeamSet.getString(2));
			}
			
			PreparedStatement mentorTraineeDetails = con.prepareStatement(
					"select trainee_name, trainee_id from trainee where team_id = (?)");
			mentorTraineeDetails.setString(1, (String)mentorDetails.get(1));
			ResultSet mentorTraineeSet = mentorTraineeDetails.executeQuery();
			while(mentorTraineeSet.next()) {
				mentorDetails.add(mentorTraineeSet.getString("trainee_name"));
				mentorDetails.add(mentorTraineeSet.getString("trainee_id"));
			}
		}
		catch(Exception gettingDetailsError) {
			System.out.println(gettingDetailsError);
			System.out.println("Error in fetching mentor details from database");
		}
		return mentorDetails;
	}
}
