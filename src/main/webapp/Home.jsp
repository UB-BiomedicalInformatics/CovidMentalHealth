<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.UserBean"%>
<%@ page import="javax.servlet.RequestDispatcher"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.DbManager"%>
<%@ page import="java.util.Arrays"%>

<jsp:useBean id="UsrBean" scope="request" class="com.UserBean" />
<jsp:useBean id="userid" scope="session" class="java.lang.String" />

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="css/layouts/pure-min.css">
<link rel="stylesheet" href="css/layouts/grids-responsive-min.css">
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" href="css/layouts/searchOverlay.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<title>CovidMentalHealth</title>
<%
UserBean userBean = (UserBean)request.getAttribute("UsrBean");
//String userId = request.getRemoteUser();
//out.println("UserID:"+userId);
String userId = (String)session.getAttribute("userid");
String errorMessage = "";
//session.setAttribute("userId",userId);
List userInfoList  = new ArrayList();
if(userId!=null){
	try {
userInfoList  = DbManager.getUserInfo(userId);
	}catch (NullPointerException e) {
		errorMessage = "Database is currently down. We are working on it to bring it up soon.Thank you for your patience.!";
	}
}
String date="";
String time="";
String seqNo="";
String lastName="";
String firstName="";
String callbackNo="";
String currentLoc="";
int age=0;
String gender="";
String teamRole="";
String unit="";
String race="";
String ethnicity="";
String newVsEx="";
String topConcern="";
String otherConcern="";
String covidStatus="";
String pastDiagnoses="";
String currentMeds="";
String summarizeIntervention="";
String psychosocialStr="";
List<String> psychosocial = new ArrayList<String>(0);
String otherPsychosocialStr="";
String medication="";
String referralStr="";
List<String> referral = new ArrayList<String>(0);
String otherReferralStr="";
String education="";
String followUpPlans="";
String durationOfIntervention="";
String newCall="";
String followUp="";
String message="";

if(userInfoList!=null){
for(int i=0;i<userInfoList.size();i++){
date = (String)userInfoList.get(0);
time = (String)userInfoList.get(1);
seqNo = (String)userInfoList.get(2);
lastName = (String)userInfoList.get(3);
firstName = (String)userInfoList.get(4);
callbackNo = (String)userInfoList.get(5);
currentLoc = (String)userInfoList.get(6);
age = (int)userInfoList.get(7);
gender = (String)userInfoList.get(8);
teamRole = (String)userInfoList.get(9);
unit = (String)userInfoList.get(10);
race = (String)userInfoList.get(11);
ethnicity = (String)userInfoList.get(12);
newVsEx = (String)userInfoList.get(13);
topConcern = (String)userInfoList.get(14);
otherConcern = (String)userInfoList.get(15);
covidStatus = (String)userInfoList.get(16);
pastDiagnoses = (String)userInfoList.get(17);
currentMeds = (String)userInfoList.get(18);
summarizeIntervention = (String)userInfoList.get(19);
psychosocialStr = (String)userInfoList.get(20);
if(psychosocialStr!=null){
	String[] psychosocialList = psychosocialStr.split(",");
	psychosocial = new ArrayList<String>(psychosocialList.length);
	for (String psychosocl:psychosocialList) {
		psychosocial.add("\""+psychosocl+"\"");
	}
}
otherPsychosocialStr = userBean.getOtherPsychosocial();
medication = (String)userInfoList.get(21);
referralStr = (String)userInfoList.get(22);
if(referralStr!=null){
	String[] referralList = referralStr.split(",");
	referral = new ArrayList<String>(referralList.length);
	for (String ref:referralList) {
		referral.add("\""+ref+"\"");
	}
}
otherReferralStr = userBean.getOtherReferralStr();
education = (String)userInfoList.get(23);
followUpPlans = (String)userInfoList.get(24);
durationOfIntervention = (String)userInfoList.get(25);
newCall = (String)userInfoList.get(26);
followUp = (String)userInfoList.get(27);
message = (String)userBean.getMessage();

}
}

