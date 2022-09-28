<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users Information</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
</head>
<body>
<%@include file="views/header.jsp" %>

<div class="container">

    <table class="table table-striped table-info">
        <tbody>
        <tr>
            <th>ID</th>
            <th> FIRST NAME</th>
            <th> LAST NAME</th>
            <th>ROLE</th>
            <th>E-MAIL</th>
            <th>DATA OF BITH</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="user" items="${listUsers}">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getRole().getRoleName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getDateOfBirth()}</td>
                <td>
                    <form action="userBooks" method="post">
                        <input hidden name="userId" value="${user.getId()}"/>
                        <button class="btn btn-info" type="submit">Show all books for user</button>
                    </form>
                </td>
                <td>
                    <form action="delete" method="post">
                        <input hidden name="userId" value="${user.getId()}"/>
                        <button class="btn btn-info" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form action="deleteAll" method="get">
        <button class="btn btn-info" type="submit">Delete all users</button>
        <br><br>
    </form>

    <div class="container">
        <form action="add" method="post">
            <div class="row">
                <h4 class="text-info text-right-">Create a new user</h4>
                <h6 class="text-danger text-right">${MessageUser}</h6>
                <%--            start row three colon, number 1--%>
                <div class="col-lg-4">
                    <div class="mb-3">
                        <label for="first" class="form-label">First name:</label>
                        <input type="text" id="first" name="firstName" value="" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="last" class="form-label">Last Name:</label>
                        <input type="text" id="last" name="lastName" value="" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" id="email" name="email" class="form-control" required>
                    </div>
                    <div class="col-lg-4">
                        <div class="mt-3">
                            <label for="role" class="form-label">Role</label>
                            <select class="form-select" name="role" id="role" aria-label="Default select example"
                                    required>
                                <c:forEach var="r" items="${roleList}">
                                    <option value="${r}">${r}<br></option>
                                </c:forEach>
                            </select>
                        </div>
                        <br>
                    </div>
                    <div class="mb-3">
                        <label for="last" class="form-label">Date Of Birth:</label>
                        <input type=date id="date" name="date" value="" class="form-control" required>
                    </div>
                </div>

            </div>
            <button class="btn btn-info" type="submit">Add User</button>
            -
        </form>
        <div class="col-lg-4">
            <div>
                <form action="get" method="post">
                    <label for="get" class="form-label">Id:</label>
                    <input type="number" id="get" name="userId" value="" class="form-control" required>
                    <button class="btn btn-info" type="submit">Find User by Id</button>
                </form>
            </div>
            <div>

                <c:choose>
                    <c:when test="${!user.equals('User not found')}">
                        <table class="table table-striped table-info">
                            <tr>
                                <td> ${user.getId()}</td>
                                <td> ${user.getFirstName()}</td>
                                <td> ${user.getLastName()}</td>
                                <td>${user.getEmail()}</td>
                                <td>${user.getDateOfBirth()}</td>
                            </tr>
                        </table>

                        <br/>
                    </c:when>
                    <c:otherwise>
                        "${user}"
                        <br/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <%@include file="views/footer.jsp" %>
</body>
</html>