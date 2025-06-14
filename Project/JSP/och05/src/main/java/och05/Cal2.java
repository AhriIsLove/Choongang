package och05;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Cal2
 */
@WebServlet("/Cal2")
public class Cal2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cal2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body><h1>연산결과</h1>");
		
		try {
			int num1 = Integer.parseInt(request.getParameter("num1"));
			int num2 = Integer.parseInt(request.getParameter("num2"));
			out.printf("%d + %d = %d<p>", num1, num2, (num1+num2));
			out.printf("%d - %d = %d<p>", num1, num2, (num1-num2));
			out.printf("%d * %d = %d<p>", num1, num2, (num1*num2));
			out.printf("%d / %d = %d<p>", num1, num2, (num1/num2));
			
		} catch (NumberFormatException e) {
			out.println(e.getMessage() + " 문자열 입력했어");
		} catch (ArithmeticException e) {
			out.println(e.getMessage() + " 0으로 나누었어");
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		
		out.println("</html></body>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
