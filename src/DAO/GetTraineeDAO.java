package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Connect.DBConnection;

public class GetTraineeDAO {

	/*public ArrayList<Object> getTrainees(String teamId) {
		ArrayList<Object> traineesList = new ArrayList<Object>();
		int count = 0;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select trainee_id, trainee_name from trainee where team_id = (?)");
			statement.setString(1, teamId);
			ResultSet traineeSet = statement.executeQuery();
			while(traineeSet.next()) {
				count+=1;
				traineesList.add(traineeSet.getString(1));
				traineesList.add(traineeSet.getString(2));
			}
			traineesList.add(count);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return traineesList;
	}
*/
	public ArrayList<Object> getTeamDetails(String traineeId) {
		ArrayList<Object> teamDetails = new ArrayList<Object>();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select teamname, mentor_id, trainee_name from trainee inner join team on trainee.team_id = team.team_id where trainee_id = (?)");
			statement.setString(1, traineeId);
			String mentor_id = null;
			ResultSet teamName = statement.executeQuery();
			while(teamName.next()) {
				teamDetails.add(teamName.getString(1));
				mentor_id = teamName.getString(2);
				teamDetails.add(teamName.getString(3));
			}
			PreparedStatement mentorNameStatement = connection.prepareStatement("select mentor_name from mentor where mentor_id = (?)");
			mentorNameStatement.setString(1, mentor_id);
			ResultSet mentorName = mentorNameStatement.executeQuery();
			while(mentorName.next()) {
				teamDetails.add(mentorName.getString(1));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return teamDetails;
	}

}
