package och02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Person
 */
@WebServlet("/Person")
public class Person extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Person() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * HW02
		 * 개인정보 이름 : 김유신3 아이디 : bb 암호 : 1 성별 : 남자 수신메일 : 공지 배송 직업 : 장군
		 */
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		
		pw.println("<h1>개인정보</h1>");
		pw.printf("이름 : %s<br>", request.getParameter("name"));
		pw.printf("아이디 : %s<br>", request.getParameter("id"));
		pw.printf("암호 : %s<br>", request.getParameter("password"));
		pw.printf("성별 : %s<br>", request.getParameter("gender"));
		pw.printf("수신메일 : ");
		String[] notices = request.getParameterValues("notice");
		if(notices != null) {
			for(String notice : notices) {
				pw.printf("%s ", notice);
			}
		}else pw.printf("없음");
		
		pw.printf("<br>");
		pw.printf("직업 : %s<br>", request.getParameter("job"));
		
		pw.println("</html></body>");
		pw.close();
	}

}
