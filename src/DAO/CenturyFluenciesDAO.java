package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bean.CenturyFluenciesBean;
import Connect.DBConnection;

public class CenturyFluenciesDAO {

	public String insertRatings(CenturyFluenciesBean cfBean, String mentorId, String traineeId, int week) {
		int otherPerspective = cfBean.getOtherPerson();
		int grammar = cfBean.getGrammar();
		int emailWriting = cfBean.getEmailWriting();
		int verbalCommunication = cfBean.getConversation();
		int questioning = cfBean.getQuestioning();
		int understanding = cfBean.getWrittenInfo();
		
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement weekCheck = connection.prepareStatement("select week from centuryfluenciesmentorrating where trainee_id = (?)");
			weekCheck.setString(1, traineeId);
			ResultSet weekSet = weekCheck.executeQuery();
			while(weekSet.next()) {
				if(week == weekSet.getInt(1)) {
					return "already";
				}
			}
			PreparedStatement insertDetails = connection.prepareStatement("insert into centuryfluenciesmentorrating values(?,?,?,?,?,?,?,?,?)");
			insertDetails.setString(1, mentorId);
			insertDetails.setString(2, traineeId);
			insertDetails.setInt(3, week);
			insertDetails.setInt(4, otherPerspective);
			insertDetails.setInt(5, grammar);
			insertDetails.setInt(6, verbalCommunication);
			insertDetails.setInt(7,understanding);
			insertDetails.setInt(8, questioning);
			insertDetails.setInt(9, emailWriting);
			int i = insertDetails.executeUpdate();
			if(i!=0) {
				return "yes";
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "no";
	}

}
