package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("ListAction Start...");
		
		BoardDao boardDao = BoardDao.getInstance();
		int totCnt = 0;
		try {
			totCnt = boardDao.getTotalCnt();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("totCnt", totCnt);
		
		return "list.jsp";
	}

}
