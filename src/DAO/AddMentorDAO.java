package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;

import Bean.AddMentorBean;
import Connect.DBConnection;
public class AddMentorDAO {

	public void addMentor(AddMentorBean addMentorBean) {
		String mentorName = addMentorBean.getMentorName();
		String mentorRole = addMentorBean.getMentorRole();
		String mentorGender = addMentorBean.getMentorGender();
		String mentorEmail = addMentorBean.getMentorEmail();
		
		try {
			Connection connection = DBConnection.getConnection();
			CallableStatement insertMentor = connection.prepareCall("{call insertmentordetails(?,?,?,?)}");
			insertMentor.setString(1, mentorName);
			insertMentor.setString(2, mentorEmail);
			insertMentor.setString(3, mentorGender);
			insertMentor.setString(4, mentorRole);
			boolean isInserted = insertMentor.execute();
			System.out.println(isInserted);
		}
		catch(Exception addMentorError) {
			System.out.println(addMentorError);
			System.out.println("Adding mentor error occured!");
		}
		
	}

}
