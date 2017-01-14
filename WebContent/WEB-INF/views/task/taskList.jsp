<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Tasks List</title>
	</head>
	<body>

		<a href="newTask">Create new task</a>
		<br /> <br />
		<table>
			<tr>
				<th>Id</th>
				<th>Description</th>
				<th>Finalized Day</th>
			</tr>
			<c:forEach items="${tasks}" var="task">
				<tr>
					<td>${task.id}</td>
					<td>${task.description}</td>
					<c:if test="${task.finalizedDay eq false }">
						<td>Not yet finalized</td>
					</c:if>
					<c:if test="${task.finalizedDay eq true}">
						<td>Finalized</td>
					</c:if>
					<td>
						<fmt:formatDate value="${task.finalizedDay.time}" pattern="dd/MM/yyyy"/>
					</td>
					<td><a href="removeTask?id=${task.id}">Remove</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>