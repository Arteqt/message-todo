<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Form Example</title>
	</head> 
	<body>

		<form:form commandName="userBean">
		    <table>
		        <tr>
		            <td>Username:</td>
		            <td><form:input path="username" /></td>
		        </tr>
		        <tr>
		            <td>Imza:</td>
		            <td><form:input path="sign" /></td>
		        </tr>
		        <tr>
		            <td colspan="2">
		                <input type="submit" value="Submit" />
		            </td>
		        </tr>
		    </table>
		</form:form>

	</body>
</html>
