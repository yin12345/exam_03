<%@ page language="java" import="java.util.*"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<html>
<head>
	<title>下拉菜单实例</title>
	<meta charset="utf-8">
	<style>
		.dropbtn {
			background-color: #4CAF50;
			color: white;
			padding: 16px;
			font-size: 16px;
			border: none;
			cursor: pointer;
		}

		.dropdown {
			position: relative;
			display: inline-block;
		}

		.dropdown-content {
			display: none;
			position: absolute;
			right: 0;
			background-color: #f9f9f9;
			min-width: 160px;
			box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
		}

		.dropdown-content a {
			color: black;
			padding: 12px 16px;
			text-decoration: none;
			display: block;
		}

		.dropdown-content a:hover {background-color: #f1f1f1}

		.dropdown:hover .dropdown-content {
			display: block;
		}

		.dropdown:hover .dropbtn {
			background-color: #3e8e41;
		}
	</style>
</head>
<body>

<h2>下拉内容的对齐方式</h2>
<p>left 和 right 属性指定了下拉内容是从左到右或从右到左。</p>

<div class="dropdown" style="float:left;">
	<a class="dropbtn">左</a>
	<div class="dropdown-content" style="left:0;">
		<a href="#">嘻嘻哈哈 1</a>
		<a href="#">嘻嘻哈哈 2</a>
		<a href="#">嘻嘻哈哈 3</a>
	</div>
</div>



</body>
</html>
