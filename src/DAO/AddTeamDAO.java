package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Bean.AddTeamBean;
import Connect.DBConnection;

public class AddTeamDAO {

	public void addTeam(AddTeamBean addTeamBean) {
		String teamName = addTeamBean.getTeamName();
		String batchStartDate = addTeamBean.getBatchStartDate();
		String mentor = addTeamBean.getMentor();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement flagSet = connection.prepareStatement("update team set flag = (?) where mentor_id = (?)");
			flagSet.setInt(1, 0);
			flagSet.setString(2, mentor);
			flagSet.executeUpdate();
			
			CallableStatement insertTeam = connection.prepareCall("{call insertteamdetails(?,?,?,?)}");
			insertTeam.setString(1, teamName);
			insertTeam.setString(2, mentor);
			insertTeam.setString(3, batchStartDate);
			insertTeam.setInt(4, 1);
			insertTeam.execute();

		}
		catch(Exception teamInsertionError) {
			System.out.println(teamInsertionError);
			System.out.println("Error in inserting team");
		}	
	}
}
