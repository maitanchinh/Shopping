<%-- 
    Document   : search
    Created on : Mar 22, 2020, 10:07:57 PM
    Author     : Chinh
--%>

<%@page import="chinhmt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.LASTNAME}
        </font>
        <h1>Search page</h1>
        <form action="search">
            Search value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}"/>
            <input type="submit" value="Search" name="btAction" />
        </form>
            <form action="logout">
            <input type="submit" value="Log out" name="btAction" />

        </form> </br>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Last name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="administrate">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}"/>
                                </td>
                                <td>${dto.lastname}</td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="delete">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="username" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                            <c:set var="errors" value="${requestScope.UPDATEERROR}"/>
                            <c:if test="${not empty errors.passwordLengthErr}">
                                <font color="red">
                                ${errors.passwordLengthErr}
                                </font></br>
                            </c:if>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is matched!!!
            </h2>
        </c:if>
    </c:if>
    <%--<%
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            String username = cookies[cookies.length - 1].getName();
            %>
            <font color="red">
            Welcome, <%= username %>
            </font>
    <%
        }//end if cookies ix existed
    %>
    <h1>Search page</h1>
    <form action="DispatcherController">
        Search value<3 <input type="text" name="txtSearchValue" 
                            value="<%= request.getParameter("txtSearchValue") %>" />
        <input type="submit" value="Search" name="btAction" />
    </form><br/>

        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistrationDTO> result = (List<RegistrationDTO>)request.getAttribute("SEARCHRESULT");

                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Last name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatcherController" 
                                            + "?btAction=delete"
                                            + "&username=" + dto.getUsername()
                                            + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatcherController">
                <tr>
                    <td>
                        <%= ++count %>
                        .</td>
                    <td>
                        <%= dto.getUsername() %>
                        <input type="hidden" name="txtUsername" 
                               value="<%= dto.getUsername() %>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" 
                               value="<%= dto.getPassword() %>" required=""/>
                    </td>
                    <td>
                        <%= dto.getLastname() %>
                    </td>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON" 
                               <%
                               if(dto.isRole()) {
                                   %>
                                   checked="checked"
                               <%
                               } //end if role is true
                               %>
                               />
                    </td>
                    <td>
                        <a href="<%= urlRewriting %>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" name="btAction" value="Update" />
                        <input type="hidden" name="lastSearchValue" value="<%= searchValue %>" />
                    </td>
                </tr>
            </form>
    
                <%
                    } //end for dto
                %>
            </tbody>
        </table>

        <%
        } else {
        %>
        <h2>
            No record is matched!!!
        </h2>
        <%
                }
            } //end if search value is existed
        %>--%>


</body>
</html>
