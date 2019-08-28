package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Bean.LoginBean;
import Connect.DBConnection;

public class ChiefMentorDetailsDAO {
	
	public String getMentorID(LoginBean loginBean) {
		String mentorEmail = loginBean.getUserEmail();
		
		
		String mentorID = null;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement getStatement = connection.prepareStatement("select mentor_id from mentor where mentor_email = (?)");
			getStatement.setString(1, mentorEmail);
			ResultSet detailsSet = getStatement.executeQuery();
			while(detailsSet.next()) {
				mentorID = detailsSet.getString(1);
			}
		}
		catch(Exception getError) {
			System.out.println(getError);
			System.out.println("Error is here");
		}
		return mentorID;
	}
	
	public ArrayList<Object> getDetailsForChief() {
		ArrayList<Object> detailsForChief = new ArrayList<Object>();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement getStatement = connection.prepareStatement("select team_id, teamname, mentor.mentor_name from team inner join mentor on team.mentor_id = mentor.mentor_id where flag=(?)");
			getStatement.setInt(1, 1);
			ResultSet detailsSet = getStatement.executeQuery();
			while(detailsSet.next()) {
				detailsForChief.add(detailsSet.getString("team_id"));
				detailsForChief.add(detailsSet.getString("teamname"));
				detailsForChief.add(detailsSet.getString(3));
			}
		}
		catch(Exception getError) {
			System.out.println(getError);
			System.out.println("Error is here");
		}
		return detailsForChief;
	}
	public int getCountOfCurrentTeams() {
		int countOfTeams = 0;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement getStatement = connection.prepareStatement("select count(*) from team where flag=(?)");
			getStatement.setInt(1, 1);
			ResultSet detailsSet = getStatement.executeQuery();
			while(detailsSet.next()) {
				countOfTeams = detailsSet.getInt(1);
			}
		}
		catch(Exception getError) {
			System.out.println(getError);
		}
		return countOfTeams;
	}

	public int getNumberOfTrainees(Object object) {
		String teamId = (String)object;
		int numberOfTrainees = 0;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select count(*) from trainee where team_id = (?)");
			statement.setString(1, teamId);
			ResultSet countTrainee = statement.executeQuery();
			while(countTrainee.next()) {
				numberOfTrainees = countTrainee.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return numberOfTrainees;
	}

	public ArrayList<Object> getTraineeNames(Object object) {
		String teamId = (String) object;
		ArrayList<Object> traineeList = new ArrayList<Object>();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select trainee_id, trainee_name from trainee where team_id = (?)");
			statement.setString(1, teamId);
			ResultSet traineeDetails = statement.executeQuery();
			while(traineeDetails.next()) {
				traineeList.add(traineeDetails.getString(1));
				traineeList.add(traineeDetails.getString(2));
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return traineeList;
	}

}
