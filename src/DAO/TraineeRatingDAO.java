package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.TraineeRatingBean;
import Connect.DBConnection;

public class TraineeRatingDAO {

	public String insertRatingInDB(TraineeRatingBean selfRatingBean, String date, int week, String traineeId) {
		int logicalThinking = selfRatingBean.getLogicalThinking();
		int codingStandards = selfRatingBean.getCodingStandards();
		int bitBucket = selfRatingBean.getBitBucket();
		int assignments = selfRatingBean.getAssignments();
		int assessments = selfRatingBean.getAssessments();
		int otherPerson = selfRatingBean.getOtherPerson();
		int grammar = selfRatingBean.getGrammar();
		int conversation = selfRatingBean.getConversation();
		int writtenInfo = selfRatingBean.getWrittenInfo();
		int questioning = selfRatingBean.getQuestioning();
		int emailWriting = selfRatingBean.getEmailWriting();
		int dressCode = selfRatingBean.getDressCode();
		int attendance = selfRatingBean.getAttendance();
		int timesheet = selfRatingBean.getTimesheet();
		int teambonding = selfRatingBean.getTeambonding();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement checkWeek = connection.prepareStatement("select week from traineerating where trainee_id=(?)");
			checkWeek.setString(1, traineeId);
			ResultSet weekSet = checkWeek.executeQuery();
			while(weekSet.next()) {
				if(week==weekSet.getInt(1)) {
					return "Already";
				}
			}
			CallableStatement insertRating = connection.prepareCall("{call inserttraineerating(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			insertRating.setString(1, traineeId);
			insertRating.setString(2, date);
			insertRating.setInt(3, week);
			insertRating.setInt(4, logicalThinking);
			insertRating.setInt(5, codingStandards);
			insertRating.setInt(6, bitBucket);
			insertRating.setInt(7, otherPerson);
			insertRating.setInt(8, grammar);
			insertRating.setInt(9, conversation);
			insertRating.setInt(10, writtenInfo);
			insertRating.setInt(11, questioning);
			insertRating.setInt(12, emailWriting);
			insertRating.setInt(13, dressCode);
			insertRating.setInt(14, attendance);
			insertRating.setInt(15, timesheet);
			insertRating.setInt(16, teambonding);
			insertRating.setInt(17, assignments);
			insertRating.setInt(18, assessments);
			boolean isInserted = insertRating.execute();
			if(!isInserted) {
				return "yes";
			}
		}
		catch(Exception insertRatingError) {
			System.out.println(insertRatingError);
		}
		return "no";
	}
}
