<%@page import="java.util.ArrayList"%>
<%@page import="green.vo.Score"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학생들 성적 목록</title>
</head>
<body>
	<h1>학생들의 성적 목록입니다.</h1>
	<h2>
		아주 멋있는 학생들 아닙니까!
		<a href="add">멋있는 학생 추가하기</a><br>
	</h2>
	<p>번호|이름| 국 | 영 | 수 | 합 | 평균 | 등급 </p>
	<%
		ArrayList<Score> scores = (ArrayList<Score>) request.getAttribute("scores");
	for (Score score : scores) {
	%>
	<%=score.getNum()%>,&nbsp;
	<a href='update?num=<%=score.getNum()%>'> <%=score.getName()%></a>,&nbsp;  
	<%=score.getKor()%>,&nbsp;
	<%=score.getEng()%>,&nbsp;  
	<%=score.getMath()%>,&nbsp;
	<%=score.getSum()%>,&nbsp;
	<%=score.getAvg()%>,&nbsp;
	<%=score.getGrade()%>
	<br>
	<%
		}
	%>
</body>
</html>