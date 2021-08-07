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
    </div>
</nav>
<div class="col-sm-12">
    <div class="container">
        <div class="search-container">
            <form action="/issuebook?action=findIBook" method="post">
                <input type="text" placeholder="Search.." name="findName"/>
                <button type="submit">Search</button>
            </form>
        </div><!-- /.container-fluid -->
        <h2>ISSUE VIEW BOOK</h2>
        <form >
            <table class="table table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th>Callno</th>
                    <th>Student Id</th>
                    <th>Student Name</th>
                    <th>Student Mobile</th>
                    <th>Issued Date</th>
                    <th>Return Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listIssueBook}" var="listissueBook" varStatus="loop">
                    <tr>
                        <td>${listissueBook.callno}</td>
                        <td>${listissueBook.studentid}</td>
                        <td>${listissueBook.studentname}</td>
                        <td>${listissueBook.studentmobile}</td>
                        <td>${listissueBook.issueddate}</td>
                        <td>${listissueBook.returnstatus}</td>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
</body>
</html>