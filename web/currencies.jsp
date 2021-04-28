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
<ul>
    <%
        List<String> names = (List<String>) request.getAttribute("model");

        if (names != null && !names.isEmpty()) {
            for (String date : names) {
               response.getWriter().write(
                       "<li>" + "<a href=currency?date=" + date + ">" + date + "</a>" + "</li>");
            }
        }
    %>
</ul>
</body>
</html>
