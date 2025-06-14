package och02;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Add3
 */
@WebServlet("/Add3")
public class Add3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");//한글깨짐현상 방지
		
		System.out.println("doGet Start");
		int num = Integer.parseInt(request.getParameter("num"));
		String han = request.getParameter("han");
		System.out.println("doGet num->"+num);
		System.out.println("doGet han->"+han);
		
		// num의 누적분을 return 목표
		int sum = 0;
		for(int i=1; i<=num; i++)
		{
			sum += i;
		}
		
		// browser에 Display 하기 위한 방법
		response.setContentType("text/html;charset=utf-8");//한글깨짐현상 해결
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.printf("<h1>han Test : %s</h1>", han);
		out.printf("<h1>1부터 %d까지 합계</h1>", num);
		out.println(sum);
		out.println("</body></html>");
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		request.setCharacterEncoding("utf-8");//한글깨짐현상 방지
		
		System.out.println("doPost Start");
		int num = Integer.parseInt(request.getParameter("num"));
		String han = request.getParameter("han");
		System.out.println("doPost num->"+num);
		System.out.println("doPost han->"+han);
		
		// num의 누적분을 return 목표
		int sum = 0;
		for(int i=1; i<=num; i++)
		{
			sum += i;
		}
		
		// browser에 Display 하기 위한 방법
		response.setContentType("text/html;charset=utf-8");//한글깨짐현상 해결
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.printf("<h1>han Test : %s</h1>", han);
		out.printf("<h1>1부터 %d까지 합계</h1>", num);
		out.println(sum);
		out.println("</body></html>");
		
		out.close();
	}

}
