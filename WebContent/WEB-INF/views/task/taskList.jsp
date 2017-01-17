<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--Created by Eduardo Geralde Neto

This Form is used to receive and show the list of all tasks from DB. It has a link to add, update, remove and
finalize a task. It make use of some AJAX to improve the user experience--%>

<!-- Importing header  -->
<c:import url="/resources/header.jsp" />
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="resources/js/jquery.js"></script>
		<title>Tasks List</title>
	</head>
	<body>
	
		<script type="text/javascript">
			function finalizeNow(id) {
				$.post("finalizeTask", { 'id':id}, function(response) {
					//Selecting the HTML element through its id and changing its HTML
					$("#task_"+id).html (response);
				});
			}
		</script>
		
		<script type="text/javascript">
			function removeTask(id){
				$.post("removeTask",{'id':id}, function() {
			 		$("#task_"+id).remove();
				});
			}
		</script>

		<a href="newTask">Create new task</a>
		<br /> <br />
		<table>
			<tr>
				<th>Id</th>
				<th>Description</th>
				<th>Task Status</th>
				<th>Finalized Date</th>
			</tr>
			<c:forEach items="${tasks}" var="task" varStatus="status">
				<tr bgcolor="${(status.index%2)==0? 'lightblue' : 'white' }" id="task_${task.id}">
					<td>${task.id}</td>
					<td>${task.description}</td>
					<c:if test="${task.isComplete eq true}">
						<td>Finalized</td>
					</c:if>
					<c:if test="${task.isComplete eq false}">
						<td> 
						<a href="#" onClick="finalizeNow(${task.id})">Finalize Now !</a>
						</td>
					</c:if>
					<td>
						<fmt:formatDate value="${task.finalizedDay.time}" pattern="dd/MM/yyyy"/>
					</td>
					<td><a href="#" onClick="removeTask(${task.id})">Remove</a></td>
					<td><a href="showTask?id=${task.id}">Update</a></td>
				</tr>
			</c:forEach>
		</table>
		<!-- importing footer -->
		<c:import url="/resources/footer.jsp" />
	</body>
</html>