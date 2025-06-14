package och14;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class LoginCheck
 */
//@WebFilter("/LoginCheck")
@WebFilter("/sub2/*")
public class LoginCheck extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//어느 페이지든 접속하면 필터가 발동!
		System.out.println("LoginCheck doFilter1...");
		
		//Servlet의 파라미터 형식으로 변경
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		//정상적으로 로그인을 하였는지 session을 통하여 확인!
		// Session 도출 방법
		HttpSession session = httpServletRequest.getSession();
		System.out.println("LoginCheck doFilter2...");
		//비정상 접근이라면(세션이 없다면)
//		session은 항상 있고 session..getAttribute("id")를 확인해야 하지 않을까..
		if(session == null || session.equals("")) {
			httpServletResponse.sendRedirect("../login.jsp");
		}
		System.out.println("doFilter session != null");
		String id = (String)session.getAttribute("id");
		System.out.println("doFilter session id : " + id);

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