%>
<script type="text/javascript">
$(document).ready(function() {
	$('#timeId').datetimepicker({
		format: 'hh:mm A'
	});
	if(('<%= gender %>')!='null'){
	$("#genderId").val('<%= gender %>');
	}
	if(('<%= teamRole %>')!='null'){
		$("#teamRoleId").val('<%= teamRole %>');
	}
	if(('<%= race %>')!='null'){
		$("#raceId").val('<%= race %>');
	}
	if(('<%= ethnicity %>')!='null'){
		$("#ethnicityId").val('<%= ethnicity %>');
	}
	if(('<%= newVsEx %>')!='null'){
		$("#newVsExId").val('<%= newVsEx %>');
	}
	if(('<%= covidStatus %>')!='null'){
		$("#covidStatusId").val('<%= covidStatus %>');
	}
	if(('<%= newCall %>')!='null'){
		$("#newCallId").val('<%= newCall %>');
	}
	if(('<%= followUp %>')!='null'){
		$("#followUpId").val('<%=followUp%>');
	}
	if(('<%= durationOfIntervention %>')!='null'){
		$("#durationOfInterventionId").val('<%= durationOfIntervention %>');
	}

	if(('<%= psychosocialStr %>')!='null'){
		var psychosocialLst = '<%= psychosocial %>';
		var psychosocialStrLen = psychosocialLst.split(',').length;
		if(psychosocialLst.includes("[")){
			psychosocialLst = psychosocialLst.replace('[','');
		}
			psychosocialLst = psychosocialLst.replace(/\"/g,'');
		if(psychosocialLst.includes("]")){
			psychosocialLst = psychosocialLst.replace(']','');
		}
		psychosocialLst = psychosocialLst.split(',');
		var multi = document.getElementById('psychosocialId');
		var otherPsychosocial= '<%=otherPsychosocialStr%>';
		if(otherPsychosocial!='null' && otherPsychosocial!="") {
			$("#psychosocialId").val('<%=otherPsychosocialStr%>');
			var option = document.createElement("option");
			option.value = otherPsychosocial;
			option.text = otherPsychosocial;
			multi.add(option);
		}
		var multiLen = multi.options.length;
		for(var i=0;i<psychosocialStrLen;i++){
			for(var j=0;j<multiLen;j++){
				if(multi.options[j].value===psychosocialLst[i].trim()){
					multi.options[j].selected = true;
				}
				}
	}//end of for
	}//end of if
	
	if(('<%= referralStr %>')!='null'){
		var referralLst = '<%= referral %>';
		var referralStrLen = referralLst.split(',').length;
		if(referralLst.includes("[")){
			referralLst = referralLst.replace('[','');
		}
		referralLst = referralLst.replace(/\"/g,'');
		if(referralLst.includes("]")){
			referralLst = referralLst.replace(']','');
		}
		referralLst = referralLst.split(',');
		var multi = document.getElementById('referralId');
		var otherReferral= '<%=otherReferralStr%>';
		if(otherReferral!='null'&&otherReferral!="") {
			$("#referralId").val('<%=otherReferralStr%>');
			var option = document.createElement("option");
			option.value = otherReferral;
			option.text = otherReferral;
			multi.add(option);
		}
		var multiLen = multi.options.length;
		for(var i=0;i<referralStrLen;i++){
			for(var j=0;j<multiLen;j++){
				//alert("i:"+i+"pscho:"+psychosocialLst[i]+"j:"+j+"option:"+multi.options[j].value);
				if(multi.options[j].value===referralLst[i].trim()){
					multi.options[j].selected = true;
				}
				}
	}//end of for
	}//end of if
	
});
function showfield(name){

	 var x=document.getElementById("psychosocialId");
     for (var i = 0; i < x.options.length; i++) {
        if(x.options[i].selected ==true){
             if(x.options[i].value=='Other'){
             	document.getElementById('ifOtherPsychosocialId').style.display="block";
             	document.getElementById("ifOtherPsychosocialId").style.paddingLeft = "100px";
             }
             else document.getElementById('ifOtherPsychosocialId').style.display="none";
         }
     }
     var x=document.getElementById("referralId");
     for (var i = 0; i < x.options.length; i++) {
        if(x.options[i].selected ==true){
             if(x.options[i].value=='Other'){
             	document.getElementById('ifOtherReferralId').style.display="block";
             	document.getElementById("ifOtherReferralId").style.paddingLeft = "100px";
             }
             else document.getElementById('ifOtherReferralId').style.display="none";
         }
     }
  }
 function hidefield() {
 document.getElementById('ifOtherTeamId').style.display='none';
 document.getElementById('ifOtherPsychosocialId').style.display='none';
 document.getElementById('ifOtherReferralId').style.display='none';
 }


</script>
<style>
input[type="checkbox"] {
  padding-left: 10px;
}
#form {	
    background-color: #FFF;
    margin-right: auto;
    margin-left: auto;
    margin-top: 0px;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
    padding: 0px;
    text-align:center;
}
div.form-group{
	
}
label {
    font-family: Georgia, "Times New Roman", Times, serif;
    font-size: 18px;
    color: #333;
    height: 20px;
    width: 200px;
    margin-top: 10px;
    margin-left: 10px;
    text-align: right;
    margin-right:15px;
    float:left;
}
input {
    height: 20px;
    width: 300px;
    border: 1px solid #000;
    margin-top: 10px;
}
</style>

</head>
<body onload="hidefield()">

<div id="form">
<form name="homeForm" id="homeformId" method="post" action="Home">	

<div class="splash-container">
		<h1 class="splash-head is-center">Mental Health in the Era of COVID 19</h1>
	</div>
	<div class="content-wrapper">
		<div class="content">
		<% 
		if(errorMessage!=null&&errorMessage!=""){%>
			<div id="errormessage"><font color=red size=3px><%=errorMessage%></font></div>
			<%}else{%>
			<div id="successmessage"><font color=red size=3px><%=message%></font></div>
			<%}%>
			<h2 class="content-head is-center">Case Report Form</h2>
			<div class="row h-100 justify-content-center align-items-center">
			<fieldset>
			
			<input id="user" name="username" type="hidden" value="" />
			<div class="form-group">
			<label for="date">Date:</label>
			<input type="date" name="date" id="dateId" value="<%=date%>" class="form-control" style="width:400px;height: 40px;" placeholder="mm/dd/yyyy" /> 
			</div>
			<div class="form-group">
			<label for="time">Time:</label>
			<%if(time==null){%>
			<input type="text" name="time" id="timeId" value="" class="form-control" style="width:400px;height: 40px;" placeholder="HH:mm AM" /> 
			<%}else{%>
			<input type="text" name="time" id="timeId" value="<%=time%>" class="form-control" style="width:400px;height: 40px;" placeholder="HH:mm AM" /> 
			<%}%>
			</div>
			<div class="form-group">
			<label for="seqNo">Sequence Number:</label>
			<%if(seqNo==null){%>
			<input type='text' name="seqNo" id="seqNoId" value="" style="width:400px;height: 40px;" class="form-control" />
			<%}else{%>
			<input type='text' name="seqNo" id="seqNoId" value="<%=seqNo%>" style="width:400px;height: 40px;" class="form-control" />
			<%}%>
			</div>
			<div class="form-group">
			<label for="lastName">Last Name:</label>
			<%if(lastName==null&&lastName==""){%>
			<input type='text' name="lastName" id="lastNameId" value="" style="width:400px;height: 40px;" class="form-control" required/>
			<%}else{%>
			<input type='text' name="lastName" id="lastNameId" value="<%=lastName%>" style="width:400px;height: 40px;" class="form-control" required/>
			<%}%>
			</div>
			<div class="form-group">
			<label for="firstName">First Name:</label>
			<%if(firstName==null){%>
			<input type='text' name="firstName" id="firstNameId" value="" style="width:400px;height: 40px;" class="form-control" required/>
			<%}else{%>
			<input type='text' name="firstName" id="firstNameId" value="<%=firstName%>" style="width:400px;height: 40px;" class="form-control" required/>
			<%}%>
			</div>
			<div class="form-group">
			<label for="callbackNo">Callback Number:</label>
			<%if(callbackNo==null){%>
			<input type='text' name="callbackNo" id="callbackNoId" value="" style="width:400px;height: 40px;" class="form-control" />
			<%}else{%>
			<input type='text' name="callbackNo" id="callbackNoId" value="<%=callbackNo%>" style="width:400px;height: 40px;" class="form-control" />
			<%}%>
			</div>
			<div class="form-group">
			<label for="currentLoc">Current Location:</label>
			<%if(currentLoc==null){%>
			<input type='text' name="currentLoc" id="currentLocId" value="" style="width:400px;height: 40px;" class="form-control" />
			<%}else{%>
			<input type='text' name="currentLoc" id="currentLocId" value="<%=currentLoc%>" style="width:400px;height: 40px;" class="form-control" />
			<%}%>
			</div>
			<h3><p style="text-decoration: underline;">History</p></h3>
			<div class="form-group">
			<label for="age">Age:</label>
			<%if(age==0){%>
			<input type='text' name="age" id="ageId" value="" style="width:400px;height: 40px;" class="form-control" />
			<%}else{%>
			<input type='text' name="age" id="ageId" value="<%=age%>" style="width:400px;height: 40px;" class="form-control" />
			<%}%>
			</div>
			<div class="form-group">
			<label for="genderId">Gender:</label>
			<%if(gender==null){%>		
			<select name="gender" id="genderId" style="width:400px;height: 40px;" class="form-control">
			  <option value="" selected>--- Select ---</option>   
			  <option value="male">Male</option>
			  <option value="female">Female</option>
			</select> 
			<%}else{%>
			<select name="gender" id="genderId" style="width:400px;height: 40px;" class="form-control">
			  <option value="" selected>--- Select ---</option>   
			  <option value="male">Male</option>
			  <option value="female">Female</option>
			</select> 
			<%}%>
			</div>
			<div class="form-group">
			<label for="teamRoleId">Team Role:</label>
			<%if(teamRole==null){%>	
			<select name="teamRole" id="teamRoleId" style="width:400px;height: 40px;" class="form-control"  onchange="showfield(this.options[this.selectedIndex].value)" >
			    <option value="" selected>--- Select ---</option> 
			  <option value="Administrator">Administrator</option>
			  <option value="Physician">Physician</option>
			  <option value="AdvancedNP">Advanced Practice Nurse Practitioner</option>
			  <option value="Nurse">Nurse</option>
			  <option value="HCAide">Health Care Aide</option>
			  <option value="AuxSupportStaff">Auxiliary Support Staff</option>
			  <option value="OtherTeamRole">Other</option>
			</select> 
			<%}else{%>
						<select name="teamRole" id="teamRoleId" style="width:400px;height: 40px;" class="form-control"  onchange="showfield(this.options[this.selectedIndex].value)" >
			    <option value="" selected>--- Select ---</option> 
			  <option value="Administrator">Administrator</option>
			  <option value="Physician">Physician</option>
			  <option value="AdvancedNP">Advanced Practice Nurse Practitioner</option>
			  <option value="Nurse">Nurse</option>
			  <option value="HCAide">Health Care Aide</option>
			  <option value="AuxSupportStaff">Auxiliary Support Staff</option>
			  <option value="OtherTeamRole">Other</option>
			</select> 
			<%}%>
			</div>
			<div class="form-group" id="ifOtherTeamId">If Other: <input type="text" name="otherRole" id="OtherRoleId"/></div>
			<div class="form-group">
			<label for="unit">Unit/office:</label>
			<%if(unit==null){%>
			<input type='text' name="unit" id="unitId" value="" style="width:400px;height: 40px;" class="form-control" />
			<%}else{%>
			<input type='text' name="unit" id="unitId" value="<%=unit%>" style="width:400px;height: 40px;" class="form-control" />
			<%}%>
			</div>
			<div class="form-group">
			<label for="raceId">Optional Race:</label>
			<%if(race==null){%>
			<select name="race" id="raceId" style="width:400px;height: 40px;" class="form-control" >
			  <option value="" selected>--- Select ---</option> 
			  <option value="American Indian or Alaska Native">American Indian or Alaska Native</option>
			  <option value="Asian">Asian</option>
			  <option value="Black or African American">Black or African American</option>
			  <option value="Native Hawaiian or Other Pacific Islander">Native Hawaiian or Other Pacific Islander</option>
			  <option value="White">White</option>
			  <option value="Other">Other</option>
			  <option value="doNotWishToProvideRace">Do not wish to provide</option>
			</select> 
			<%}else{%>
			<select name="race" id="raceId" style="width:400px;height: 40px;" class="form-control" >
			  <option value="" selected>--- Select ---</option> 
			  <option value="American Indian or Alaska Native">American Indian or Alaska Native</option>
			  <option value="Asian">Asian</option>
			  <option value="Black or African American">Black or African American</option>
			  <option value="Native Hawaiian or Other Pacific Islander">Native Hawaiian or Other Pacific Islander</option>
			  <option value="White">White</option>
			  <option value="Other">Other</option>
			  <option value="doNotWishToProvideRace">Do not wish to provide</option>
			</select> 
			<%}%>
			</div>
			<div class="form-group">
			<label for="ethnicityId">Optional Ethnicity:</label>
			<%if(ethnicity==null){%>
			<select name="ethnicity" id="ethnicityId" style="width:400px;height: 40px;" class="form-control" >
			  <option value="" selected>--- Select ---</option> 
			  <option value="Hispanic">Hispanic</option>
			  <option value="notHispanic">Not Hispanic</option>
			  <option value="doNotWishToProvideEthnicity">Do not wish to provide</option>
			</select> 
			<%}else{%>
			<select name="ethnicity" id="ethnicityId" style="width:400px;height: 40px;" class="form-control" >
			  <option value="" selected>--- Select ---</option> 
			  <option value="Hispanic">Hispanic</option>
			  <option value="notHispanic">Not Hispanic</option>
			  <option value="doNotWishToProvideEthnicity">Do not wish to provide</option>
			</select> 
			<%}%>
			</div>
			<h3><p style="text-decoration: underline;">Presenting Issues</p></h3>
			<div class="form-group">
			<label for=newVsExId>New vs. Exacerbation:</label>
			<%if(newVsEx==null){%>
			<select name="newVsEx" id="newVsExId" style="width:400px;height: 40px;" class="form-control" >
			  <option value="" selected>--- Select ---</option>
			  <option value="New">New</option>
			  <option value="Exacerbation">Exacerbation</option>
			</select> 
			<%}else{%>
			<select name="newVsEx" id="newVsExId" style="width:400px;height: 40px;" class="form-control" >
			  <option value="" selected>--- Select ---</option>
			  <option value="New">New</option>
			  <option value="Exacerbation">Exacerbation</option>
			</select> 
			<%}%>
			</div>
			<div class="form-group">
			<label for="topConcern">Top presenting concern:</label>
			<%if(topConcern==null){%>
			<input type='text' name="topConcern" id="topConcernId" value="" style="width:400px;height: 40px;" class="form-control" />
			<%}else{%>
			<input type='text' name="topConcern" id="topConcernId" value="<%=topConcern%>" style="width:400px;height: 40px;" class="form-control" />
			<%}%>
			</div>
			<div class="form-group">
			<label for="otherConcernId">List other concerns in rank order:</label>
			<%if(otherConcern==null){%>
			<textarea id="otherConcernId" name="otherConcern" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			</textarea>
			<%}else{%>
			<textarea id="otherConcernId" name="otherConcern" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			<%=otherConcern%>
			</textarea>
			<%}%>
			</div>
			<div class="form-group">
			<label for="covidStatusId">Covid Status:</label>
			<%if(covidStatus==null){%>
			<select name="covidStatus" id="covidStatusId" style="width:400px;height: 40px;" class="form-control" >
			 <option value="" selected>--- Select ---</option> 
			  <option value="Positive">Positive</option>
			  <option value="Negative">Negative</option>
			  <option value="underInvestigation">Case Under Investigation</option>
			  <option value="notTested">Not Tested</option>
			</select> 
			<%}else{%>
			<select name="covidStatus" id="covidStatusId" style="width:400px;height: 40px;" class="form-control" >
			  <option value="" selected>--- Select ---</option> 
			  <option value="Positive">Positive</option>
			  <option value="Negative">Negative</option>
			  <option value="underInvestigation">Case Under Investigation</option>
			  <option value="notTested">Not Tested</option>
			</select> 
			<%}%>
			</div>
			<div class="form-group">
			<label for="pastDiagnosesId">Any known past psychiatric diagnoses:</label>
			<%if(pastDiagnoses==null){%>
			<textarea id="pastDiagnosesId" name="pastDiagnoses" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			</textarea>
			<%}else{%>
			<textarea id="pastDiagnosesId" name="pastDiagnoses" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			<%=pastDiagnoses%>
			</textarea>
			<%}%>
			</div>
			<div class="form-group">
			<label for="currentMedsId">Current Medication:</label>
			<%if(currentMeds==null){%>
			<textarea id="currentMedsId" name="currentMeds" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			</textarea>
				<%}else{%>
			<textarea id="currentMedsId" name="currentMeds" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			<%=currentMeds%>
			</textarea>
			<%}%>
			</div>
			<h3><p style="text-decoration: underline;">Intervention</p></h3>
			<div class="form-group">
			<label for="summarizeInterventionId">Summarize intervention:</label>
			<%if(summarizeIntervention==null){%>
			<textarea id="summarizeInterventionId" name="summarizeIntervention" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			</textarea>
			<%}else{%>
			<textarea id="summarizeInterventionId" name="summarizeIntervention" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			<%=summarizeIntervention%>
			</textarea>
			<%}%>
			</div>
			<div class="form-group">
			<label for="psychosocialId">Psychosocial :</label>
			<%if(psychosocialStr==null){%>
			<select name="psychosocial" id="psychosocialId" style="width:400px;height: 100px;" class="form-control"  onchange="showfield(this.options[this.selectedIndex].value)" multiple>
			  <option value="Supportive">Supportive</option>
			  <option value="Brief CBT">Brief CBT</option>
			  <option value="MI">MI</option>
			  <option value="Relaxation/Stress Management">Relaxation/Stress Management</option>
			  <option value="Grief work">Grief work</option>
			  <option value="Other">Other</option>
			</select> 
			</div>
			<div class="form-group" id="ifOtherPsychosocialId">If Other: <input type="text" name="OtherPsychosocial" id="OtherPsychosocialId"/></div>
			<%}else{%>
			<select name="psychosocial" id="psychosocialId" style="width:400px;height: 100px;" class="form-control"  onchange="showfield(this.options[this.selectedIndex].value)" multiple>
			  <option value="Supportive">Supportive</option>
			  <option value="Brief CBT">Brief CBT</option>
			  <option value="MI">MI</option>
			  <option value="Relaxation/Stress Management">Relaxation/Stress Management</option>
			  <option value="Grief work">Grief work</option>
			  <option value="Other">Other</option>
			</select> 
			</div>
			<div class="form-group" id="ifOtherPsychosocialId">If Other: <input type="text" name="OtherPsychosocial" id="OtherPsychosocialId"/></div>
				<%}%>
			<div class="form-group">
			<label for="medicationId">Medication/Somatic Therapy Education:</label>
			<%if(medication==null){%>
			<textarea id="medicationId" name="medication" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			</textarea>
			<%}else{%>
			<textarea id="medicationId" name="medication" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			<%=medication%>
			</textarea>
			<%}%>
			</div> 
			<div class="form-group">
			<label for="referralId">Referral :</label>
			<%if(referralStr==null){%>
			<select name="referral" id="referralId" style="width:400px;height: 100px;" class="form-control"  onchange="showfield(this.options[this.selectedIndex].value)" multiple >
			  <option value="Brief intervention (3-4 session)">Brief intervention (3-4 session)</option>
			  <option value="Ongoing psychotherapy">Ongoing psychotherapy</option>
			  <option value="Grief">Grief</option>
			  <option value="Psychiatry (medication management)">Psychiatry (medication management)</option>
			  <option value="Support Concluded">Support Concluded</option>
			  <option value="Other">Other</option>
			</select> 
			</div>
			<div class="form-group" id="ifOtherReferralId">If Other: <input type="text" name="OtherReferral" id="OtherReferralId"/></div> 
				<%}else{%>
				<select name="referral" id="referralId" style="width:400px;height: 100px;" class="form-control"  onchange="showfield(this.options[this.selectedIndex].value)" multiple >
			  <option value="Brief intervention (3-4 session)">Brief intervention (3-4 session)</option>
			  <option value="Ongoing psychotherapy">Ongoing psychotherapy</option>
			  <option value="Grief">Grief</option>
			  <option value="Psychiatry (medication management)">Psychiatry (medication management)</option>
			  <option value="Support Concluded">Support Concluded</option>
			  <option value="Other">Other</option>
			</select> 
			</div>
			<div class="form-group" id="ifOtherReferralId">If Other: <input type="text" name="OtherReferral" id="OtherReferralId"/></div> 
			<%}%>
			<div class="form-group">
			<label for="educationId">Education:</label>
			<%if(education==null){%>
			<textarea id="educationId" name="education" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			</textarea>
			<%}else{%>
			<textarea id="educationId" name="education" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			<%=education%>
			</textarea>
			<%}%>
			</div> 
			<div class="form-group">
			<label for="followUpPlansId">Follow-up plans:</label>
			<%if(followUpPlans==null){%>
			<textarea id="followUpPlansId" name="followUpPlans" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			</textarea>
			<%}else{%>
			<textarea id="followUpPlansId" name="followUpPlans" style="width:400px;height: 100px;" class="form-control" rows="4" cols="50" >
			<%=followUpPlans%>
			</textarea>
			<%}%>
			</div>
			<div class="form-group">
			<label for="durationOfInterventionId">Duration of Intervention in Minutes:</label>
			<%if(durationOfIntervention==null){%>
			<select name="durationOfIntervention" style="width:400px;height: 40px;" id="durationOfInterventionId" class="form-control" >
			<option value="" selected>--- Select ---</option> 
			  <option value="5-10">5-10</option>
			  <option value="10-15">10-15</option>
			  <option value="15-20">15-20</option>
			  <option value="20-25">20-25</option>
			  <option value="25-30">25-30</option>
			  <option value="30+">30+</option>
			</select> 
			<%}else{%>
			<select name="durationOfIntervention" style="width:400px;height: 40px;" id="durationOfInterventionId" class="form-control" >
			<option value="" selected>--- Select ---</option> 
			  <option value="5-10">5-10</option>
			  <option value="10-15">10-15</option>
			  <option value="15-20">15-20</option>
			  <option value="20-25">20-25</option>
			  <option value="25-30">25-30</option>
			  <option value="30+">30+</option>
			</select> 
			<%}%>
			</div>
			<h3><p style="text-decoration: underline;">Outcomes</p></h3>
			<div class="form-group">
			<label for="newCallId">New Call:</label>
			<%if(newCall==null){%>
			<select name="newCall" id="newCallId" style="width:400px;height: 40px;" class="form-control" >
			   <option value="" selected>--- Select ---</option> 
			  <option value="Normal, not at all ill">Normal, not at all ill</option>
			  <option value="Borderline mentally ill">Borderline mentally ill</option>
			  <option value="Mildly ill">Mildly ill</option>
			  <option value="Moderately ill">Moderately ill</option>
			  <option value="Markedly ill">Markedly ill</option>
			  <option value="Severely ill">Severely ill</option>
			  <option value="Among the most extremely ill patients">Among the most extremely ill patients</option>
			</select> 
			<%}else{%>
			<select name="newCall" id="newCallId" style="width:400px;height: 40px;" class="form-control" >
			   <option value="" selected>--- Select ---</option> 
			  <option value="Normal, not at all ill">Normal, not at all ill</option>
			  <option value="Borderline mentally ill">Borderline mentally ill</option>
			  <option value="Mildly ill">Mildly ill</option>
			  <option value="Moderately ill">Moderately ill</option>
			  <option value="Markedly ill">Markedly ill</option>
			  <option value="Severely ill">Severely ill</option>
			  <option value="Among the most extremely ill patients">Among the most extremely ill patients</option>
			</select> 
			<%}%>
			</div>
			<div class="form-group">
			<label for="followUpId">Follow-up:</label>
			<%if(followUp==null){%>
			<select name="followUp" id="followUpId" style="width:400px;height: 40px;" class="form-control" >
			  <option value="" selected>--- Select ---</option> 
			  <option value="1-Very much improved">1-Very much improved</option>
			  <option value="2-Much improved">2-Much improved</option>
			  <option value="3-Minimally improved">3-Minimally improved</option>
			  <option value="4-No change">4-No change</option>
			  <option value="5-Minimally Worse">5-Minimally Worse</option>
			  <option value="6-Much Worse">6-Much Worse</option>
			  <option value="7-Very much Worse">7-Very much Worse</option>
			</select> 
			<%}else{%>
			<select name="followUp" id="followUpId" style="width:400px;height: 40px;" class="form-control" >
			  <option value="" selected>--- Select ---</option> 
			  <option value="1-Very much improved">1-Very much improved</option>
			  <option value="2-Much improved">2-Much improved</option>
			  <option value="3-Minimally improved">3-Minimally improved</option>
			  <option value="4-No change">4-No change</option>
			  <option value="5-Minimally Worse">5-Minimally Worse</option>
			  <option value="6-Much Worse">6-Much Worse</option>
			  <option value="7-Very much Worse">7-Very much Worse</option>
			</select> 
			<%}%>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
			</fieldset>
			</div>
		</div>
	</div>
	
	</form>
	</div>
</body>
</html>