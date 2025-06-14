package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ContentAction Service start...");
		
		//게시글 번호
		int num = Integer.parseInt(request.getParameter("num"));
		//페이지 번호(String) : 어차피 바로 반활할 데이터라서 parsing할 필요 없음 
		String pageNum = request.getParameter("pageNum");

		BoardDao boardDao = BoardDao.getInstance();		
		Board board = null;
		try {
			//게시글 정보 가져오기
			board = boardDao.select(num);
			
			//게시글의 조회수 증가
			boardDao.readCount(num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("board", board);
		
//		//board 자체를 넘기는 테스트
//		Board board = request.getParameter("board");
//		request.setAttribute("board", board);
		
		return "content.jsp";
	}

}
