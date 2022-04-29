<%@ page import="com.doublefakefrog.hw2.Models.UserModel" %><%--
  Created by IntelliJ IDEA.
  User: Chan
  Date: 2022/4/21
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="https://cdn.lineicons.com/3.0/lineicons.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/boxicons@2.0.7/css/boxicons.min.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container">
    <div class="row">
        <div class="col-12 col-lg-3">
            <div class="card">
                <div class="card-body">
                    <div class="d-grid">
                        <a href="home.jsp" class="btn ">
                            <h2>Library</h2>
                        </a>

                    </div>
                    <h5 class="my-3">Actions</h5>
                    <div class="fm-menu">
                        <div class="list-group list-group-flush">

                            <%  UserModel user = null;
                                if (session.getAttribute("user") != null){
                                    user = (UserModel)session.getAttribute("user");
                            %>
                            <a href="home.jsp" class="list-group-item py-1">
                                </i><span style="margin-left: 10px; padding:2px">Home</span></a>
                            <a href="reservation.jsp" class="list-group-item py-1">
                                </i><span style="margin-left: 10px; padding:2px">Reservation</span></a>
                            <a href="Logout" class="list-group-item py-1">
                                </i><span style="margin-left: 10px; padding:2px">Logout</span></a>
                            <%}else{%>
                            <a href="login.jsp" class="list-group-item py-1">
                                </i><span style="margin-left: 10px; padding:2px">Login</span></a>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>




        <div class="col-12 col-lg-9">
            <div class="card">
                <div class="card-body">
                    <div class="ms-auto"><h6>Hi <%=user.getUsername()%> Welcome!</h6>
                    </div>