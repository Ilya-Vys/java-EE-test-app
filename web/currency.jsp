<%@ page import="java.io.PrintWriter" %>
<%@ page import="example.CurrencyRate" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 28.04.2021
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Currency</title>
</head>
<body>
<a href="index.jsp">-Go back to main menu</a>
<ul>
    <%
        CurrencyRate rate = (CurrencyRate) request.getAttribute("model");
        PrintWriter writer = response.getWriter();
        writer.print("<li>" + "the euro to US dollar rate is: " + rate.getUsDollar()+ "</li>");
        writer.print("<li>" + "the euro to canadian dollar rate is: " + rate.getCanadianDollar()+ "</li>");
        writer.print("<li>" + "the euro to UK pound sterling rate is: " + rate.getPoundSterling()+ "</li>");
        writer.print("<li>" + "the euro to japanese yen rate is: " + rate.getJapaneseYen()+ "</li>");
        writer.print("<li>" + "the euro to chinese yuan rate is: " + rate.getChineseYuan()+ "</li>");
        writer.print("<li>" + "the euro to russian rouble rate is: " + rate.getRussianRouble()+ "</li>");

    %>
</ul>

</body>
</html>
