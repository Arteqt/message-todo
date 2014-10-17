<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Hello</title>
	</head> 
	<body>

		<h1>Hello</h1>
		
		<c:forEach items="${user.todos}" var="todo">
			<p>${todo.title}</p>
		</c:forEach>
		

	</body>
</html>
