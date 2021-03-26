package com;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.ConnectionManager;

public class DbManager {

	public DbManager() {
	}

	public static void SaveUserInDB(UserBean userBean)
			throws ClassNotFoundException, SQLException {
		System.out.println("inside SaveUserInDB");
		Connection conn = ConnectionManager.getInstance().getConnection();
		ResourceBundle rbi = ResourceBundle.getBundle("sql");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			PreparedStatement pstmt = conn.prepareStatement(rbi
					.getString("insertUserInfoToDb"));
			pstmt.setString(1, userBean.getUserId());
			pstmt.setString(2, userBean.getDate());
			pstmt.setString(3, userBean.getTime());
			pstmt.setString(4, userBean.getSeqNo());
			pstmt.setString(5, userBean.getLastName());
			pstmt.setString(6, userBean.getFirstName());
			pstmt.setString(7, userBean.getCallbackNo());
			pstmt.setString(8, userBean.getCurrentLoc());
			pstmt.setInt(9, userBean.getAge());
			pstmt.setString(10, userBean.getGender());
			pstmt.setString(11, userBean.getTeamRole());
			pstmt.setString(12, userBean.getUnit());
			pstmt.setString(13, userBean.getRace());
			pstmt.setString(14, userBean.getEthnicity());
			pstmt.setString(15, userBean.getNewVsEx());
			pstmt.setString(16, userBean.getTopConcern());
			pstmt.setString(17, userBean.getOtherConcern());
			pstmt.setString(18, userBean.getCovidStatus());
			pstmt.setString(19, userBean.getPastDiagnoses());
			pstmt.setString(20, userBean.getCurrentMeds());
			pstmt.setString(21, userBean.getSummarizeIntervention());
			String[] psychosocial = userBean.getPsychosocialList();
			String delimiter = ",";
			String psychosocialStr="", prefix="";
			String referralStr="", prefixRef="";
			String joinedString = "";
		    for (int i=0; i<psychosocial.length; i++)
		    {
		       if(psychosocial.length == 1) {
		       psychosocialStr = psychosocial[i];
		       }else {
		       psychosocialStr += prefix + psychosocial[i];
		       prefix = delimiter;
		       }
		    }
		    
		    System.out.println("psychosocialStr in dbmanager:"+psychosocialStr);
		    pstmt.setString(22, psychosocialStr);
			pstmt.setString(23, userBean.getMedication());
			String[] referral = userBean.getReferralList();
		    for (int i=0; i<referral.length; i++)
		    {
		       if(referral.length == 1) {
		    	   referralStr = referral[i];
		       }else {
		    	   referralStr += prefixRef + referral[i];
		    	   prefixRef = delimiter;
		       }
		    }
		    System.out.println("referralStr in dbmanager:"+referralStr);
			pstmt.setString(24, referralStr);
			pstmt.setString(25, userBean.getEducation());
			pstmt.setString(26, userBean.getFollowUpPlans());
			pstmt.setString(27, userBean.getDurationOfIntervention());
			pstmt.setString(28, userBean.getNewCall());
			pstmt.setString(29, userBean.getFollowUp());
			pstmt.setTimestamp(30, timestamp);
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}

	}
	
	public static List getUserInfo(String userId) throws SQLException {
		System.out.println("inside dbmanager getuserinfo");
		ResourceBundle rb = ResourceBundle.getBundle("sql");
		Connection conn = ConnectionManager.getInstance().getConnection();
		String date = "";
		String time = "";
		String seqNo = "";
		String lastName = "";
		String firstName = "";
		String callbackNum = "";
		String currentLoc = "";
		int age = 0;
		String gender = "";
		String teamRole = "";
		String unit = "";
		String race = "";
		String ethnicity = "";
		String newVsExacerbation = "";
		String topConcern = "";
		String otherConcerns = "";
		String covidStatus = "";
		String pastPsychiatric = "";
		String curMedication = "";
		String sumIntervention = "";
		String psychosocial = "";
		String medEducation = "";
		String referral = "";
		String education = "";
		String followUpPlans = "";
		String durOfIntervention = "";
		String newCall = "";
		String followUp = "";
		
		List UserInfoList = new ArrayList();
		try {
			System.out.println("userId in dbmanager:"+userId);
			PreparedStatement prepStmt = conn.prepareStatement(rb.getString("selectUserInfo"));
			prepStmt.setString(1, userId);
			ResultSet rs = prepStmt.executeQuery();
			
			if (rs != null) {
				System.out.println("inside rs not null");
//				if (rs.next() == false) {
//					System.out.println("inside rs nullllllll--------");
//				}else {
//					System.out.println("inside else of rs not null");
				while (rs.next()) {
					System.out.println("inside rs next");
					date = rs.getString("Date");
					System.out.println("inside rs next date: "+date);
					time = rs.getString("Time");
					seqNo = rs.getString("SeqNumber");
					lastName = rs.getString("LastName");
					firstName = rs.getString("FirstName");
					callbackNum = rs.getString("CallbackNum");
					currentLoc = rs.getString("CurrentLoc");
					age = rs.getInt("Age");
					gender = rs.getString("Gender");
					teamRole = rs.getString("TeamRole");
					unit = rs.getString("Unit");
					race = rs.getString("Race");
					ethnicity = rs.getString("Ethnicity");
					newVsExacerbation = rs.getString("NewVsExacerbation");
					topConcern = rs.getString("TopConcern");
					otherConcerns = rs.getString("OtherConcerns");
					covidStatus = rs.getString("CovidStatus");
					pastPsychiatric = rs.getString("PastPsychiatric");
					curMedication = rs.getString("CurMedication");
					sumIntervention = rs.getString("SumIntervention");
					psychosocial = rs.getString("Psychosocial");
					medEducation = rs.getString("MedEducation");
					referral = rs.getString("Referral");
					education = rs.getString("Education");
					followUpPlans = rs.getString("FollowUpPlans");
					durOfIntervention = rs.getString("DurOfIntervention");
					newCall = rs.getString("NewCall");
					followUp = rs.getString("FollowUp");
//				}
				
//			System.out.println("date:"+date);
//			System.out.println("time:"+time);
//			System.out.println("seqNo:"+seqNo);
//			System.out.println("lastName:"+lastName);
//			System.out.println("firstName:"+firstName);
//			System.out.println("callbackNum:"+callbackNum);
//			System.out.println("currentLoc:"+currentLoc);
//			System.out.println("age:"+age);
//			System.out.println("gender:"+gender);
//			System.out.println("teamRole:"+teamRole);
//			System.out.println("unit:"+unit);
//			System.out.println("race:"+race);
//			System.out.println("ethnicity:"+ethnicity);
//			System.out.println("newVsExacerbation:"+newVsExacerbation);
//			System.out.println("topConcern:"+topConcern);
//			System.out.println("otherConcerns:"+otherConcerns);
//			System.out.println("covidStatus:"+covidStatus);
//			System.out.println("pastPsychiatric:"+pastPsychiatric);
//			System.out.println("curMedication:"+curMedication);
//			System.out.println("sumIntervention:"+sumIntervention);
//			System.out.println("psychosocial:"+psychosocial);
//			System.out.println("medEducation:"+medEducation);
//			System.out.println("referral:"+referral);
//			System.out.println("education:"+education);
//			System.out.println("followUpPlans:"+followUpPlans);
//			System.out.println("durOfIntervention:"+durOfIntervention);
//			System.out.println("newCall:"+newCall);
//			System.out.println("followUp:"+followUp);
//			
			UserInfoList.add(date);
			UserInfoList.add(time);
			UserInfoList.add(seqNo);
			UserInfoList.add(lastName);
			UserInfoList.add(firstName);
			UserInfoList.add(callbackNum);
			UserInfoList.add(currentLoc);
			UserInfoList.add(age);
			UserInfoList.add(gender);
			UserInfoList.add(teamRole);
			UserInfoList.add(unit);
			UserInfoList.add(race);
			UserInfoList.add(ethnicity);
			UserInfoList.add(newVsExacerbation);
			UserInfoList.add(topConcern);
			UserInfoList.add(otherConcerns);
			UserInfoList.add(covidStatus);
			UserInfoList.add(pastPsychiatric);
			UserInfoList.add(curMedication);
			UserInfoList.add(sumIntervention);
			UserInfoList.add(psychosocial);
			UserInfoList.add(medEducation);			
			UserInfoList.add(referral);
			UserInfoList.add(education);
			UserInfoList.add(followUpPlans);
			UserInfoList.add(durOfIntervention);
			UserInfoList.add(newCall);
			UserInfoList.add(followUp);
			
			}//end-while
			if (rs.next() == false) {
				System.out.println("inside rs nullllllll--------");
			}
			rs.close();
			prepStmt.close();
			
			}//if rs!=null
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conn = null;
			}
		}
//		for(int i=0;i<UserInfoList.size();i++) {
//			System.out.println("UserInfoList:"+UserInfoList.get(i).toString());
//		}
		
		return UserInfoList;
	}

}
