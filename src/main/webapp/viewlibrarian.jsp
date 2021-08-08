<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>View Librarian</title>
<link rel='stylesheet' href='bootstrap.min.css'/>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
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
                <li><a href="account?action=addlibrarian">Add Librarian</a></li>
                <li><a href="account?action=showlib">View Librarian</a></li>
                <li><a href="account?action=showuser">View User</a></li>
                <li><a href="account?action=logout">Logout</a></li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class='container'>
    <table class='table table-bordered table-striped'>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Mobile</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
<c:forEach items="${listLib}" var="x" varStatus="loop">
    <tr>
        <td>${x.id}</td>
        <td>${x.name}</td>
        <td>${x.email}</td>
        <td>${x.password}</td>
        <td>${x.mobile}</td>
        <td><a href='account?action=editlib&id=${x.id}'>Edit</a></td>
        <td><a href='account?action=deletelib&id=${x.id}'>Delete</a></td></tr>
</c:forEach>
    </table>
</div>

<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
</body>
</html>