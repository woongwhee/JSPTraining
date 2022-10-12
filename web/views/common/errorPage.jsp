<%--
  Created by IntelliJ IDEA.
  User: jinunghwi
  Date: 2022/10/06
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String errorMsg=(String) request.getAttribute("errorMsg");

%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>errorPage</title>
  <style>

  </style>
</head>
<body>
  <h1 style="color: red;text-align: center;"><%=errorMsg%></h1>
</body>
</html>