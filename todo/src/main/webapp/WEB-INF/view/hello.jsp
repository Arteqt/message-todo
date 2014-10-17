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

		<h1>Hello ıişğüöç</h1>
		
		<p>${messageBean.content}</p>
		
		<form action="hello" method="post">
			<input type="text" name="content">
			<button>Submit</button>
		</form>

	</body>
</html>
