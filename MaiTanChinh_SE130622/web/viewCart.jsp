<%-- 
    Document   : viewCart
    Created on : Mar 4, 2020, 3:30:56 PM
    Author     : Chinh
--%>

<%@page import="java.util.Map"%>
<%@page import="chinhmt.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book</title>
    </head>
    <body>
        <h1>View Your Cart</h1>
        <c:if test="${not empty session}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.getItems()}"/>
                <c:if test="${not empty items}">
                    <form action="cart">
                        <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                        </tbody>
                        <c:forEach var="itemKey" items="items" varStatus="counter">
                            <tr>
                        <td>${counter.count}</td>
                        <td>${itemKey}</td>
                        <td>${items.get(itemKey)}</td>
                        <td>
                            <input type="checkbox" name="chkItem" value="${itemKey}" />
                        </td>
                    </tr>
                        </c:forEach>
                    <tr>
                        <th colspan="3">
                            <a href="market">Add More Product to Your Cart</a>
                        </th>
                        <th>
                            <input type="submit" value="Remove Selected Book" name="btAction" />
                        </th>

                    </tr>
                </tbody>
            </table>
                                <input type="submit" value="Check out" name="btAction" />
                    </form>
                </c:if>
            </c:if>
        </c:if>
<%--        <%
            //1. User goes to Cart place
            if (session != null) {
                //2. User takas cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3.User takes items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. Show items
        %>
        <form action="cart">

            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String itemKey : items.keySet()) {
                    %>
                    <tr>
                        <td><%= ++count%></td>
                        <td><%= itemKey%></td>
                        <td><%= items.get(itemKey)%></td>
                        <td>
                            <input type="checkbox" name="chkItem" value="<%= itemKey%>" />
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <tr>
                        <th colspan="3">
                            <a href="bookMarket.html">Add More Books to Your Cart</a>
                        </th>
                        <th>
                            <input type="submit" value="Remove Selected Book" name="btAction" />
                        </th>

                    </tr>
                </tbody>
            </table>
                                <input type="submit" value="Check out" name="btAction" />
        </form>
        <%
            return;
                    }//end if items is existed
                }//end if cart is existed
            }//end if session is existed
        %>--%>
        <h2>
            No cart is existed!!!
        </h2>
    </body>
</html>
