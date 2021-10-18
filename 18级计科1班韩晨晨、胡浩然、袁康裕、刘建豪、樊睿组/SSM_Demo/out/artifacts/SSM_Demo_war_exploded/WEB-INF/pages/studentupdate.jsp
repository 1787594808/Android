<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学生信息更改</title>
</head>
<body>

<form action="/student/save" method="post">
    <table>
        <input type="hidden" name="id" value="${student.id}">
        <tr>
            <td>学生姓名</td>
            <td><input id="name" name="name" value="${student.name}" /></td>
        </tr>
        <tr>
            <td>学生电话</td>
            <td><input id="tele" name="tele" value="${student.tele}" /></td>
        </tr>
        <tr>
            <td colspan=2><input type="submit" value="submit"/></td>
        </tr>
    </table>
</form>
</body>
</html>