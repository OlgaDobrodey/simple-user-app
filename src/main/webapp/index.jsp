<%--
  Created by IntelliJ IDEA.
  User: Olga
  Date: 9/23/2022
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users Information</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>

<table width="90%" cellpadding="15" cellspacing="10">
    <tr>
        <td>
            <form action="users" method="get">
                <button type="submit">Get all users</button>
                <br><br>
            </form>

            <table>
                <body>
                <c:forEach var="user" items="${listUsers}">
                    <tr>
                        <td> ${user.getId()} </td>
                        <td> ${user.getFirstName()}</td>
                        <td> ${user.getLastName()}</td>
                        <td>
                            <form action="delete" method="post">
                                <input hidden name="userId" value="${user.getId()}"/>
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </body>
            </table>

            <form action="deleteAll" method="get">
                <button type="submit">Delete User All</button>
                <br><br>
            </form>
        </td>
        <td>
            <form action="add" method="post">
                <label for="first">First name:</label>
                <input type="text" id="first" name="firstName" value="" required><br><br>
                <label for="last">Last Name:</label>
                <input type="text" id="last" name="lastName" value="" required><br><br>
                <button type="submit">Add User</button>
                <br><br>
            </form>
        </td>
        <td>
            <form action="get" method="post">
                <label for="get">Id:</label>
                <input type="number" id="get" name="userId" value="" required><br><br>
                <button type="submit">Find User by Id</button>
                <br><br>
            </form>

            <c:choose>
                <c:when test="${!user.equals('User not found')}">
                    <table>
                        <tr>
                            <td> ${user.getId()}</td>
                            <td> ${user.getFirstName()}</td>
                            <td> ${user.getLastName()}</td>
                        </tr>
                    </table>

                    <br/>
                </c:when>
                <c:otherwise>
                    "${user}"
                    <br/>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
</body>
</html>
