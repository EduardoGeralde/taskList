<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importing header  -->
<c:import url="/resources/header.jsp" />
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login Page</title>
	</head>
	<body>
		<h2>Login Page for Tasks System</h2>
		<form action="makeLogin" method="post">
			Login: <input type="text" name="login"/> <br />
			Password: <input type="password" name="password"/> <br />
			<input type="submit" value="Enter"/>
		</form>
		<!-- importing footer -->
		<c:import url="/resources/footer.jsp" />
	</body>
</html>