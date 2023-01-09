<%@ page import="java.util.List" %>
<%@ page import="com.example.project4task2.APIRequest" %>
<%@ page import="com.example.project4task2.APIResponse" %>
<%--
  Created by IntelliJ IDEA.
  Author: weihuang
  AndrewID:weih2
  Date: 2022/11/18
  Time: 3:26 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--reference: https://github.com/CMU-Heinz-95702/Lab2-InterestingPicture-->
<html>
<head>
    <title>Log Analysis</title>
</head>
<body>
<h1>Poetry API Analysis</h1>
<form action="getMongoServlet" method="GET">
    <input type="submit" name="Renew" value="said" />
</form>
<h2>The most popular method:  <%= request.getAttribute("popularMethod")%> times</h2>
<h2>Average response time:  <%= request.getAttribute("AvgResTime")%> minutes</h2>
<h2>Total number of request:  <%= request.getAttribute("numReq")%></h2>

<h2>Log for Request</h2>
<table>
    <tr>
        <th>Request ID</th>
        <th>Method</th>
        <th>Request Time</th>
        <th>Request Data</th>
    </tr>
    <%  List requestLog = (List) request.getAttribute("reqLog");
        for (int i = 0; i<requestLog.size();i++) {
            APIRequest tmp = (APIRequest) requestLog.get(i);%>
    <tr>
    <td><%= tmp.getId()%></td>
    <td><%= tmp.getMethod()%></td>
    <td><%= tmp.getDate()%></td>
    <td><%= tmp.getData()%></td>
  </tr>
    <%  }%>

</table>
<h2>Log for Response</h2>
<table>
    <tr>
        <th>Response ID</th>
        <th>Request ID</th>
        <th>Response Time</th>
        <th>Response Data</th>
    </tr>
    <%  List responseLog = (List) request.getAttribute("resLog");
        for (int i = 0; i<responseLog.size();i++) {
            APIResponse tmp = (APIResponse) responseLog.get(i);%>
    <tr>
        <td><%= tmp.getId()%></td>
        <td><%= tmp.getRequestID()%></td>
        <td><%= tmp.getDate()%></td>
        <td><%= tmp.getData()%></td>
    </tr>
    <%  }%>

</table>
</body>
</html>
