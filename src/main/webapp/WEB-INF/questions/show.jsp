<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Questions profile</title>
	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

</head>
<body>
	<div class="container w-50 mt-5 border border-dark border-2 p-5">
		<h1><c:out value="${question.question}"></c:out> </h1>
		<div class="d-flex gap-2 align-items-center">
			<h2>Tags:</h2>
			<c:forEach var="tag" items="${question.tags}">
				<p class="border border-dark border-2 rounded px-2 py-1 mb-0"><c:out value="${tag.subject}"/></p>
			</c:forEach>
		</div>
		<!-- Show Answers -->
		<div class="d-flex gap-3">
			<table class="table col">
				<thead>
					<tr><th>Answers</th></tr>
				</thead>
				<tbody>
					<c:forEach var="answer" items="${question.answers}">
						<tr class="align-middle"><td><c:out value="${answer.answer}"/></td></tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="col">
				<h3>Add your answer:</h3>
				<form:form action="/answers/${question.id}" method="post" modelAttribute="ans">
					<form:errors class="text-danger col" path="answer"/>
					<p class="d-flex">
						<form:label class="form-label col w-25" path="answer">Answer</form:label>
						<form:textarea class="form-control w-75" path="answer"/>
					</p>
					<div class="d-flex justify-content-end">
						<input class = "btn btn-dark p-1" type="submit" value="Answer it!"/>
					</div>
				</form:form>
			</div>
		</div>
		<footer class="d-flex justify-content-end mt-5">
			<a href="/questions">Home</a>
		</footer>
	</div>
</body>
</html>