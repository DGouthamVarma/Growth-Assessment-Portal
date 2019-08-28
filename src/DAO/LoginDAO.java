package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Bean.LoginBean;
import Connect.DBConnection;

public class LoginDAO {

	public ArrayList<String> validateUser(LoginBean loginBean) {
		String userEmail = loginBean.getUserEmail();
		String password = loginBean.getPassword();
		ArrayList<String> userValues = new ArrayList<String>();
		try {
			Connection con = DBConnection.getConnection();
			Statement traineeSelect = con.createStatement();
			ResultSet traineeDetails = traineeSelect.executeQuery("select trainee_email, trainee_name, password from trainee");
			while(traineeDetails.next()) {
				String traineeEmailInDB = traineeDetails.getString("trainee_email");
				String traineePassInDB = traineeDetails.getString("password");
				String traineeNameInDB = traineeDetails.getString("trainee_name");
				if(traineeEmailInDB.toLowerCase().equals(userEmail.toLowerCase()) && traineePassInDB.equals(password)) {
					userValues.add(traineeNameInDB);
					userValues.add(traineeEmailInDB);
					userValues.add("Trainee");
					return userValues;
				}
			}
			Statement mentorSelect = con.createStatement();
			ResultSet mentorDetails = mentorSelect.executeQuery("select mentor_email, mentor_name, password, mentor_role from mentor");
			while(mentorDetails.next()) {
				String mentorEmailInDB = mentorDetails.getString("mentor_email");
				String mentorNameInDB = mentorDetails.getString("mentor_name");
				String mentorPassInDB = mentorDetails.getString("password");
				String mentorRoleInDB = mentorDetails.getString("mentor_role");
				if((mentorEmailInDB.toLowerCase().equals(userEmail.toLowerCase()) && mentorPassInDB.equals(password))) {
					if(mentorRoleInDB.equals("Chief")) {
						userValues.add(mentorNameInDB);
						userValues.add(mentorEmailInDB);
						userValues.add("Chief");
						return userValues;
					}
					else if(mentorRoleInDB.equals("21st century fluencies")){
						userValues.add(mentorNameInDB);
						userValues.add(mentorEmailInDB);
						userValues.add("21st century fluencies");
						return userValues;
					}
					else {
						userValues.add(mentorNameInDB);
						userValues.add(mentorEmailInDB);
						userValues.add("Mentor");
						return userValues;
					}
				}	
			}
		}
		catch(Exception validateError){
			System.out.println(validateError);
		}
		userValues.add("error");
		userValues.add("error");
		userValues.add("error");
		return userValues;
	}

}
