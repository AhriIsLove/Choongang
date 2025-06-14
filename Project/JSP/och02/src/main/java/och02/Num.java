package och02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Num
 */
@WebServlet("/Num")
public class Num extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Num() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		/*
		 * HW01
		 * 연산결과 덧셈 : 100 + 2 = 102 뺄셈 : 100 - 2 = 98 곱셈 : 100 * 2 = 200 나눗셈 : 100 / 2 =
		 * 50
		 */
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		pw.println("<h1>연산결과</h1>");
		pw.printf("덧셈 : %d + %d = %d<br>", num1, num2, num1+num2);
		pw.printf("뺄셈 : %d - %d = %d<br>", num1, num2, num1-num2);
		pw.printf("곱셈 : %d * %d = %d<br>", num1, num2, num1*num2);
		if(num2 != 0) {
			pw.printf("나눗셈 : %d / %d = %d<br>", num1, num2, num1/num2);
		}
		else {
			pw.printf("나눗셈 불가능<br>");
		}
		pw.println("</html></body>");
		pw.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
