<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/11/11
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SerchResult</title>
</head>
<body>
<h1>查询结果</h1>
<table>
    <tr>
        <td>学生id</td>
        <td>学生姓名</td>
        <td>学生电话</td>
    </tr>
    <c:forEach items="${students}" var="d">
        <tr>
            <td>${d.id}</td>
            <td>${d.name}</td>
            <td>${d.tele}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
