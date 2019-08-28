package DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Connect.DBConnection;

public class ProfilePicUploadDAO {

	public void insertImage(InputStream inputStream, String traineeId) {
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement uploadPic = connection.prepareStatement("update trainee set profilepic = (?) where trainee_id = (?)");
			if(inputStream!=null) {
				uploadPic.setBlob(1, inputStream);
			}
			uploadPic.setString(2, traineeId);
			uploadPic.executeUpdate();
		}
		catch(Exception imageUploadError) {
			System.out.println(imageUploadError);
		}
		
	}

}
