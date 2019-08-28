package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Bean.LoginBean;
import Connect.DBConnection;

public class TraineesUnderMentorDAO {
	public int getCountOfTrainees(LoginBean loginBean) {
		String userEmail = loginBean.getUserEmail();
		ArrayList<Object> mentorDetails = new ArrayList<Object>();
		int countOfTrainees = 0;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement mentorDetailsStatement = con.prepareStatement(
					"select Mentor_ID from mentor where mentor_email = (?)");
			mentorDetailsStatement.setString(1, userEmail);
			ResultSet mentorDetailsSet = mentorDetailsStatement.executeQuery();
			while(mentorDetailsSet.next()) {
				mentorDetails.add(mentorDetailsSet.getString("mentor_id"));
			}
			PreparedStatement mentorTeamDetails = con.prepareStatement(
					"select team_id from team where mentor_id = (?) and flag =1");
			mentorTeamDetails.setString(1, (String)mentorDetails.get(0));
			ResultSet mentorTeamSet = mentorTeamDetails.executeQuery();
			while(mentorTeamSet.next()) {
				mentorDetails.add(mentorTeamSet.getString("team_id"));
			}
			
			PreparedStatement mentorTraineeDetails = con.prepareStatement(
					"select count(*) from trainee where team_id = (?)");
			mentorTraineeDetails.setString(1, (String)mentorDetails.get(1));
			ResultSet mentorTraineeSet = mentorTraineeDetails.executeQuery();
			while(mentorTraineeSet.next()) {
				countOfTrainees = mentorTraineeSet.getInt(1);
			}
		}
		
		catch(Exception gettingDetailsError) {
			System.out.println(gettingDetailsError);
			System.out.println("Error in fetching trainee count from database");
		}
		return countOfTrainees;
	}
}
