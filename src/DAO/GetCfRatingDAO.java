package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Connect.DBConnection;

public class GetCfRatingDAO {

	public ArrayList<Object> getDetails(String teamId) {
		ArrayList<Object> details = new ArrayList<Object>();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select teamname from team where team_id = (?)");
			statement.setString(1, teamId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				details.add(resultSet.getString(1));
			}
			PreparedStatement traineeStatement = connection.prepareStatement("select trainee_id, trainee_name from trainee where team_id = (?)");
			traineeStatement.setString(1, teamId);
			ResultSet traineeSet = traineeStatement.executeQuery();
			while(traineeSet.next()) {
				details.add(traineeSet.getString(1));
				details.add(traineeSet.getString(2));
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return details;
		
		
	}

	public int getTraineeCount(String teamId) {
		int numberOfTrainees = 0;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select count(*) from trainee where team_id = (?)");
			statement.setString(1, teamId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				numberOfTrainees = resultSet.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return numberOfTrainees;
	}

}
