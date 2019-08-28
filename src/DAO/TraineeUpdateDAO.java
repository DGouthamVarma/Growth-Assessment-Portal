package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Bean.SettingsBean;
import Connect.DBConnection;

public class TraineeUpdateDAO {

	public void updateTrainee(SettingsBean updateTrainee, String currentTraineeID, String fromUrl) {
		
		if(fromUrl.equals("name")) {
			String newName = updateTrainee.getNewName();
			try {
				Connection connection = DBConnection.getConnection();
				PreparedStatement updateTraineeName = connection.prepareStatement("update trainee set trainee_name = (?) where trainee_id = (?)");
				updateTraineeName.setString(1, newName);
				updateTraineeName.setString(2, currentTraineeID);
				updateTraineeName.executeUpdate();
			}
			catch(Exception updateNameError) {
				System.out.println(updateNameError);
				System.out.println("Error in updating the name");
			}
		}
		
		else if(fromUrl.equals("email")) {
			String newEmail = updateTrainee.getNewEmail();
			try {
				Connection connection = DBConnection.getConnection();
				PreparedStatement updateTraineeName = connection.prepareStatement("update trainee set trainee_email = (?) where trainee_id = (?)");
				updateTraineeName.setString(1, newEmail);
				updateTraineeName.setString(2, currentTraineeID);
				updateTraineeName.executeUpdate();
			}
			catch(Exception updateEmailError) {
				System.out.println(updateEmailError);
				System.out.println("Error in updating the email");
			}
		}
		else if(fromUrl.equals("password")) {
			String newPassword = updateTrainee.getNewPassword();
			String oldPassword = updateTrainee.getOldPassword();
			try {
				Connection connection = DBConnection.getConnection();
				PreparedStatement getOldPassword = connection.prepareStatement("select password from trainee where trainee_id=(?)");
				getOldPassword.setString(1, currentTraineeID);
				ResultSet oldPasswordSet = getOldPassword.executeQuery();
				while(oldPasswordSet.next()) {
					if(oldPassword.equals(oldPasswordSet.getString(1))) {
						PreparedStatement setNewPassword = connection.prepareStatement("update trainee set password = (?) where trainee_id = (?)");
						setNewPassword.setString(1, newPassword);
						setNewPassword.setString(2, currentTraineeID);
						setNewPassword.executeUpdate();
					}
				}
			}
			catch(Exception updatePasswordError) {
				System.out.println(updatePasswordError);
				System.out.println("Error in updating the email");
			}
		}
		else if(fromUrl.equals("gender")) {
			String gender = updateTrainee.getGender();
			try {
				Connection connection = DBConnection.getConnection();
				PreparedStatement genderUpdate = connection.prepareStatement("update trainee set gender = (?) where trainee_id = (?)");
				genderUpdate.setString(1, gender);
				genderUpdate.setString(2, currentTraineeID);
				genderUpdate.executeUpdate();
			}
			catch(Exception updateGenderError) {
				System.out.println(updateGenderError);
			}
		}
	}

	public boolean getDetails(String currentTraineeID, String oldPassword) {
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(oldPassword);
			System.out.println(connection);
			PreparedStatement getStatement = connection.prepareStatement("select password from trainee where trainee_id = (?)");
			System.out.println(currentTraineeID);
			getStatement.setString(1, currentTraineeID);
			ResultSet details = getStatement.executeQuery();
			while(details.next()) {
				if(oldPassword.equals(details.getString(1))) {
					return true;
				}
			}
		}
		catch(Exception getPassError) {
			System.out.println(getPassError);
			System.out.println("Error");
		}
		return false;
	}

}
