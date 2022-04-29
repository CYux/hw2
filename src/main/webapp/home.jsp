<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.doublefakefrog.hw2.Models.UserModel" %>
<%@ page import="com.doublefakefrog.hw2.Service.MySQLdb" %>
<%@ page import="java.util.List" %>
<%@ page import="com.doublefakefrog.hw2.Models.BookModel" %>
<%@ page import="sun.util.locale.provider.AvailableLanguageTags" %>
<%@ page import="com.doublefakefrog.hw2.Models.TopicModel" %><%--
  Created by IntelliJ IDEA.
  User: Chan
  Date: 2022/4/19
  Time: 8:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Home</title>
</head>
<body>

<%@include file="header.jsp"%>

<%
    MySQLdb db = new MySQLdb();
    List<TopicModel> topics = db.fetchTopic();
%>

<!--end row-->
<div class="d-flex align-items-center">
    <div>
        <h5 class="mb-0">Recent</h5>
    </div>

</div>


<form method="get" action="home.jsp">
    <select name="topic" id="topic" >
        <option value="" disabled selected hidden>Choose a Topic</option>
        <option value="All">All</option>
        <%
        for (TopicModel topic : topics) {
        %>
        <option value="<%=topic.getTopicId()%>"><%=topic.getTopicName()%></option>
        <%
            }
        %>
    </select>
    <input type="submit" value="Search">
</form>
<div class="table-responsive mt-3">
    <table class="table table-striped table-hover table-sm mb-0">
<%
    List<BookModel> books = db.fetchBook();
    out.println(" <thead>" +
            "            <tr>" +
            "               <th>Book ID</th>" +
            "               <th>Book Name</th> "+
            "               <th>Topic</th>" +
            "               <th>Author</th>" +
            "               <th>Reservation</th>" +
            "            </tr> </thead>");
    for (BookModel book : books) {
        if(request.getParameter("topic")==null || request.getParameter("topic").equals("All"))
            {
            }else if(Integer.parseInt(request.getParameter("topic")) !=book.getTopic_id()) {
                System.out.println(request.getParameter("topic"));
                continue;
            }
%>

<tr>
    <td>
        <%=book.getBook_id()%>
    </td>
    <td>
        <%=book.getBook_name()%>
    </td>
    <td>
        <%=book.getTopic_name()%>
    </td>
    <td>
        <%=book.getAuthor_name()%>
    </td>
    <td><%=(book.isAvailable()? "<a href=\"Reservation?bookID="+book.getBook_id()+"\">Reserve</a>":"Unavailable")%></td>
</tr>
<%
    }
    out.println(" </table>");
%>
</div>
</div>
</div>
</div>
</div>
</div>
</body>
</html>
