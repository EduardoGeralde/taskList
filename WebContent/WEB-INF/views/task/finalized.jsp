<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<td>${task.id}</td>
<td>${task.description}</td>
<td>Finalized</td>
<td><fmt:formatDate value="${task.finalizedDay.time}" pattern="dd/MM/yyyy" /></td>
<td><a href="removeTask?id=${task.id}" >Remove</a></td>
<td><a href="showTask?id=${task.id}">Update</a></td>