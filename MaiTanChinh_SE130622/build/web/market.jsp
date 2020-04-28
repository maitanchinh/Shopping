<%-- 
    Document   : product
    Created on : Mar 24, 2020, 8:03:49 PM
    Author     : Chinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Page</title>
    </head>
    <body>
        Please choose product what you want to buy:
        <form action="add">
            <c:set var="product" value="${session.PRODUCT}"/>
            <c:if test="${not empty product}">
            Choose product:
            <select name="cboProduct">
                    <c:forEach var="list" items="${product}">
                        <option>${list}</option>
                    </c:forEach></br>
                <select>
                <input type="submit" value="Add To Cart" name="btAction" />
            </c:if>
        </form>
        <form action="viewCart">
            <input type="submit" value="View Cart" name="btAction" />
        </form>
    </body>
</html>
