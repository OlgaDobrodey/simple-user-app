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
            <th> TITLE</th>
            <th> AUTHOR</th>
        </tr>
        <c:forEach var="book" items="${listBooks}">
            <tr>
                <td>${book.getBookId()}</td>
                <td>${book.getTitle()}</td>
                <td>${book.getAuthor()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="container">
        </form>
        <div class="col-lg-4">
            <div>
                <form action="books" method="post">
                    <label for="get" class="form-label">Id:</label>
                    <input type="number" id="get" name="bookId" value="" class="form-control" required>
                    <button class="btn btn-info" type="submit">Find book by Id</button>
                </form>
            </div>
            <div>

                <c:choose>
                    <c:when test="${!book.equals('Book not found')}">
                        <table class="table table-striped table-info">
                            <tr>
                                <td> ${book.getBookId()}</td>
                                <td> ${book.getTitle()}</td>
                                <td> ${book.getAuthor()}</td>
                            </tr>
                        </table>

                        <br/>
                    </c:when>
                    <c:otherwise>
                        "${book}"
                        <br/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <%@include file="views/footer.jsp" %>
</body>
</html>