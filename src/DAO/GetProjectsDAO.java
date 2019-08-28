package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Connect.DBConnection;


public class GetProjectsDAO {

	public int getNumberOfTrainees(String teamId) {
		int numberOfWebProjects = 0;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement getNumber = connection.prepareStatement("select count(*) from trainee where team_id = (?)");
			getNumber.setString(1, teamId);
			ResultSet countSet = getNumber.executeQuery();
			while(countSet.next()) {
				numberOfWebProjects = countSet.getInt(1);
			}
		}
		catch(Exception getNumberError) {
			System.out.println(getNumberError);
		}
		return numberOfWebProjects;
	}

	public ArrayList<Object> getProjectsInfo(String teamId) {
		ArrayList<Object> projectData = new ArrayList<Object>();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement getTeamDetails = connection.prepareStatement("select teamname, mentor.mentor_name from team inner join mentor on mentor.mentor_id = team.mentor_id where team_id = (?)");
			getTeamDetails.setString(1, teamId);
			ResultSet teamSet = getTeamDetails.executeQuery();
			while(teamSet.next()) {
				projectData.add(teamSet.getString(1));
				projectData.add(teamSet.getString(2));
			}
			PreparedStatement getTrainees = connection.prepareStatement("select trainee_id from trainee where team_id = (?)");
			getTrainees.setString(1, teamId);
			ResultSet traineeSet = getTrainees.executeQuery();
			while(traineeSet.next()) {
				PreparedStatement getTraineeName = connection.prepareStatement("select trainee_name from trainee where trainee_id = (?)");
				getTraineeName.setString(1, traineeSet.getString(1));
				ResultSet traineesSet = getTraineeName.executeQuery();
				while(traineesSet.next()) {
					projectData.add(traineesSet.getString(1));
				}
				projectData.add(traineeSet.getString(1));
			}
			/*PreparedStatement getDProjects = connection.prepareStatement("select project_name from project where team_id = (?) and project_type = (?)");
			getDProjects.setString(1, teamId);
			getDProjects.setString(2, "Desktop");
			ResultSet desktopProjectsSet = getDProjects.executeQuery();
			while(desktopProjectsSet.next()) {
				System.out.println("Inside Desktops");
				projectData.add(desktopProjectsSet.getString(1));
			}*/
			/*PreparedStatement getWProjects = connection.prepareStatement("select project_name from project where team_id = (?) and project_type = (?)");
			getWProjects.setString(1, teamId);
			getWProjects.setString(2, "web");
			ResultSet webProjectsSet = getWProjects.executeQuery();
			while(webProjectsSet.next()) {
				System.out.println(webProjectsSet.getString(1));
				projectData.add(webProjectsSet.getString(1));
			}*/
		}
		catch(Exception exception) {
			System.out.println(exception);
		}
		return projectData;
	}

}
