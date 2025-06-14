package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 1.pageNum  Get
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		// 2. Board board 생성 
		Board board = new Board();
		
		// 3. board.setXXX(....)
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setWriter(request.getParameter("writer"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		board.setEmail(request.getParameter("email"));
		//board.setReadcount(Integer.parseInt(request.getParameter("readcount")));
		board.setPasswd(request.getParameter("passwd"));
		//board.setRef(Integer.parseInt(request.getParameter("ref")));
		//board.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		//board.setRe_level(Integer.parseInt(request.getParameter("re_level")));
//		board.setIp(request.getParameter("ip"));
		
		// 3. BoardDao bd Instance
		BoardDao bd = BoardDao.getInstance();

		// 	int result = bd.update(board);
		int result = 0;
		try {
			result = bd.update(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 4. request 객체에 result, num , pageNum
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		
		return "updatePro.jsp";
	}
}
