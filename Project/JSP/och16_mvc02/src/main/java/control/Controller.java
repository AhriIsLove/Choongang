package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CommandProcess;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config /* web.xml */) throws ServletException {
		// TODO Auto-generated method stub
		String props = config.getInitParameter("config"); // init-param에서 name이 config인 값을 가져온다 : '/WEB-INF/command.properties'

		System.out.println("1. init String props : " + props);

		Properties pr = new Properties();
		FileInputStream f = null;

		String configFilePath = config.getServletContext().getRealPath(props); // command.properties
		System.out.println("2. init String configFilePath : " + configFilePath);

		try {
			f = new FileInputStream(configFilePath); // /list.do=service.ListAction

			// pr
			// key : list.do,writeForm.do
			// value : service.ListAction,service.WriteFormAction
			pr.load(f);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				f.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("IOException ex.getMessage() : " + e.getMessage());
			}
		}

		// /list.do=service.ListAction
		Iterator<Object> keyIter = pr.keySet().iterator();
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();// list.do,writeForm.do
			String className = pr.getProperty(command);// service.ListAction,service.WriteFormAction

			System.out.println("3. init command : " + command);
			System.out.println("4. init className : " + className);

			try {
				//해당 문자열을 클래스로 만든다.
				//service.ListAction listAction = new service.ListAction();
				// => 서비스간 '약연결'을 하기 위함
				// 코드의 유연성과 확장성을 높이고
				// 테스트를 용이하게 하며
				// IoC (제어의 역전) 원칙을 따르기 위함
				Class<?> commandClass = Class.forName(className);
				CommandProcess commandInstance = (CommandProcess) commandClass.getDeclaredConstructor().newInstance();

				// list.do 		String Key, service.ListAction 객체
				// writeForm.do String Key, service.WriteFormAction 객체
				commandMap.put(command, commandInstance);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		requestServletPro(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void requestServletPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = null;
		CommandProcess com = null;
		String command = request.getRequestURI();
//		StringBuffer command
		
		System.out.println("1-1. requestServletPro URI command : " + command);
		command = command.substring(request.getContextPath().length());//경로 폴더 삭제하고 파일명만 남김
		System.out.println("1-2. requestServletPro URI command substring : " + command);
		
		try {
			com = (CommandProcess) commandMap.get(command);
			System.out.println("1-3. requestServletPro com : " + com); //ListAction
			view = com.requestPro(request, response);
			System.out.println("1-4. requestServletPro view : " + view); //ListAction의 부모 인터페이스 requestPro()실행 결과 값 : "list.jsp"
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
		
		//view("list.jsp")로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
