package green;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import green.vo.Score;

@SuppressWarnings("serial")
@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StudentUpdateServlet_doGet에 들어옴.");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/greendb?serverTimezone=Asia/Seoul", // JDBC URL
					"root", 
					"1234");
			stmt = conn.createStatement();
			String sql = "select * from student where num = " + request.getParameter("num");
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				request.setAttribute("score", new Score().setNum(rs.getInt("num")).setName(rs.getString("name"))
						.setKor(rs.getInt("kor")).setEng(rs.getInt("eng")).setMath(rs.getInt("math"))
						);
			} else {
				throw new Exception("해당 번호의 학생을 찾을 수 없습니다");
			}
			RequestDispatcher rd =request.getRequestDispatcher("/student/StudentUpdateForm.jsp");
			rd.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("StudentUpdateServlet_doPost에 들어옴");
		request.setCharacterEncoding("utf-8");
		Connection conn = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/greendb?serverTimezone=Asia/Seoul", // JDBC URL
					"root",
					"1234");
			System.out.println("StudentUpdateServlet_doPost에서의 DB 접속 성공 " + conn);
			String sql = "update student set name=?,kor=?,eng=?,math=?,num_sum=?,num_avg=?,grade=? where num=?";
			st = conn.prepareStatement(sql);
			
			int kor = Integer.parseInt(request.getParameter("kor"));
			int eng = Integer.parseInt(request.getParameter("eng"));
			int math = Integer.parseInt(request.getParameter("math"));
			int sum = kor+eng+math;
			float avg = sum/3;
			
			String grade;
			if(avg > 90) grade="수";
			else if(avg > 80) grade="우";
			else if(avg > 70) grade="미";
			else if(avg > 60) grade="양";
			else grade="가";
			
			st.setString(1, request.getParameter("name"));
			st.setInt(2, kor);
			st.setInt(3, eng);
			st.setInt(4, math);
			st.setInt(5, sum);
			st.setFloat(6, avg);
			st.setString(7, grade);
			st.setInt(8, Integer.parseInt(request.getParameter("num")));
			st.execute();
			
			System.out.println("StudentUpdateServlet_doPost에서 값을 넣은 후 이제 list로 이동합니다.");
			response.sendRedirect("list");
		} catch (Exception e) {
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}


}
