<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Question</title>
	<!-- for Bootstrap CSS -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
	<div class="container w-50 mt-5 border border-dark border-2 p-5">
		<h1>What is your question?</h1>
		<form action="/questions" method="post">
			<p class="text-danger"><c:out value="${question_error}"/></p>
			<p class="d-flex">
				<label for="question" class="form-label col">Question</label>
				<textarea class="form-control col" name="question"></textarea>
			</p>
			<p class="text-danger"><c:out value="${tag_error}"/></p>
			<p class="d-flex">
				<label for="tags" class="form-label col">Tag</label>
				<input type="text" class="form-control col" name="tags"/>
			</p>
			<div class="d-flex justify-content-end">
				<input class = "btn btn-dark" type="submit" value="Submit"/>
			</div>
		</form>
	</div>
</body>
</html>