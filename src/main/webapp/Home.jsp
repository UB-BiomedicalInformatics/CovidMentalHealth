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
<jsp:useBean id="editUser" scope="session" class="java.lang.String" />

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
<% String errorMessage = ""; 

%>
<script type="text/javascript">
function checkIfUserExists(){
	alert("inside check if user exists");
	var userIdEntered = document.getElementById('searchId').value;
	alert("userid:"+userIdEntered);	
	document.getElementById("hiddenField").value=userIdEntered;

	//if(userIdEntered!=null && userIdEntered!=""){
		//alert("inside userentered no null");
	
	//alert("prob with db connection");
	//if(userInfoList!=null && userInfoList!=""){
	//alert("userInfoList not null");
	//}else{
		//alert("userInfoList nullll");
	//}
	//}else{
		//alert("Please enter UserId to proceed");
	//}
}
`	`
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
<body>

<div id="form">
<form name="homeForm" id="homeformId" method="post" action="Home">	

<div class="splash-container">
		<h1 class="splash-head is-center">Mental Health in the Era of COVID 19</h1>
	</div>
	<div class="content-wrapper">
		<div class="content">	
			<h2 class="content-head is-center">Case Report Form</h2>
			<div class="row h-100 justify-content-center align-items-center">
			<label for="search">Enter UserId to search:</label>
			<input type='text' name="search" id="searchId" style="width:400px;height: 40px;" class="form-control"/>
			<input type="hidden" name="hiddenField" id="hiddenField"/>
			</div>
			<br/>
			<div>
			<button type="submit" class="btn btn-primary" name="searchUser" id="searchUserId" onclick="checkIfUserExists();">Search</button>
			</div>
			<br/>
			<div>
			<button type="submit" class="btn btn-primary" name="createUser" id="createUserId" value="createUser">Create New User</button>
			</div>
		</div>
	</div>

	</form>
	</div>
</body>
</html>