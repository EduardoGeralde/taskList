<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Adding the tag created  with datePicker-->
<%@ taglib tagdir="/WEB-INF/tags" prefix="ed" %>

<html>
	<head>
		<link href=<c:url value="resources/css/jquery.css"/> rel="stylesheet" type="text/css">
		<script src="resources/js/jquery.js" type="text/javascript"></script>
		<script src="resources/js/jquery-ui.js" type="text/javascript"></script>
	</head>
	<body>
		<h3>Update Task - ${task.id}</h3>
		<form action ="updateTask" method="post" name="task">
			<input type="hidden" name="id" value="${task.id}"/>
			
			Description: <br />
			<textarea name="description" cols="100" rows="5">
				${task.description}
			</textarea>
			<br />
			
			Finalized? 
			<input type="checkbox" name="isComplete" value="true" ${task.isComplete ? 'checked' : ' ' }/><br />
			
			<fmt:formatDate pattern="dd/MM/yyyy" value="${task.finalizedDay.time}" var="formattedDate"/>
			Finalized Day: <ed:dateField id="finalizedDay" value="${formattedDate}"/> <br /> 
			<br />
			<input type="submit" value="Update"/>
		</form>
	</body>
</html>