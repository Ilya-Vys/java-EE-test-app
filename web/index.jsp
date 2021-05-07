<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 26.04.2021
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Web app</title>
  </head>
  <body>
  <div>
    <h1>Currencies app!</h1>
  </div>
  <div class="row">
    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
      <div class="btn-group mr-2" role="group" aria-label="First group">
        <button type="button" class="btn btn-secondary" onclick="location.href='/currency?date=now'">Latest rate</button>
      </div>
      <div class="btn-group mr-2" role="group" aria-label="Second group">
        <button type="button" class="btn btn-secondary" onclick="location.href='/currencies'">Rate for a specific date</button>
      </div>
    </div>
  </div>
  </body>
</html>
