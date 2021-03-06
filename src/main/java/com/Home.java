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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside doget");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("inside dopost");
		UserBean userBean = new UserBean();
		List userInfoList = new ArrayList();
		String message = "";
		HttpSession session = request.getSession();
		String useridFromSession = (String) session.getAttribute("userid");
		String newUserFromSession = (String) session.getAttribute("newUser");
		String saveUserFromSession = (String) session.getAttribute("saveUser");
		String editUserFromSession = (String) session.getAttribute("editUser");
		String userId = request.getParameter("search");
		String newUser = request.getParameter("createUser");
		String saveUserInfo = request.getParameter("saveForm");
		if (newUser != null && newUser.equalsIgnoreCase("createUser")) {
			System.out.println("inside create new user");
			session.removeAttribute("userid");
			String newUserId = UUID.randomUUID().toString();
			session.setAttribute("userid", newUserId);
			session.setAttribute("newUser", "newUser");
			userBean.setUserId(newUserId);
			request.setAttribute("UsrBean", userBean);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CaseReport.jsp");
			dispatcher.forward(request, response);
		} else if (saveUserInfo != null && session.getAttribute("newUser") != null
				&& saveUserInfo.equalsIgnoreCase("saveForm")) {
			System.out.println("inside save form");
//			session.setAttribute("saveUser", "saveUser");
			String dateStr = request.getParameter("date");
			String timeStr = request.getParameter("time");
			String seqNoStr = request.getParameter("seqNo");
			String lastNameStr = request.getParameter("lastName");
			String firstNameStr = request.getParameter("firstName");
			String callbackNoStr = request.getParameter("callbackNo");
			String currentLocStr = request.getParameter("currentLoc");
			String ageStr = request.getParameter("age");
			if (ageStr != "") {
				int age = Integer.parseInt(ageStr);
				userBean.setAge(age);
			} else {
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
			if (psychosocialStr != null) {
				List<String> psychosocialList = new ArrayList<String>(psychosocialStr.length);
				for (String psychosocial : psychosocialStr) {
					psychosocialList.add(psychosocial);
				}
				otherPsychosocialStr = request.getParameter("OtherPsychosocial");
				if (otherPsychosocialStr != "") {
					psychosocialList.add(otherPsychosocialStr);
					userBean.setOtherPsychosocial(otherPsychosocialStr);
				}
				if (psychosocialList.contains("Other")) {
					psychosocialList.remove("Other");
				}
				psychosocialStr = psychosocialList.toArray(new String[0]);
				userBean.setPsychosocialList(psychosocialStr);
			} else {
				userBean.setPsychosocialList(new String[0]);
			}
			if (otherPsychosocialStr != null && !otherPsychosocialStr.isEmpty()) {
				userBean.setOtherPsychosocial(otherPsychosocialStr);
			}
			String medicationStr = (request.getParameter("medication")).trim();
			String[] referralStr = request.getParameterValues("referral");
			if (referralStr != null) {
				List<String> referralList = new ArrayList<String>(referralStr.length);
				for (String referral : referralStr) {
					referralList.add(referral);
				}
				otherReferralStr = request.getParameter("OtherReferral");
				if (otherReferralStr != "") {
					referralList.add(otherReferralStr);
					userBean.setOtherReferralStr(otherReferralStr);
				}
				if (referralList.contains("Other")) {
					referralList.remove("Other");
				}
				referralStr = referralList.toArray(new String[0]);
				userBean.setReferralList(referralStr);
			} else {
				userBean.setReferralList(new String[0]);
			}
			if (otherReferralStr != null && !otherReferralStr.isEmpty()) {
				userBean.setOtherReferralStr(otherReferralStr);
			}
			String educationStr = (request.getParameter("education")).trim();
			String followUpPlansStr = (request.getParameter("followUpPlans")).trim();
			String durationOfInterventionStr = request.getParameter("durationOfIntervention");
			String newCallStr = request.getParameter("newCall");
			String followUpStr = request.getParameter("followUp");
			String userid = (String) session.getAttribute("userid");
			session.setAttribute("userid", userid);
			userBean.setUserId(userid);
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
			try {
				if (saveUserFromSession != null && saveUserFromSession.equalsIgnoreCase("saveUser")
						&& editUserFromSession.equalsIgnoreCase("")) {
					DbManager.EditUserInDB(userBean);
					session.setAttribute("editUser", "editUser");
				} else {
					DbManager.SaveUserInDB(userBean);
					session.setAttribute("saveUser", "saveUser");
				}
				message = "Information saved. Thank you!";
				userBean.setMessage(message);
				request.setAttribute("UsrBean", userBean);
			} catch (NullPointerException e) {
				message = "Database is currently down. We are working on it to bring it up soon.Thank you for your patience.!";
				userBean.setMessage(message);
				request.setAttribute("UsrBean", userBean);
			} catch (SQLException e) {
				message = "Error accessing Database!";
				userBean.setMessage(message);
				request.setAttribute("UsrBean", userBean);
			} catch (ClassNotFoundException e) {
				request.setAttribute("UsrBean", userBean);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CaseReport.jsp");
			dispatcher.forward(request, response);
		} else if (userId != null) {
			System.out.println("inside search for a user");

			try {
				userInfoList = DbManager.getUserInfo(userId);
				if (userInfoList != null && !userInfoList.isEmpty()) {// user matched
					System.out.println("inside user matched");
					for (int i = 0; i < userInfoList.size(); i++) {
						String dateStr = (String) userInfoList.get(0);
						String timeStr = (String) userInfoList.get(1);
						String seqNoStr = (String) userInfoList.get(2);
						String lastNameStr = (String) userInfoList.get(3);
						String firstNameStr = (String) userInfoList.get(4);
						String callbackNoStr = (String) userInfoList.get(5);
						String currentLocStr = (String) userInfoList.get(6);
						int age = (int) userInfoList.get(7);
						String genderStr = (String) userInfoList.get(8);
						String teamRoleStr = (String) userInfoList.get(9);
						String unitStr = (String) userInfoList.get(10);
						String raceStr = (String) userInfoList.get(11);
						String ethnicityStr = (String) userInfoList.get(12);
						String newVsExStr = (String) userInfoList.get(13);
						String topConcernStr = (String) userInfoList.get(14);
						String otherConcernStr = (String) userInfoList.get(15);
						String covidStatusStr = (String) userInfoList.get(16);
						String pastDiagnosesStr = (String) userInfoList.get(17);
						String currentMedsStr = (String) userInfoList.get(18);
						String summarizeInterventionStr = (String) userInfoList.get(19);
						String medicationStr = (String) userInfoList.get(21);
						String educationStr = (String) userInfoList.get(23);
						String followUpPlansStr = (String) userInfoList.get(24);
						String durationOfInterventionStr = (String) userInfoList.get(25);
						String newCallStr = (String) userInfoList.get(26);
						String followUpStr = (String) userInfoList.get(27);
						session.setAttribute("userid", userId);
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
					}
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CaseReport.jsp");
					dispatcher.forward(request, response);
				} else {
					System.out.println("inside else of search");
					message = "Please enter correct UserId";
					userBean.setMessage(message);
					request.setAttribute("UsrBean", userBean);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Home.jsp");
					dispatcher.forward(request, response);
				}
			} catch (NullPointerException e) {
				message = "Database is currently down. We are working on it to bring it up soon.Thank you for your patience.!";
				userBean.setMessage(message);
				request.setAttribute("UsrBean", userBean);
			} catch (SQLException e1) {
				request.setAttribute("UsrBean", userBean);
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
