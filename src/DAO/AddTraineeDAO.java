package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;

import Bean.AddTraineeBean;
import Connect.DBConnection;

public class AddTraineeDAO {

	public void addTrainee(AddTraineeBean addTraineeBean) {
		String traineeName = addTraineeBean.getTraineeName();
		String dateOfJoining = addTraineeBean.getDateOfJoining();
		String traineeGender = addTraineeBean.getTraineeGender();
		String team = addTraineeBean.getTeam();
		String traineeEmail = addTraineeBean.getTraineeEmail();
		
		try {
			Connection connection = DBConnection.getConnection();
			CallableStatement insertTrainee = connection.prepareCall("{call inserttraineedetails(?,?,?,?,?)}");
			insertTrainee.setString(1, traineeName);
			insertTrainee.setString(2, team);
			insertTrainee.setString(3, traineeEmail);
			insertTrainee.setString(4, dateOfJoining);
			insertTrainee.setString(5, traineeGender);
			boolean isInserted = insertTrainee.execute();
			System.out.println(isInserted);
		}
		catch(Exception traineeAddError) {
			System.out.println(traineeAddError);
			System.out.println("Error in adding trainee!");
		}
	}

}
