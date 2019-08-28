package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Connect.DBConnection;

public class GetDataForAdminDAO {

	public int getCountOfMentors() {
		int numberOfMentors = 0;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement getCount = connection.prepareStatement("select count(*) from mentor where mentor_role =(?)");
			getCount.setString(1, "Technical");
			ResultSet countSet = getCount.executeQuery();
			while(countSet.next()) {
				numberOfMentors = countSet.getInt(1);
				System.out.println(numberOfMentors);
			}
		}
		catch(Exception error) {
			System.out.println(error);
		}
		return numberOfMentors;
	}

	public ArrayList<String> getMentorNames() {
		ArrayList<String> mentorNames = new ArrayList<String>();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement getMentors = connection.prepareStatement("select mentor_name, mentor_id from mentor where mentor_role =(?)");
			getMentors.setString(1, "Technical");
			ResultSet mentorSet = getMentors.executeQuery();
			while(mentorSet.next()) {
				mentorNames.add(mentorSet.getString("mentor_name"));
				mentorNames.add(mentorSet.getString("mentor_id"));
			}
		}
		catch(Exception error) {
			System.out.println(error);
		}
		return mentorNames;
	}

	public int getCountOfTeams() {
		int numberOfTeams = 0;
		try {
			Connection connection = DBConnection.getConnection();
			Statement getCount = connection.createStatement();
			ResultSet countSet = getCount.executeQuery("select count(*) from team");
			while(countSet.next()) {
				numberOfTeams = countSet.getInt(1);
				System.out.println(numberOfTeams);
			}
		}
		catch(Exception error) {
			System.out.println(error);
		}
		return numberOfTeams;
	}

	public ArrayList<String> getTeamNames() {
		ArrayList<String> teamNames = new ArrayList<String>();
		try {
			Connection connection = DBConnection.getConnection();
			Statement getTeams = connection.createStatement();
			ResultSet teamSet = getTeams.executeQuery("select teamname, team_id from team");
			while(teamSet.next()) {
				teamNames.add(teamSet.getString("teamname"));
				teamNames.add(teamSet.getString("team_id"));
			}
		}
		catch(Exception error) {
			System.out.println(error);
		}
		return teamNames;
	}

}
