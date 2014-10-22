<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>message Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<h1>
    Send a message
</h1>
 
<c:url var="sendAction" value="/messages/send" ></c:url>
 
<form:form action="${sendAction}" commandName="message">
<table>
    <c:if test="${!empty message.messageId}">
    <tr>
        <td>
            <form:label path="messageId">
                <spring:message text="ID"/>
            </form:label>
        </td>
        <td>
            <form:input path="messageId" readonly="true" size="8"  disabled="true" />
            <form:hidden path="messageId" />
        </td> 
    </tr>
    </c:if>
    <tr>
        <td>
            <form:label path="messageSender">
                <spring:message text="Sender"/>
            </form:label>
        </td>
        <td>
            <form:input path="messageSender" />
        </td> 
    </tr>
    <tr>
        <td>
            <form:label path="messageReceiver">
                <spring:message text="Receiver"/>
            </form:label>
        </td>
        <td>
            <form:input path="messageReceiver" />
        </td> 
    </tr>
    <tr>
        <td>
            <form:label path="messageContent">
                <spring:message text="Content"/>
            </form:label>
        </td>
        <td>
            <form:input path="messageContent" />
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <c:if test="${!empty message.messageId}">
                <input type="submit"
                    value="<spring:message text="Edit message"/>" />
            </c:if>
            <c:if test="${empty message.messageId}">
                <input type="submit"
                    value="<spring:message text="Add message"/>" />
            </c:if>
        </td>
    </tr>
</table>  
</form:form>
<br>
<h3>messages List</h3>
<c:if test="${!empty listmessages}">
    <table class="tg">
    <tr>
        <th width="80">Message ID</th>
        <th width="120">Message Sender</th>
        <th width="120">Message Receiver</th>
        <th width="240">Message Content</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listMessages}" var="message">
        <tr>
            <td>${message.messageId}</td>
            <td>${message.messageSender}</td>
            <td>${message.messageReceiver}</td>
            <td>${message.messageContent}</td>
            <td><a href="<c:url value='messages/delete/${message.messageId}' />" >Delete</a></td>
        </tr>
    </c:forEach>
    </table>
</c:if>
<h3>Users List</h3>
<c:if test="${!empty listUsers}">
    <table class="tg">
    <tr>
        <th width="80">User ID</th>
        <th width="120">User Name</th>
        <th width="120">User Password</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${sessionScope.listUsers}" var="sessionScope.user">
        <tr>
            <td>${user.userId}</td>
            <td>${user.userName}</td>
            <td>${user.userPassword}</td>
            <td><a href="<c:url value='users/update/${user.userId}' />" >Edit</a></td>
            <td><a href="<c:url value='users/delete/${user.userId}' />" >Delete</a></td>
        </tr>
    </c:forEach>
    </table>
</c:if>
</body>
</html>