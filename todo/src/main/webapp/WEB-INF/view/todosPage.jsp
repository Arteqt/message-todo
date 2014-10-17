<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Todo</title>
	</head> 
	<body>
	
		<h1>My TODOs</h1>

		<c:forEach items="${allTodos}" var="todo">
			<p>${todo.title}</p>
		</c:forEach>

		<h3>Add New Todo</h3>
		<form:form commandName="todoBean">
		    <table>
		        <tr>
		            <td>Title:</td>
		            <td><form:input path="title" /></td>
		        </tr>
		        <tr>
		            <td>Content:</td>
		            <td><form:input path="content" /></td>
		        </tr>
		        <tr>
		            <td colspan="2">
		                <input type="submit" value="Save" />
		            </td>
		        </tr>
		    </table>
		</form:form>

	</body>
</html>
