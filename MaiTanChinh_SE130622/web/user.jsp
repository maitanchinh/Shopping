<%-- 
    Document   : user
    Created on : Mar 23, 2020, 10:27:48 PM
    Author     : Chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.LASTNAME}
        </font>
        <h1>User Profile</h1>
        <form action="user" method="POST">
            <input type="submit" value="Shopping Now" name="btAction" />
            <input type="submit" value="Update Profile" name="btAction" />
            <input type="submit" value="Log out" name="btAction" />
        </form>
        
    </body>
</html>
