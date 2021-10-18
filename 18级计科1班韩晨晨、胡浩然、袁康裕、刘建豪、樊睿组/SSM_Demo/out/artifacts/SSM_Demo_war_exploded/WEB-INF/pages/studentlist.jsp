<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="../../static/CSS1.css" type="text/css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script language="javascript">
        function confirmDel(str){
            return confirm(str);
        }
    </script>
    <script>
        function submit1()
        {
            var name = document.getElementById("name").value;
            var tele= document.getElementById("tele").value;
            var findstr="?name="+name + "&tele=" + tele;
            window.location.href = 'http://localhost:8080/student/serch' + findstr;
        }
    </script>
    <script>
        function submit2() {
            var id = document.getElementById("id").value;
            var name = document.getElementById("name").value;
            var tele= document.getElementById("tele").value;
            var findstr="?id=" + id + "&name="+name + "&tele=" + tele;
            window.location.href = 'http://localhost:8080/student/save' + findstr;
        }
    </script>
</head>
<body>
<form method="get">
    <table align="center">
        <input type="hidden" name="id" id="id" value="">
        <tr>
            <th>学生姓名</th>
            <td><input  id="name" /></td>
            <th>学生电话</th>
            <td><input  id="tele" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" value="查询" onclick="submit1()" align="middle" /></td>
            <td colspan="2"><input type="button" value="添加" onclick="submit2()" align="middle"/></td>
        </tr>
    </table>
</form>
<table align="center">
    <tr>
        <th>学生id</th>
        <th>学生姓名</th>
        <th colspan=3>操作</th>
    </tr>
    <c:forEach items="${students}" var="d">
        <tr>
            <td>${d.id}</td>
            <td>${d.name}</td>
            <td><a href="/student/studentDetail?id=${d.id}">详情</a></td>
            <td><a href="student/toEditStudent?id=${d.id}">编辑</a></td>
            <td><a href="student/toDeleteStudent?id=${d.id}"
                   onclick ="javascript:return confirmDel('确定要删除${d.name}吗')">删除</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>