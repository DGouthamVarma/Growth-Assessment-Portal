package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bean.MentorRatingBean;
import Connect.DBConnection;

public class MentorRatingDAO {

	public String insertRatings(MentorRatingBean mentorRatings, String mentor_id, String date) {
		int assessment = mentorRatings.getAssessment();
		int assessment_mark = mentorRatings.getAssessment_mark();
		int assignment = mentorRatings.getAssignment();
		int dress_code = mentorRatings.getDress_code();
		String trainee_id = mentorRatings.getTrainee_id();
		int week = mentorRatings.getWeek();
		String topic = mentorRatings.getTopic();
		try {
			Connection connection = DBConnection.getConnection();
			PreparedStatement checkWeek = connection.prepareStatement("select week from technicalmentorrating where mentor_id=(?) and trainee_id = (?)");
			checkWeek.setString(1, mentor_id);
			checkWeek.setString(2, trainee_id);
			ResultSet weekSet = checkWeek.executeQuery();
			while(weekSet.next()) {
				if(week==weekSet.getInt(1)) {
					return "Already";
				}
			}
			CallableStatement insertRating = connection.prepareCall("{call inserttechmentorrating(?,?,?,?,?,?,?)}");
			insertRating.setString(1, mentor_id);
			insertRating.setString(2, trainee_id);
			insertRating.setString(3, date);
			insertRating.setInt(4, week);
			insertRating.setInt(5, dress_code);
			insertRating.setInt(6, assignment);
			insertRating.setInt(7, assessment);
			boolean isInserted = insertRating.execute();
			PreparedStatement checkWeekInMark = connection.prepareStatement("select week from assessment where mentor_id=(?) and trainee_id = (?)");
			checkWeekInMark.setString(1, mentor_id);
			checkWeekInMark.setString(2, trainee_id);
			ResultSet weekInMarkSet = checkWeekInMark.executeQuery();
			while(weekInMarkSet.next()) {
				if(week==weekInMarkSet.getInt(1)) {
					return "Already";
				}
			}
			CallableStatement insertMark = connection.prepareCall("{call insertassessmentdetails(?,?,?,?,?,?)}");
			insertMark.setString(1, mentor_id);
			insertMark.setString(2, trainee_id);
			insertMark.setInt(3, assessment_mark);
			insertMark.setInt(4, week);
			insertMark.setString(5, date);
			insertMark.setString(6, topic);
			boolean isMarkInserted = insertMark.execute();
			if(!isInserted && !isMarkInserted) {
				return "yes";
			}
		}
		catch(Exception insertError) {
			System.out.println(insertError);
		}
		return "no";
	}

}
