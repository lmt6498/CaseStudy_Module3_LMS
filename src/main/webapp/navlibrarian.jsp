<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Book Form</title>
    <link rel='stylesheet' href='bootstrap.min.css'/>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/account">eLibrary</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/account">Home</a></li>
                <li><a href="/book?action=AddBookForm">Add Book</a></li>
                <li><a href="/book?action=ViewBook">View Book</a></li>
                <li><a href="/issuebook?action=IssueBookForm">Issue Book</a></li>
                <li><a href="/issuebook?action=ViewIssuedBook">View Issued Book</a></li>
                <li><a href="/book?action=ReturnBookForm">Return Book</a></li>
                <li><a href="account?action=logout">Logout</a></li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="col-sm-12">
    <div class="container">
        <h2>THONG TIN TAI LIEU</h2>
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>Callno</th>
                <th>Name</th>
                <th>Author</th>
                <th>Image</th>
                <th>Publisher</th>
                <th>Quantity</th>
                <th>Issued</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listBook}" var="listbook" varStatus="loop">
                <tr>
                    <td>${listbook.callno}</td>
                    <td>${listbook.name}</td>
                    <td>${listbook.author}</td>
                    <td><img src="/images/${listbook.image}" width="100" height="100"></td>
                    <td>${listbook.publisher}</td>
                    <td>${listbook.quantity}</td>
                    <td>${listbook.issued}</td>

                    <td>
                        <form>
                            <a href="/book?action=Edit&index=${loop.index}" class="btn btn-warning">Edit</a>
                        </form>
                    </td>
                    <td>
                        <form>
                            <a href="/book?action=DeleteBook&index=${loop.index}"
                               class="btn btn-danger">Delete</a>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
</body>
</html>