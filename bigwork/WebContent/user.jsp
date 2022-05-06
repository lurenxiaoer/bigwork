<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<title>用户页面</title>
	<link rel="stylesheet" type="text/css" href="css/user&admin.css">
	<link rel="icon" type="image/x-ico" href="images/stu.ico">
	
</head>
	
<body>
	<%
		
		User user = (User) session.getAttribute("user");
	
		if(user != null){
	%>
	<header>
		<div class="title">
			<span>用户操作界面</span>
		</div>
		<nav>
			<div class="userinfo">
				<ul>
					<li><%=user.getUsername() %></li>
					<li><%=user.getLevel() %></li>
					<li><a href="UserExitServlet">退出</a></li>
					<li><a href="login.html">返回</a></li>
				</ul>
			</div>
		</nav>
	</header>
	
	<main>
		<%
		}else{
			response.sendRedirect("login.html");
		}
		%>
		<div class="container">
			<div class="select">
		
				<ul id="accordion" class="accordion">
					<li>
						<div class="link">院系管理</div>
						<ul class="submenu">
							<li><a onclick="query_all('department')">查看院系</a></li>
						</ul>
					</li>
					<li>
						<div class="link">班级管理</div>
						<ul class="submenu">
							<li><a onclick="query_all('class')">查看班级</a></li>
						</ul>
					</li>
					<li>
						<div class="link">学生管理</div>
						<ul class="submenu">
							<li><a  onclick="query_all('student')">查看学生</a></li>
						</ul>
					</li>
					<li>
						<div class="link">课程管理</div>
						<ul class="submenu">
							<li><a onclick="query_all('course')">查看课程</a></li>
							<li><a onclick="course_avg()">查询课程平均分</a></li>
							<li><a onclick="fail_rate()">查询课程不及格率</a></li>
							<li><a onclick="show_course_ranking()">查询课程排名情况</a></li>
						</ul>
					</li>
					<li>
						<div class="link">学生成绩</div>
						<ul class="submenu">
							<li><a  onclick="query_all('sc')">查看全部成绩</a></li>
						</ul>
					</li>
				</ul>
				</div>

				<div id="result" class="result">
					<p class="welcome">来了，老弟！！！</p>
				</div>
			</div>
		
	</main>
	
	<footer>
		<div class="copyright">
			&copy;From Qing Lu Chai Wu Hou <a href="admin.jsp">FourOneTwo</a>
		</div>
	</footer>

	<script src="js/jquery-3.1.1.min.js"></script>
	<script src="js/user.js"></script>
</body>
</html>