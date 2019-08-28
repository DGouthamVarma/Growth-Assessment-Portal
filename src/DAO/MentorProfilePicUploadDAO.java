package DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Connect.DBConnection;

public class MentorProfilePicUploadDAO {

	public void insertImage(InputStream inputStream, String mentorId) {
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement uploadPic = connection.prepareStatement("update mentor set profilepic = (?) where mentor_id = (?)");
			if(inputStream!=null) {
				uploadPic.setBlob(1, inputStream);
			}
			uploadPic.setString(2, mentorId);
			uploadPic.executeUpdate();
		}
		catch(Exception imageUploadError) {
			System.out.println(imageUploadError);
		}
		
	}

}
