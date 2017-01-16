<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Update Task</title>
	</head>
	<body>
		<h3>Update Task - ${task.id}</h3>
		<form action ="updateTask" method="post">
			<input type="hidden" name="id" value="${task.id}"/>
			
			Description: <br />
			<textarea name="description" cols="100" rows="5">
				${task.description}
			</textarea>
			<br />
			
			Finalized? 
			<input type="checkbox" name="isComplete" value="true" ${task.isComplete ? 'checked' : '' }/><br />
			
			Date of Finalization: <br />
			<input type="text" name="finalizedDay" 
							value="<fmt:formatDate value="${task.finalizedDay.time}" pattern="dd/MM/yyyy"/>"/>
			<br />
			<input type="submit" value="Update"/>
		</form>
	</body>
</html>