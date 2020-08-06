<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.RequestDispatcher"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Covid Mental Health</title>
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="css/layouts/pure-min.css">
<link rel="stylesheet" href="css/layouts/grids-responsive-min.css">
<link rel="stylesheet"
	href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
<link rel="stylesheet" href="css/layouts/searchOverlay.css">
<title>CovidMentalHealth</title>
<script type="text/javascript">
function showfield(name){
    if(name=='OtherTeamRole'){
    	document.getElementById('ifOtherTeamId').style.display="block";
    	document.getElementById("ifOtherTeamId").style.paddingLeft = "100px";
    }
    else document.getElementById('ifOtherTeamId').style.display="none";
    if(name=='OtherPsychosocial'){
    	document.getElementById('ifOtherPsychosocialId').style.display="block";
    	document.getElementById("ifOtherPsychosocialId").style.paddingLeft = "100px";
    }
    else document.getElementById('ifOtherPsychosocialId').style.display="none";
    if(name=='OtherReferral'){
    	document.getElementById('ifOtherReferralId').style.display="block";
    	document.getElementById("ifOtherReferralId").style.paddingLeft = "100px";
    }
    else document.getElementById('ifOtherReferralId').style.display="none";
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
</style>

</head>
<body onload="hidefield()">
<form name="homeForm" id="homeformId" method="post" action="Home">	
<% 
String userId = request.getRemoteUser();
out.println("user id:"+userId);
%>
<div class="splash-container">
		<h1 class="splash-head is-center">Mental Health in the Era of COVID 19</h1>
	</div>
	<div class="content-wrapper">
		<div class="content">
			<font size="3px"></font>
			<h2 class="content-head is-center">Case Report Form</h2>
			<form id="homeForm"	class="pure-form pure-form-aligned">
			<fieldset>
			<div class="pure-control-group">
			<label for="date">Date:</label>
			<input type="date" id="date" placeholder="mm/dd/yyyy"/> 
			</div>
			<div class="pure-control-group">
			<label for="time">Time:</label>
			<input type="time" id="time" placeholder="hh:mm:ss.ms"/> 
			</div>
			<div class="pure-control-group">
			<label for="seqNo">Sequence Number:</label>
			<input type='text' id="seqNo"/>
			</div>
			<div class="pure-control-group">
			<label for="lastName">Last Name:</label>
			<input type='text' id="lastName"/>
			</div>
			<div class="pure-control-group">
			<label for="firstName">First Name:</label>
			<input type='text' id="firstName"/>
			</div>
			<div class="pure-control-group">
			<label for="callbackNo">Callback Number:</label>
			<input type='text' id="callbackNo"/>
			</div>
			<div class="pure-control-group">
			<label for="currentLoc">Current Location:</label>
			<input type='text' id="currentLoc"/>
			</div>
			<h3><p style="text-decoration: underline;">History</p></h3>
			<div class="pure-control-group">
			<label for="age">Age:</label>
			<input type='text' id="age"/>
			</div>
			<div class="pure-control-group">
			<label for="genderId">Gender:</label>
			<select name="gender" id="genderId">
			  <option value="male">Male</option>
			  <option value="female">Female</option>
			</select> 
			</div>
			<div class="pure-control-group">
			<label for="teamRoleId">Team Role:</label>
			<select name="teamRole" id="teamRoleId" onchange="showfield(this.options[this.selectedIndex].value)">
			  <option value="Administrator">Administrator</option>
			  <option value="Physician">Physician</option>
			  <option value="AdvancedNP">Advanced Practice Nurse Practitioner</option>
			  <option value="Nurse">Nurse</option>
			  <option value="HCAide">Health Care Aide</option>
			  <option value="AuxSupportStaff">Auxiliary Support Staff</option>
			  <option value="OtherTeamRole">Other</option>
			</select> 
			</div>
			<div class="pure-control-group" id="ifOtherTeamId">If Other: <input type="text" name="otherRole" id="OtherRoleId"/></div>
			<div class="pure-control-group">
			<label for="unit">Unit/office:</label>
			<input type='text' id="unit"/>
			</div>
			<div class="pure-control-group">
			<label for="raceId">Optional Race:</label>
			<select name="race" id="raceId">
			  <option value="American Indian or Alaska Native">American Indian or Alaska Native</option>
			  <option value="Asian">Asian</option>
			  <option value="Black or African American">Black or African American</option>
			  <option value="Native Hawaiian or Other Pacific Islander">Native Hawaiian or Other Pacific Islander</option>
			  <option value="White">White</option>
			  <option value="Other">Other</option>
			  <option value="doNotWishToProvideRace">Do not wish to provide</option>
			</select> 
			</div>
			<div class="pure-control-group">
			<label for="ethnicityId">Optional Ethnicity:</label>
			<select name="ethnicity" id="ethnicityId">
			  <option value="Hispanic">Hispanic</option>
			  <option value="notHispanic">Not Hispanic</option>
			  <option value="doNotWishToProvideEthnicity">Do not wish to provide</option>
			</select> 
			</div>
			<h3><p style="text-decoration: underline;">Presenting Issues</p></h3>
			<div class="pure-control-group">
			<label for=newVsExId>New vs. Exacerbation:</label>
			<select name="newVsEx" id="newVsExId">
			  <option value="New">New</option>
			  <option value="Exacerbation">Exacerbation</option>
			</select> 
			</div>
			<div class="pure-control-group">
			<label for="topConcern">Top presenting concern:</label>
			<input type='text' id="topConcern"/>
			</div>
			<div class="pure-control-group">
			<label for="topConcernId">List other concerns in rank order:</label>
			<textarea id="topConcernId" name="topConcern" rows="4" cols="50">
			</textarea>
			</div>
			<div class="pure-control-group">
			<label for="covidStatusId">Covid Status:</label>
			<select name="covidStatus" id="covidStatusId">
			  <option value="Positive">Positive</option>
			  <option value="Negative">Negative</option>
			  <option value="underInvestigation">Case Under Investigation</option>
			  <option value="notTested">Not Tested</option>
			</select> 
			</div>
			<div class="pure-control-group">
			<label for="pastDiagnosesId">Any known past psychiatric diagnoses:</label>
			<textarea id="pastDiagnosesId" name="pastDiagnoses" rows="4" cols="50">
			</textarea>
			</div>
			<div class="pure-control-group">
			<label for="priorMedsId">Current Medication:</label>
			<textarea id="priorMedsId" name="priorMeds" rows="4" cols="50">
			</textarea>
			</div>
			<h3><p style="text-decoration: underline;">Intervention</p></h3>
			<div class="pure-control-group">
			<label for="summarizeInterventionId">Summarize intervention:</label>
			<textarea id="summarizeInterventionId" name="summarizeIntervention" rows="4" cols="50">
			</textarea>
			</div>
			<div class="pure-control-group">
			<label for="psychosocialId">Psychosocial :</label>
			<select name="psychosocial" id="psychosocialId"  onchange="showfield(this.options[this.selectedIndex].value)" multiple>
			  <option value="Supportive">Supportive</option>
			  <option value="briefCBT">Brief CBT</option>
			  <option value="MI">MI</option>
			  <option value="relaxation">Relaxation/Stress Management</option>
			  <option value="griefWork">Grief work</option>
			  <option value="OtherPsychosocial">Other</option>
			</select> 
			</div>
			<div class="pure-control-group" id="ifOtherPsychosocialId">If Other: <input type="text" name="otherPsychosocial" id="OtherPsychosocialId"/></div>
			<div class="pure-control-group">
			<label for="medicationId">Medication/Somatic Therapy Education:</label>
			<textarea id="medicationId" name="medication" rows="4" cols="50">
			</textarea>
			</div> 
			<div class="pure-control-group">
			<label for="referralId">Referral :</label>
			<select name="referral" id="referralId" onchange="showfield(this.options[this.selectedIndex].value)" multiple>
			  <option value="briefIntervention">Brief intervention (3-4 session)</option>
			  <option value="ongoingPsychotherapy">Ongoing psychotherapy</option>
			  <option value="grief">Grief</option>
			  <option value="psychiatry">Psychiatry (medication management)</option>
			  <option value="supportConcluded">Support Concluded</option>
			  <option value="OtherReferral">Other</option>
			</select> 
			</div>
			<div class="pure-control-group" id="ifOtherReferralId">If Other: <input type="text" name="otherReferral" id="OtherReferralId"/></div>
			<div class="pure-control-group">
			<label for="educationId">Education:</label>
			<textarea id="educationId" name="education" rows="4" cols="50">
			</textarea>
			</div> 
			<div class="pure-control-group">
			<label for="followUpPlansId">Follow-up plans:</label>
			<textarea id="followUpPlansId" name="followUpPlans" rows="4" cols="50">
			</textarea>
			</div>
			<div class="pure-control-group">
			<label for="durationOfInterventionId">Duration of Intervention in Minutes:</label>
			<select name="durationOfIntervention" id="durationOfInterventionId">
			  <option value="5-10">5-10</option>
			  <option value="10-15">10-15</option>
			  <option value="15-20">15-20</option>
			  <option value="20-25">20-25</option>
			  <option value="25-30">25-30</option>
			  <option value="30+">30+</option>
			</select> 
			</div>
			<h3><p style="text-decoration: underline;">Outcomes</p></h3>
			<div class="pure-control-group">
			<label for="newCallId">New Call:</label>
			<select name="newCall" id="newCallId">
			  <option value="Normal, not at all ill">Normal, not at all ill</option>
			  <option value="Borderline mentally ill">Borderline mentally ill</option>
			  <option value="Mildly ill">Mildly ill</option>
			  <option value="Moderately ill">Moderately ill</option>
			  <option value="Markedly ill">Markedly ill</option>
			  <option value="Severely ill">Severely ill</option>
			  <option value="Among the most extremely ill patients">Among the most extremely ill patients</option>
			</select> 
			</div>
			<div class="pure-control-group">
			<label for="followUpId">Follow-up:</label>
			<select name="followUp" id="followUpId">
			  <option value="1-Very much improved">1-Very much improved</option>
			  <option value="2-Much improved">2-Much improved</option>
			  <option value="3-Minimally improved">3-Minimally improved</option>
			  <option value="4-No change">4-No change</option>
			  <option value="5-Minimally Worse">5-Minimally Worse</option>
			  <option value="6-Much Worse">6-Much Worse</option>
			  <option value="7-Very much Worse">7-Very much Worse</option>
			</select> 
			</div>
			</fieldset>
			</form>
		</div>
	</div>
	</form>
</body>
</html>