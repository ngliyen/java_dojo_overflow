<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Questions Dashboard</title>
	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

</head>
<body>
	<div class="container w-50 mt-5 border border-dark border-2 p-5">
		<h1>Questions Dashboard</h1>
		<table class="table">
			<thead>
				<tr>
					<th>Question</th>
					<th>Tags</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="question" items="${questions}">
					<tr class="align-middle">
						<td><a href="/questions/${question.id}"><c:out value="${question.question}"/></a></td>
						<td>
							<c:forEach var="tag" items="${question.tags}" varStatus="status">
								<c:out value="${tag.subject}"/>
								<c:if test="${status.getCount() != fn:length(question.tags)}">
									<span>, </span> 
								</c:if>
							</c:forEach>	
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<footer class="d-flex justify-content-end">
			<a href="/questions/new">New Question</a>
		</footer>
	</div>
</body>
</html>