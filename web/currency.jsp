<%@ page import="java.io.PrintWriter" %>
<%@ page import="example.CurrenciesFromCBR" %>
<%@ page import="example.CurrencyRateFromCBR" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 28.04.2021
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Currency</title>
</head>
<body>
<a href="index.jsp">-Go back to main menu</a>
<%
    CurrenciesFromCBR rate = (CurrenciesFromCBR) request.getAttribute("model");
    PrintWriter writer = response.getWriter();
    writer.print("<li>" + "The ruble exchange rate for a date : " + rate.getLocalDate() + "</li>");

%>
<div>
    <table>
        <thead>
        <tr>
            <td>NumCode</td>
            <td>CharCode</td>
            <td>Nominal</td>
            <td>Name</td>
            <td>Value</td>
        </tr>
        </thead>
        <%
            List<CurrencyRateFromCBR> rates = rate.getRates();
            for (CurrencyRateFromCBR rateFromCBR : rates) {%>
        <tr>
            <td><%=rateFromCBR.getNumCode()%>
            </td>
            <td><%=rateFromCBR.getCharCode()%>
            </td>
            <td><%=rateFromCBR.getNominal()%>
            </td>
            <td><%=rateFromCBR.getName()%>
            </td>
            <td><%=rateFromCBR.getValue()%>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
