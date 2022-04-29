<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Catalog</title>
    <link type="text/css" rel="stylesheet" href="css/style.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
          crossorigin="anonymous">
</head>
<body>

<div>

<% if(request.getSession().getAttribute("user")!= null) {
    request.getRequestDispatcher("home.jsp").forward(request, response);
} %>
    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-5">
                <div class="card cardbox">
                    <div class="card-header">Login</div>
                    <div class="card-body">

    <form method="post" action="Login">
    <label for="username">Username:
    <input type="text" id="username" name="username" required/> </label><br/>
    <label for="password">Password:
    <input type="password" id="password" name="password" required/> </label><br/>
    <input type="hidden" name="user_type" value="user"/>
    <input type="submit" value="Submit"/>
</form>


<p>
    ${error}
</p>

                    </div>
                </div>

                <div class="login-or">
                    <hr class="hr-or">
                    <span class="span-or">or</span>
                </div>

                <div class="card cardbox">
                            <div class="card-header">Register</div>
                            <div class="card-body">


<form method="post" action="Register">
    First Name: <label>
    <input type="text" name="firstname" required/>
</label><br/>
    Last Name: <label>
    <input type="text" name="lastname" required/>
</label><br/>
    User name: <label>
    <input type="text" name="username" required/>
</label><br/>
    <label>
        Email:
        <input type="text" name="email" required/>
    </label><br/>
    <label>
        Password:
        <input type="password" id="reg-pass" name="password" onchange='check();' required/>
    </label><br/>
    Confirm Password: <input type="password"  id="confirm-reg-pass" name="password" onchange='check();'  required/><br/>
    <input id="submit" type="submit" value="Submit" />
</form>

<p id='message'>${msg}</p>
</div>
                </div>

            </div>
        </div>
    </div>
</div>
</div>
</body>
<script>
    var check = function() {
        if (document.getElementById('reg-pass').value ==
            document.getElementById('confirm-reg-pass').value) {
            document.getElementById('message').style.color = 'green';
            document.getElementById('message').innerHTML = 'Confirm Password Matching';
            document.getElementById('submit').disabled = false;
        } else {
            document.getElementById('message').style.color = 'red';
            document.getElementById('message').innerHTML = 'Password Not Matching';
            document.getElementById('submit').disabled = true;
        }
    }</script>
</html>