package com;
import java.io.IOException;
import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeServlet
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside doget");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside dopost");
		UserBean userBean = new UserBean();
		List userInfoList  = new ArrayList();
		String message = "";
		HttpSession session = request.getSession();
		System.out.println("uuserid passed: "+request.getParameter("search"));
		System.out.println("New User: "+request.getParameter("createUser"));
		System.out.println("Save User: "+request.getParameter("saveForm"));
		System.out.println("get newuser from session :"+session.getAttribute("newUser"));
		System.out.println("get userid from session :"+session.getAttribute("userid"));
		String useridFromSession = (String)session.getAttribute("userid");
		String newUserFromSession = (String)session.getAttribute("newUser");
		
		if(newUserFromSession!=null) {
			System.out.println("nottttttttttt nulllllllllllllllllllll");
		}else {
			System.out.println("nulllllllllllllllllllll");
		}
//		if(newUserFromSession.equalsIgnoreCase("")) {
//			System.out.println("emptyyyyyyyyyyyyyyyy");
//		}else {
//			System.out.println("nottttttttttttt emptyyyyyyyyyyyyyyyyy");
//		}
		String userId = request.getParameter("search");
		System.out.println("111111111111111111");
		String newUser = request.getParameter("createUser");
		System.out.println("222222222222222");
		String saveUserInfo = request.getParameter("saveForm");
		System.out.println("333333333333333");
		if(newUser!=null && (session.getAttribute("newUser")==null) && (newUser.equalsIgnoreCase("createUser"))) {
			System.out.println("inside create new user");
			System.out.println("before session");
			String newUserId = UUID.randomUUID().toString();
			System.out.println("newUserId:"+newUserId);
			session.setAttribute("userid", newUserId);
			session.setAttribute("newUser", "newUser");
			System.out.println("after setting session variable");
			System.out.println("before set usrbean userid");
			userBean.setUserId(newUserId);
			System.out.println("after set usrbean userid");
			request.setAttribute("UsrBean", userBean);
			System.out.println("before create new user request dispatch");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CaseReport.jsp");
			dispatcher.forward(request, response);
		}else if(saveUserInfo!=null && session.getAttribute("newUser")!=null && saveUserInfo.equalsIgnoreCase("saveForm")) {
			System.out.println("inside save form");
			session.setAttribute("saveUser", "saveUser");
			String dateStr = request.getParameter("date");
			String timeStr = request.getParameter("time");
			String seqNoStr = request.getParameter("seqNo");
			String lastNameStr = request.getParameter("lastName");
			String firstNameStr = request.getParameter("firstName");
			String callbackNoStr = request.getParameter("callbackNo");
			String currentLocStr = request.getParameter("currentLoc");
		
			String ageStr = request.getParameter("age");
			if(ageStr!="") {
			int age = Integer.parseInt(ageStr);
			userBean.setAge(age);
			}else {
			userBean.setAge(0);
			}
			String genderStr = request.getParameter("gender");
			String teamRoleStr = request.getParameter("teamRole");
			String unitStr = request.getParameter("unit");
			String raceStr = request.getParameter("race");
			String ethnicityStr = request.getParameter("ethnicity");
			String newVsExStr = request.getParameter("newVsEx");
			String topConcernStr = request.getParameter("topConcern");
			String otherConcernStr = (request.getParameter("otherConcern")).trim();
			String covidStatusStr = request.getParameter("covidStatus");
			String pastDiagnosesStr = (request.getParameter("pastDiagnoses")).trim();
			String currentMedsStr = (request.getParameter("currentMeds")).trim();
			String summarizeInterventionStr = (request.getParameter("summarizeIntervention")).trim();
			String[] psychosocialStr = request.getParameterValues("psychosocial");
			String otherPsychosocialStr = "";
			String otherReferralStr = "";
			if(psychosocialStr!=null) {
				List<String> psychosocialList = new ArrayList<String>(psychosocialStr.length);
		        for (String psychosocial:psychosocialStr) {
		        	psychosocialList.add(psychosocial);
		        }
		        otherPsychosocialStr = request.getParameter("OtherPsychosocial");
				if(otherPsychosocialStr!="") {
					psychosocialList.add(otherPsychosocialStr);
					userBean.setOtherPsychosocial(otherPsychosocialStr);
				}
				if(psychosocialList.contains("Other")) {
					psychosocialList.remove("Other");
				}
				psychosocialStr = psychosocialList.toArray(new String[0]);
				userBean.setPsychosocialList(psychosocialStr);
			}else {
				userBean.setPsychosocialList(new String[0]);
			}
			if(otherPsychosocialStr!=null&&!otherPsychosocialStr.isEmpty()) {
				userBean.setOtherPsychosocial(otherPsychosocialStr);
			}
			String medicationStr = (request.getParameter("medication")).trim();
			String[] referralStr = request.getParameterValues("referral");
			if(referralStr!=null) {
				List<String> referralList = new ArrayList<String>(referralStr.length);
		        for (String referral:referralStr) {
		        	referralList.add(referral);
		        }
		        otherReferralStr = request.getParameter("OtherReferral");
				if(otherReferralStr!="") {
					referralList.add(otherReferralStr);
					userBean.setOtherReferralStr(otherReferralStr);
				}
				if(referralList.contains("Other")) {
					referralList.remove("Other");
				}
				referralStr = referralList.toArray(new String[0]);
				userBean.setReferralList(referralStr);
			}else {
				userBean.setReferralList(new String[0]);
			}
			if(otherReferralStr!=null&&!otherReferralStr.isEmpty()) {
				userBean.setOtherReferralStr(otherReferralStr);
			}
			String educationStr = (request.getParameter("education")).trim();
			String followUpPlansStr = (request.getParameter("followUpPlans")).trim();
			String durationOfInterventionStr = request.getParameter("durationOfIntervention");
			String newCallStr = request.getParameter("newCall");
			String followUpStr = request.getParameter("followUp");
//			HttpSession session = request.getSession();
			String userid = (String)session.getAttribute("userid");
			session.setAttribute("userid", userid);
//			System.out.println("after setting session variable");
//			System.out.println("dateStr: "+dateStr);
//			System.out.println("timeStr: "+timeStr);
//			System.out.println("lastNameStr: "+lastNameStr);
//			System.out.println("firstNameStr: "+firstNameStr);
//			System.out.println("currentLocStr: "+currentLocStr);
//			System.out.println("genderStr: "+genderStr);
//			System.out.println("teamRoleStr: "+teamRoleStr);
//			System.out.println("unitStr: "+unitStr);
//			System.out.println("raceStr: "+raceStr);
//			System.out.println("ethnicityStr: "+ethnicityStr);
//			System.out.println("newVsExStr: "+newVsExStr);
//			System.out.println("topConcernStr: "+topConcernStr);
//			System.out.println("otherConcernStr: "+otherConcernStr);
//			System.out.println("covidStatusStr: "+covidStatusStr);
//			System.out.println("pastDiagnosesStr: "+pastDiagnosesStr);
//			System.out.println("currentMedsStr: "+currentMedsStr);
//			System.out.println("summarizeInterventionStr: "+summarizeInterventionStr);
//			System.out.println("psychosocialStr: "+psychosocialStr);
//			System.out.println("medicationStr: "+medicationStr);
//			System.out.println("referralStr: "+referralStr);
//			System.out.println("educationStr: "+educationStr);
//			System.out.println("followUpPlansStr: "+followUpPlansStr);
//			System.out.println("durationOfInterventionStr: "+durationOfInterventionStr);
////			System.out.println("newCallStr: "+newCallStr);
////			System.out.println("followUpStr: "+followUpStr);
////			System.out.println("otherPsychosocialStr: "+otherPsychosocialStr);
////			System.out.println("otherReferralStr: "+otherReferralStr);
//			
			System.out.println("before set usrbean userid");
			userBean.setUserId(userid);
			System.out.println("after set usrbean userid");
			userBean.setDate(dateStr);	
			userBean.setTime(timeStr);
			userBean.setSeqNo(seqNoStr);
			userBean.setLastName(lastNameStr);
			userBean.setFirstName(firstNameStr);
			userBean.setCallbackNo(callbackNoStr);
			userBean.setCurrentLoc(currentLocStr);
			userBean.setGender(genderStr);
			userBean.setTeamRole(teamRoleStr);
			userBean.setUnit(unitStr);
			userBean.setRace(raceStr);
			userBean.setEthnicity(ethnicityStr);
			userBean.setNewVsEx(newVsExStr);
			userBean.setTopConcern(topConcernStr);
			userBean.setOtherConcern(otherConcernStr);
			userBean.setCovidStatus(covidStatusStr);
			userBean.setPastDiagnoses(pastDiagnosesStr);
			userBean.setCurrentMeds(currentMedsStr);
			userBean.setSummarizeIntervention(summarizeInterventionStr);
			userBean.setMedication(medicationStr);
			userBean.setEducation(educationStr);
			userBean.setFollowUpPlans(followUpPlansStr);
			userBean.setDurationOfIntervention(durationOfInterventionStr);
			userBean.setNewCall(newCallStr);
			userBean.setFollowUp(followUpStr);
//			request.setAttribute("UsrBean", userBean);
			try {
				if(useridFromSession!=null) {
					DbManager.EditUserInDB(userBean);
				}else {
				DbManager.SaveUserInDB(userBean);
				}
				message = "Information saved. Thank you!";
				userBean.setMessage(message);
				request.setAttribute("UsrBean", userBean);
			} catch (NullPointerException e) {
				message = "Database is currently down. We are working on it to bring it up soon.Thank you for your patience.!";
				userBean.setMessage(message);
				request.setAttribute("UsrBean", userBean);
			} 
			catch (SQLException e) {
				message = "Error accessing Database!";
				userBean.setMessage(message);
				request.setAttribute("UsrBean", userBean);
			} catch (ClassNotFoundException e) {
				request.setAttribute("UsrBean", userBean);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("before create new user request dispatch");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CaseReport.jsp");
			dispatcher.forward(request, response);
		}
		else if(userId!=null) {
			System.out.println("inside search for a user");		
			session.setAttribute("editUser", "editUser");
				try {
			userInfoList  = DbManager.getUserInfo(userId);
			if(userInfoList!=null && !userInfoList.isEmpty()) {//user matched
				System.out.println("inside user matched");	
				for(int i=0;i<userInfoList.size();i++) {
					String dateStr = (String)userInfoList.get(0);	
//					System.out.println("111 :"+dateStr);
					String timeStr = (String)userInfoList.get(1);	
//					System.out.println("222 :"+timeStr);
					String seqNoStr = (String)userInfoList.get(2);	
//					System.out.println("333 :"+seqNoStr);
					String lastNameStr = (String)userInfoList.get(3);
//					System.out.println("444 :"+lastNameStr);
					String firstNameStr = (String)userInfoList.get(4);
//					System.out.println("555 :"+firstNameStr);
					String callbackNoStr = (String)userInfoList.get(5);	
//					System.out.println("667 :"+callbackNoStr);
					String currentLocStr = (String)userInfoList.get(6);	
//					System.out.println("777 :"+currentLocStr);
//				
//					String ageStr = request.getParameter("age");
//					if(ageStr!="") {
//					int age = Integer.parseInt(ageStr);
//					userBean.setAge(age);
//					}else {
//					userBean.setAge(0);
//					}
					int age = (int)userInfoList.get(7);	
//					System.out.println("888 :"+age);
					String genderStr = (String)userInfoList.get(8);
//					System.out.println("999 :"+genderStr);
					String teamRoleStr = (String)userInfoList.get(9);
//					System.out.println("aaa :"+teamRoleStr);
					String unitStr = (String)userInfoList.get(10);
//					System.out.println("bbb :"+unitStr);
					String raceStr = (String)userInfoList.get(11);
//					System.out.println("ccc :"+raceStr);
					String ethnicityStr = (String)userInfoList.get(12);
//					System.out.println("ddd :"+ethnicityStr);
					String newVsExStr = (String)userInfoList.get(13);
//					System.out.println("eee :"+newVsExStr);
					String topConcernStr = (String)userInfoList.get(14);
//					System.out.println("fff :"+topConcernStr);
					String otherConcernStr = (String)userInfoList.get(15);
//					System.out.println("ggg :"+otherConcernStr);
					String covidStatusStr = (String)userInfoList.get(16);
//					System.out.println("hhh :"+covidStatusStr);
					String pastDiagnosesStr = (String)userInfoList.get(17);
//					System.out.println("iii :"+pastDiagnosesStr);
					String currentMedsStr = (String)userInfoList.get(18);
//					System.out.println("jjj :"+currentMedsStr);
					String summarizeInterventionStr = (String)userInfoList.get(19);
//					System.out.println("kkk :"+summarizeInterventionStr);
//					String[] psychosocialStr = request.getParameterValues("psychosocial");
//					String otherPsychosocialStr = "";
//					String otherReferralStr = "";
//					if(psychosocialStr!=null) {
//						List<String> psychosocialList = new ArrayList<String>(psychosocialStr.length);
//				        for (String psychosocial:psychosocialStr) {
//				        	psychosocialList.add(psychosocial);
//				        }
//				        otherPsychosocialStr = request.getParameter("OtherPsychosocial");
//						if(otherPsychosocialStr!="") {
//							psychosocialList.add(otherPsychosocialStr);
//							userBean.setOtherPsychosocial(otherPsychosocialStr);
//						}
//						if(psychosocialList.contains("Other")) {
//							psychosocialList.remove("Other");
//						}
//						psychosocialStr = psychosocialList.toArray(new String[0]);
//						userBean.setPsychosocialList(psychosocialStr);
//					}else {
//						userBean.setPsychosocialList(new String[0]);
//					}
//					if(otherPsychosocialStr!=null&&!otherPsychosocialStr.isEmpty()) {
//						userBean.setOtherPsychosocial(otherPsychosocialStr);
//					}
					String medicationStr = (String)userInfoList.get(21);
//					System.out.println("lll :"+medicationStr);
//					String[] referralStr = request.getParameterValues("referral");
//					if(referralStr!=null) {
//						List<String> referralList = new ArrayList<String>(referralStr.length);
//				        for (String referral:referralStr) {
//				        	referralList.add(referral);
//				        }
//				        otherReferralStr = request.getParameter("OtherReferral");
//						if(otherReferralStr!="") {
//							referralList.add(otherReferralStr);
//							userBean.setOtherReferralStr(otherReferralStr);
//						}
//						if(referralList.contains("Other")) {
//							referralList.remove("Other");
//						}
//						referralStr = referralList.toArray(new String[0]);
//						userBean.setReferralList(referralStr);
//					}else {
//						userBean.setReferralList(new String[0]);
//					}
//					if(otherReferralStr!=null&&!otherReferralStr.isEmpty()) {
//						userBean.setOtherReferralStr(otherReferralStr);
//					}
					String educationStr = (String)userInfoList.get(23);
					String followUpPlansStr = (String)userInfoList.get(24);
					String durationOfInterventionStr = (String)userInfoList.get(25);
					String newCallStr = (String)userInfoList.get(26);
					String followUpStr = (String)userInfoList.get(27);
//					HttpSession session = request.getSession();
//					//String userId = (String)session.getAttribute("userId");
//					//String userId = request.getParameter("username");
////					String userId = UUID.randomUUID().toString();
////					System.out.println("userId:"+userId);
					session.setAttribute("userid", userId);
//				
//						System.out.println("dateStr: "+dateStr);
//					System.out.println("timeStr: "+timeStr);
//					System.out.println("lastNameStr: "+lastNameStr);
//					System.out.println("firstNameStr: "+firstNameStr);
//					System.out.println("currentLocStr: "+currentLocStr);
//					System.out.println("age: "+age);
//					System.out.println("genderStr: "+genderStr);
//					System.out.println("teamRoleStr: "+teamRoleStr);
//					System.out.println("unitStr: "+unitStr);
//					System.out.println("raceStr: "+raceStr);
//					System.out.println("ethnicityStr: "+ethnicityStr);
//					System.out.println("newVsExStr: "+newVsExStr);
//					System.out.println("topConcernStr: "+topConcernStr);
//					System.out.println("otherConcernStr: "+otherConcernStr);
//					System.out.println("covidStatusStr: "+covidStatusStr);
//					System.out.println("pastDiagnosesStr: "+pastDiagnosesStr);
//					System.out.println("currentMedsStr: "+currentMedsStr);
//					System.out.println("summarizeInterventionStr: "+summarizeInterventionStr);
//////					System.out.println("psychosocialStr: "+psychosocialStr);
//					System.out.println("medicationStr: "+medicationStr);
//////					System.out.println("referralStr: "+referralStr);
//					System.out.println("educationStr: "+educationStr);
//					System.out.println("followUpPlansStr: "+followUpPlansStr);
//					System.out.println("durationOfInterventionStr: "+durationOfInterventionStr);
//					System.out.println("newCallStr: "+newCallStr);
//					System.out.println("followUpStr: "+followUpStr);
////					System.out.println("otherPsychosocialStr: "+otherPsychosocialStr);
////					System.out.println("otherReferralStr: "+otherReferralStr);
//					
//					
					userBean.setUserId(userId);
					userBean.setDate(dateStr);	
					userBean.setTime(timeStr);
					userBean.setSeqNo(seqNoStr);
					userBean.setLastName(lastNameStr);
					userBean.setFirstName(firstNameStr);
					userBean.setCallbackNo(callbackNoStr);
					userBean.setCurrentLoc(currentLocStr);
					userBean.setAge(age);
					userBean.setGender(genderStr);
					userBean.setTeamRole(teamRoleStr);
					userBean.setUnit(unitStr);
					userBean.setRace(raceStr);
					userBean.setEthnicity(ethnicityStr);
					userBean.setNewVsEx(newVsExStr);
					userBean.setTopConcern(topConcernStr);
					userBean.setOtherConcern(otherConcernStr);
					userBean.setCovidStatus(covidStatusStr);
					userBean.setPastDiagnoses(pastDiagnosesStr);
					userBean.setCurrentMeds(currentMedsStr);
					userBean.setSummarizeIntervention(summarizeInterventionStr);
					userBean.setMedication(medicationStr);
					userBean.setEducation(educationStr);
					userBean.setFollowUpPlans(followUpPlansStr);
					userBean.setDurationOfIntervention(durationOfInterventionStr);
					userBean.setNewCall(newCallStr);
					userBean.setFollowUp(followUpStr);
//					request.setAttribute("UsrBean", userBean);
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CaseReport.jsp");
				dispatcher.forward(request, response);
			}else {
				System.out.println("insid else of search");
				message = "Please enter correct UserId";
				userBean.setMessage(message);
				request.setAttribute("UsrBean", userBean);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
				dispatcher.forward(request, response);
			}
				}catch (NullPointerException e) {
					message = "Database is currently down. We are working on it to bring it up soon.Thank you for your patience.!";
					userBean.setMessage(message);
					request.setAttribute("UsrBean", userBean);
				}
			//System.out.println("userid value entered:"+request.getParameter("search"));
					catch (SQLException e1) {
					request.setAttribute("UsrBean", userBean);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
//		if(request.getParameter("createUser")=="createUser") {
//			System.out.println("creating new user");
//		}
			}
	
}
