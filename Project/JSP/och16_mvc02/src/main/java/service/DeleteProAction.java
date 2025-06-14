package service;

import java.io.IOException;

import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("DeleteFroAction start...");
		
		try {
			// 1. num , passwd , pageNum Get
			int num = Integer.parseInt(request.getParameter("num"));
			String passwd = request.getParameter("passwd");
			String pageNum = request.getParameter("pageNum");
			// 2. BoardDao bd Instance
			BoardDao bd = BoardDao.getInstance();
			// 3. 본인의 게시판 만 삭제 int result = bd.delete(num, passwd);
			int result = bd.delete(num, passwd);
			// 4. request 객체에  num , pageNum ,result
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("result", result);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return "deletePro.jsp";
	}

}
