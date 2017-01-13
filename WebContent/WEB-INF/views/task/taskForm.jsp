<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Task Form</title>
	</head>
	<body>
		<h3>Add Tasks</h3>
		<form action="addTask" method="post">
			Description: <br />
			<textarea name="description" rows="5" cols="100"></textarea><br /> 
			<input type="submit" value="Add">
		</form>
	</body>
</html>