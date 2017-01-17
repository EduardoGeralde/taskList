<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--Created by Eduardo Geralde Neto

Used to help fill the finalized time for the task list--%>

<td>${task.id}</td>
<td>${task.description}</td>
<td>Finalized</td>
<td><fmt:formatDate value="${task.finalizedDay.time}" pattern="dd/MM/yyyy" /></td>
<td><a href="removeTask?id=${task.id}" >Remove</a></td>
<td><a href="showTask?id=${task.id}">Update</a></td>