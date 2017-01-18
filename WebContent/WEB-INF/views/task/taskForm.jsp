<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--Created by Eduardo Geralde Neto

This Form is to add new tags--%>

<!-- Importing header  -->
<c:import url="/resources/header.jsp" />
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link type="text/css" href="resources/css/tasks.css" rel="stylesheet"/>
		<title>Task Form</title>
	</head>
	<body>
		<h3>Add Tasks</h3>
		<!-- Getting validation error messages -->
		<form:errors path="task.description" cssStyle="color:red"/>
		<form action="addTask" method="post">
			Description: <br />
			<textarea name="description" rows="3" cols="50"></textarea><br /> 
			<input type="submit" value="Add">
		</form>
		<!-- importing footer -->
		<c:import url="/resources/footer.jsp" />
	</body>
</html>