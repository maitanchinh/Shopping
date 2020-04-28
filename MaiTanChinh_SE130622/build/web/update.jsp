<%-- 
    Document   : update
    Created on : Mar 24, 2020, 12:01:26 AM
    Author     : Chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Profile Page</title>
    </head>
    <body>
        <h1>Update User Account</h1>
        <form action="update" method="POST">
            <c:set var="errors" value="${requestScope.UPDATEERRORS}"/>
            User name: ${sessionScope.USERNAME}</br>
            Password: <input type="password" name="txtPassword" value="" /></br>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font></br>
            </c:if>
            Confirm: <input type="password" name="txtConfirm" value="" /></br>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                ${errors.confirmNotMatched}
                </font></br>
            </c:if>
            Full name: <input type="text" name="txtLastname" value="${sessionScope.LASTNAME}" /></br>
            <input type="submit" value="Update" name="btAction" />
            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color="red">
                ${errors.fullnameLengthErr}
                </font></br>
            </c:if>
        </form>
    </body>
</html>
