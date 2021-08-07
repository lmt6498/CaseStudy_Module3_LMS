<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login Admin Form</title>
  <link rel='stylesheet' href='bootstrap.min.css'/>");
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
        <li><a href="/account" class="active">Home</a></li>
        <li><a href="account?action=admin" class="active">Admin</a></li>
        <li><a href="account?action=librarian">Librarian</a></li>
      </ul>

    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div class='container'>
  <h3>Admin Login</h3>
  ${messagelogin}
  <form action="account?action=admin" method="post" style="width:300px">
    <div class="form-group">
      <label for="email1">Email address</label>
      <input type="email" class="form-control" name="email" id="email1" placeholder="Email"/>
    </div>
    <div class="form-group">
      <label for="password1">Password</label>
      <input type="password" class="form-control" name="password" id="password1" placeholder="Password"/>
    </div>
    <button type="submit" class="btn btn-primary">Login</button>
  </form>
</div>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
</body>
</html>