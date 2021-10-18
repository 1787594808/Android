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
            // var name = document.getElementById("name").value;
            // var tele= document.getElementById("tele").value;
            // var findstr="?name="+name + "&tele=" + tele;
            // window.location.href = 'http://localhost:8080/student/serch' + findstr;
        }
    </script>
    <script>
        function submit2() {
            var username = document.getElementById("username").value;
            var userage= document.getElementById("userage").value;
            var selfesc= document.getElementById("selfesc").value;
            var findstr= "?username="+username + "&userage=" + userage+"&selfesc=" + selfesc;
            window.location.href = 'http://localhost:8080/user/save' + findstr;
        }
    </script>
</head>
<body>
<form method="get">
    <table align="center">
        <tr>
            <th>用户姓名</th>
            <td><input  id="username" /></td>
            <th>用户年龄</th>
            <td><input  id="userage" /></td>
            <th>用户??</th>
            <td><input id="selfesc"/> </td>
        </tr>
        <tr>
            <td colspan="2"><input type="button" value="查询" onclick="submit1()" align="middle" /></td>
            <td colspan="2"><input type="button" value="添加" onclick="submit2()" align="middle"/></td>
        </tr>
    </table>
</form>
<table align="center">
    <tr>
        <th>用户id</th>
        <th>用户姓名</th>
        <th>用户年龄</th>
        <th>用户??</th>
        <th colspan=3>操作</th>
    </tr>
    <c:forEach items="${users}" var="d">
        <tr>
            <td>${d.userid}</td>
            <td>${d.username}</td>
            <td>${d.userage}</td>
            <td>${d.selfesc}</td>
            <td><a href="/student/studentDetail?id=${d.userid}">详情</a></td>
            <td><a href="/student/toEditStudent?id=${d.userid}">编辑</a></td>
            <td><a href="/user/toDeleteUser?userid=${d.userid}"
                   onclick ="javascript:return confirmDel('确定要删除${d.username}吗')">删除</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>