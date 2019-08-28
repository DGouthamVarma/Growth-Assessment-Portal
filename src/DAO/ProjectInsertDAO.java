package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bean.ProjectInsertBean;
import Connect.DBConnection;

public class ProjectInsertDAO {

	public String insertProject(ProjectInsertBean projectBean, String traineeTeamId, String traineeId) {
		String projectTitle = projectBean.getProjectTitle();
		String projectType = projectBean.getProjectType();
		String domain = projectBean.getDomain();
		int count=0;
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement checkProjects = connection.prepareStatement("select project_id from project where trainee_id = (?) and domain = (?)");
			checkProjects.setString(1, traineeId);
			checkProjects.setString(2, domain);
			ResultSet checkSet = checkProjects.executeQuery();
			while(checkSet.next()) {
				count += 1;
			}
			if(count==0) {
				CallableStatement projectInsert = connection.prepareCall("{call insertprojectdetails(?,?,?,?,?)}");
				projectInsert.setString(1, traineeTeamId);
				projectInsert.setString(2, traineeId);
				projectInsert.setString(3, domain);
				projectInsert.setString(4, projectTitle);
				projectInsert.setString(5, projectType);
				boolean isInserted = projectInsert.execute();
				if(!isInserted) {
					return "inserted";
				}
			}
			else {
				return "already";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "no";
	}

}
