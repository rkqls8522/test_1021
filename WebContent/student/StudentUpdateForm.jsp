<%@page import="java.util.ArrayList"%>
<%@page import="green.vo.Score"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="score" scope="request" class="green.vo.Score" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>수정할 정보를 입력</title>
</head>
<body>
	<h1>학생 정보</h1>
	<form action="update" method="post">
		번호 : <input type="text" name= "num" value="<%=score.getNum()%>" readonly="readonly"><br> 
		이름 : <input type="text" name="name" value="<%=score.getName()%>"><br> 
		국어 : <input type="text" name="kor" value="<%=score.getKor()%>"><br>
		영어 : <input type="text" name="eng" value="<%=score.getEng()%>"><br>
		수학 : <input type="text" name="math" value="<%=score.getMath()%>"><br>
		<input type="submit" value="저장">
		<input type="reset" value="다시 작성">
	</form>
</body>
</html>
