package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.ForgotUsernameAndPassword;
import Connect.DBConnection;

public class ForgotDAO {

	public String validate(ForgotUsernameAndPassword forgotCredentialsBean) {
		String userEmail = forgotCredentialsBean.getUserEmail();
		
		try {
			Connection connection = DBConnection.getConnection();
			Statement emailCheckInMentor = connection.createStatement();
			ResultSet emailSetFromMentor = emailCheckInMentor.executeQuery("select mentor_email from mentor");
			while(emailSetFromMentor.next()) {
				System.out.println(emailSetFromMentor.getString(1));
				if(emailSetFromMentor.getString(1).equals(userEmail)) {
					return "mentor";
				}
			}
			Statement emailCheckInTrainee = connection.createStatement();
			ResultSet emailSetFromTrainee = emailCheckInTrainee.executeQuery("select trainee_email from trainee");
			while(emailSetFromTrainee.next()) {
				System.out.println(emailSetFromTrainee.getString(1));
				if(emailSetFromTrainee.getString(1).equals(userEmail)) {
					return "trainee";
				}
			}
		}
		catch(Exception emailCheckError) {
			System.out.println(emailCheckError);
		}
		return "none";
	}

	public String getPassword(ForgotUsernameAndPassword forgotCredentialsBean, String user) {
		String userEmail = forgotCredentialsBean.getUserEmail();
		try {
			Connection connection = DBConnection.getConnection();
			if(user.equals("mentor")) {
				PreparedStatement getUsername = connection.prepareStatement("select password from mentor where mentor_email = (?)");
				getUsername.setString(1, userEmail);
				ResultSet mentorName = getUsername.executeQuery();
				while(mentorName.next()) {
					return mentorName.getString(1);
				}
			}
			else {
				PreparedStatement getUsername = connection.prepareStatement("select password from trainee where trainee_email = (?)");
				getUsername.setString(1, userEmail);
				ResultSet traineeName = getUsername.executeQuery();
				while(traineeName.next()) {
					return traineeName.getString(1);
				}
			}
		}
		catch(Exception getNameError) {
			System.out.println(getNameError);
		}
		return "Could not find user";
	}
}
