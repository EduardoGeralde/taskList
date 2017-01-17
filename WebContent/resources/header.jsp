<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--Created by Eduardo Geralde Neto

This is a simple JSP to be our default header in other JSP page--%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
		<!-- absolute path with c:url -->
		<img src="<c:url value="/resources/images/tasksImage.jpeg"/>"/>
		<h2> Welcome, ${userLogged.login}</h2>
		<a href="logout">logout</a>
		<hr />
	</body>
</html>