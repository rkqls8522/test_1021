package green;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import green.vo.Score;

@SuppressWarnings("serial")
@WebServlet("/student/list")
public class StudentListServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StudentListServlet_doGet에 들어옴");
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greendb?serverTimezone=Asia/Seoul","root","1234");
			st = conn.createStatement();
			String sql = "select * from student;";
			rs = st.executeQuery(sql);
			response.setContentType("text/html; charset=utf-8");
			
			ArrayList<Score> scores = new ArrayList<Score>();
			while(rs.next()) {
				scores.add(new Score()
						.setNum(rs.getInt("num"))
						.setName(rs.getString("name"))
						.setKor(rs.getInt("kor"))
						.setEng(rs.getInt("eng"))
						.setMath(rs.getInt("math"))
						.setSum(rs.getInt("num_sum"))
						.setAvg(rs.getFloat("num_avg"))
						.setGrade(rs.getString("grade")));
			}
			request.setAttribute("scores", scores);
			System.out.println("StudentListServlet_doGet에서 score보관완료, 전달시작");
			RequestDispatcher rd = request.getRequestDispatcher("/student/StudentList.jsp");
			rd.forward(request, response);
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(rs !=null) rs.close();
			}catch(Exception e) {
				
			}
			try {
				if(st != null) st.close();
			}catch(Exception e) {
				
			}
			try {
				if(conn != null) conn.close();
			}catch(Exception e) {
				
			}
		}
		
	}

}
