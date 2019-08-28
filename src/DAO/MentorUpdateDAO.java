package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bean.SettingsBean;
import Connect.DBConnection;

public class MentorUpdateDAO {

	public void updateMentor(SettingsBean updateMentor, String currentMentorID, String fromUrl) {
		if(fromUrl.equals("name")) {
			String newName = updateMentor.getNewName();
			System.out.println(newName);
			try {
				Connection connection = DBConnection.getConnection();
				PreparedStatement updateMentorName = connection.prepareStatement("update mentor set mentor_name = (?) where mentor_id = (?)");
				updateMentorName.setString(1, newName);
				updateMentorName.setString(2, currentMentorID);
				updateMentorName.executeUpdate();
			}
			catch(Exception updateNameError) {
				System.out.println(updateNameError);
				System.out.println("Error in updating the name");
			}
		}
		
		else if(fromUrl.equals("email")) {
			String newEmail = updateMentor.getNewEmail();
			try {
				Connection connection = DBConnection.getConnection();
				PreparedStatement updateMentorName = connection.prepareStatement("update mentor set mentor_email = (?) where mentor_id = (?)");
				updateMentorName.setString(1, newEmail);
				updateMentorName.setString(2, currentMentorID);
				updateMentorName.executeUpdate();
			}
			catch(Exception updateEmailError) {
				System.out.println(updateEmailError);
				System.out.println("Error in updating the email");
			}
		}
		else if(fromUrl.equals("password")) {
			String newPassword = updateMentor.getNewPassword();
			String oldPassword = updateMentor.getOldPassword();
			try {
				Connection connection = DBConnection.getConnection();
				PreparedStatement getOldPassword = connection.prepareStatement("select password from mentor where mentor_id=(?)");
				getOldPassword.setString(1, currentMentorID);
				ResultSet oldPasswordSet = getOldPassword.executeQuery();
				while(oldPasswordSet.next()) {
					if(oldPassword.equals(oldPasswordSet.getString(1))) {
						PreparedStatement setNewPassword = connection.prepareStatement("update mentor set password = (?) where mentor_id = (?)");
						setNewPassword.setString(1, newPassword);
						setNewPassword.setString(2, currentMentorID);
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
			String gender = updateMentor.getGender();
			try {
				Connection connection = DBConnection.getConnection();
				PreparedStatement genderUpdate = connection.prepareStatement("update mentor set gender = (?) where mentor_id = (?)");
				genderUpdate.setString(1, gender);
				genderUpdate.setString(2, currentMentorID);
				genderUpdate.executeUpdate();
			}
			catch(Exception updateGenderError) {
				System.out.println(updateGenderError);
			}
		}
	}
}
