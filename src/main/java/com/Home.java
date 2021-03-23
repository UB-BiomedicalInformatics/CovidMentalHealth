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
		System.out.println("uuserid passed"+request.getParameter("search"));
		if(request.getParameter("search")!=null) {
			System.out.println("yes searching for user");
			System.out.println("userid value entered:"+request.getParameter("search"));
		}
		if(request.getParameter("createUser")=="createUser") {
			System.out.println("creating new user");
		}
		String dateStr = request.getParameter("date");
		String timeStr = request.getParameter("time");
		String seqNoStr = request.getParameter("seqNo");
		String lastNameStr = request.getParameter("lastName");
		String firstNameStr = request.getParameter("firstName");
		String callbackNoStr = request.getParameter("callbackNo");
		String currentLocStr = request.getParameter("currentLoc");
		String message = "";
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
		HttpSession session = request.getSession();
		//String userId = (String)session.getAttribute("userId");
		String userId = request.getParameter("username");
		userId = UUID.randomUUID().toString();
		System.out.println("userId:"+userId);
		session.setAttribute("userid", userId);
	
//		System.out.println("dateStr: "+dateStr);
//		System.out.println("timeStr: "+timeStr);
//		System.out.println("lastNameStr: "+lastNameStr);
//		System.out.println("firstNameStr: "+firstNameStr);
//		System.out.println("currentLocStr: "+currentLocStr);
//		System.out.println("genderStr: "+genderStr);
//		System.out.println("teamRoleStr: "+teamRoleStr);
//		System.out.println("unitStr: "+unitStr);
//		System.out.println("raceStr: "+raceStr);
//		System.out.println("ethnicityStr: "+ethnicityStr);
//		System.out.println("newVsExStr: "+newVsExStr);
//		System.out.println("topConcernStr: "+topConcernStr);
//		System.out.println("otherConcernStr: "+otherConcernStr);
//		System.out.println("covidStatusStr: "+covidStatusStr);
//		System.out.println("pastDiagnosesStr: "+pastDiagnosesStr);
//		System.out.println("currentMedsStr: "+currentMedsStr);
//		System.out.println("summarizeInterventionStr: "+summarizeInterventionStr);
//		System.out.println("psychosocialStr: "+psychosocialStr);
//		System.out.println("medicationStr: "+medicationStr);
//		System.out.println("referralStr: "+referralStr);
//		System.out.println("educationStr: "+educationStr);
//		System.out.println("followUpPlansStr: "+followUpPlansStr);
//		System.out.println("durationOfInterventionStr: "+durationOfInterventionStr);
//		System.out.println("newCallStr: "+newCallStr);
//		System.out.println("followUpStr: "+followUpStr);
//		System.out.println("otherPsychosocialStr: "+otherPsychosocialStr);
//		System.out.println("otherReferralStr: "+otherReferralStr);
		
		
		userBean.setUserId(userId);
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
		request.setAttribute("UsrBean", userBean);
		
		try {
			DbManager.SaveUserInDB(userBean);
			message = "Information saved. Thank you!";
			userBean.setMessage(message);
		} catch (NullPointerException e) {
			message = "Database is currently down. We are working on it to bring it up soon.Thank you for your patience.!";
		} 
		catch (SQLException e) {
			message = "Error accessing Database!";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
		dispatcher.forward(request, response);
	}
	
}
