<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020/11/9
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<form action="/student/save" method="post">
    <table>
        <tr>
            <td>学生姓名</td>
            <td><input name="name" /></td>
        </tr>
        <tr>
            <td>学生电话</td>
            <td><input name="tele" /></td>
        </tr>
        <tr>
            <td colspan=2><input type="submit" value="submit"/></td>
        </tr>
    </table>
</form>
</body>
</html>
