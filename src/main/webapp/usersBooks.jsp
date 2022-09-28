<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">
</head>
<body>
<%@include file="views/header.jsp" %>

<div class="container">

    <c:choose>
        <c:when test="${user!= null}">
            <h4 class="text-info text-right-">${user.getFirstName()} ${user.getLastName()}</h4></c:when>
        <c:otherwise> "${user}"</c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${!bookList.equals('No found book for user')}">
            <table class="table table-striped table-info">
                <tbody>
                <tr>
                    <th>ID</th>
                    <th> TITLE</th>
                    <th> AUTHOR</th>
                </tr>
                <c:forEach var="book" items="${bookList}">
                    <tr>
                        <td>${book.getBookId()}</td>
                        <td>${book.getTitle()}</td>
                        <td>${book.getAuthor()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>${bookList}</c:otherwise>
    </c:choose>
</div>

<%@include file="views/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>