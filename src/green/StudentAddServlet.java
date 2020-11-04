package green;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/student/add")
public class StudentAddServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StudentAddServlet_doGet안에 들어옴");
		RequestDispatcher rd = request.getRequestDispatcher("/student/StudentForm.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/greendb?serverTimezone=Asia/Seoul","root","1234");
			System.out.println("StudentAddServlet_doPost에서 db접속 성공 conn : " + conn);
			
			String sql = "insert into student values(null,?,?,?,?,?,?,?);";
			st = conn.prepareStatement(sql);
			st.setString(1, request.getParameter("name"));
			
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
			
			st.setInt(2, kor);
			st.setInt(3, eng);
			st.setInt(4, math);
			st.setInt(5, sum);
			st.setFloat(6, avg);
			st.setString(7, grade);
			
			st.executeUpdate();
			System.out.println("StudentAddServlet_doPost에서 값을 넣은 후 이제 list로 이동합니다.");
			response.sendRedirect("list");
			
		}catch(Exception e) {
			System.out.println(e);
		}finally {
			try {
				if(st != null)
					st.close();
			}catch(Exception e) {
				
			}
			try {
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				
			}
		}
		
	}

}
