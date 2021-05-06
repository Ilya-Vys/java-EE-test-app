<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 26.04.2021
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row">
    <div class="col">
        <h1>Choose a date</h1>
    </div>
</div>
<div class="row">
    <form method="post">
        <div class="form-group row">
            <div class="col">
                <%
                    LocalDate localDate = LocalDate.now();
                %>
                <input type="date" class="form-control" min="2020-01-01" max="<%= localDate %>" value="<%= localDate %>" name="date">
            </div>
            <div>
                <button type="submit" class="btn btn-secondary">Submit</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
