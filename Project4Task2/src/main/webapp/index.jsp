<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  Author: weihuang
  AndrewID:weih2
  Date: 2022/11/18
  Time: 3:26 PM
--%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="getMongoServlet" method="GET">
    <input type="submit" name="Renew" value="Log" />
</form>
</body>
</html>