package och06;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

/**
 * Servlet implementation class Greet
 */
@WebServlet("/Greet")
public class Greet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter log;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Greet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init() : 세상에 나를 선 보인 날!");
		// file 저장을 위해
		try {
			//FileWriter 생성자의 두번째 매개변수'true'는 파일이 있다면 이어서 작성하도록
			log = new PrintWriter(new FileWriter("C:/log/log.txt", true));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Init 오류...");
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("init() : 세상에 유언을 남긴 날!");
		if(log != null) log.close();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String name = request.getParameter("name");
		String msg = name + "님 반가워\r\n";
		System.out.println(name + "님의 사회활동...");
		
		GregorianCalendar gc = new GregorianCalendar();
		String date = String.format("%TF %TT\r\n", gc, gc);
		log.print(date + msg);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body><h1>인사</h1>" + msg);
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
