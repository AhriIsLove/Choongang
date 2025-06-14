package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 1. num , pageNum  GET
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		// 2. BoardDao bd Instance
		BoardDao bd = BoardDao.getInstance();
		// 3. Board board = bd.select(num);
		Board board = null;
		try {
			board = bd.select(num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 4. request 객체에 pageNum, board
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", board);
		
		return "updateForm.jsp";
	}

}
