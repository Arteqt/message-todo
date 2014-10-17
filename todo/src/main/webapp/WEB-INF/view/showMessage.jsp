<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>

		<h1>Todos</h1>

		<p>${message}</p>
		
		<c:if test="${not empty todos}">
 
			<ul>
				<c:forEach items="${todos}" var="todo">
					<li>${todo.title} | ${todo.content} </li>
				</c:forEach>
			</ul>
	 
		</c:if>

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
		                <input type="submit" value="Save Todo" />
		            </td>
		        </tr>
		    </table>
		</form:form>
	</body>
</html>
