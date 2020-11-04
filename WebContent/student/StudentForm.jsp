<%@page import="java.util.ArrayList"%>
<%@page import="green.vo.Score"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="score" scope="request" class="green.vo.Score" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학생추가</title>
</head>
<body>
	<h1>추가</h1>
	<form action="add" method="post">
		이름 :<input type = "text" name="name"><br>
		국어 :<input type="text" name="kor"><br>
		영어 :<input type="text" name="eng"><br>
		수학 :<input type="text" name="math"><br>
		<input type="submit" value="전송"><br>
		<input type="reset" value="다시작성">
	</form>
</body>
</html>