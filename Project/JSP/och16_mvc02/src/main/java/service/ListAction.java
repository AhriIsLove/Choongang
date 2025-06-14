package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.Board;
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
		//총 게시글 수
		int totCnt = 0;
		try {
			totCnt = boardDao.getTotalCnt();
			
			//페이지 번호
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null || pageNum.equals("")) {
				pageNum = "1";
			}
			//현재 페이지 번호
			int currentPage = Integer.parseInt(pageNum);
			//페이지당 최대 게시글 수 
			int pageSize = 10;
			//이동 가능한 최대 페이지 수
			int blockSize = 10;
			//페이지의 시작 게시글 번호
			int startRow = (currentPage - 1) * pageSize + 1;
			//페이지의 마지막 게시글 번호
			int endRow = startRow + pageSize - 1;
			//게시글의 번호
			int startNum = totCnt - startRow + 1;
			
			//한 페이지에 보여줄 게시글들 가져오기
			List<Board> boardList  = boardDao.boardList(startRow, endRow);
			//총 페이지 수
			int pageCnt = (int)Math.ceil((double)totCnt/pageSize);
			System.out.println("currentPage : " + currentPage);
			System.out.println("blockSize : " + blockSize);
			
			//이동 가능한 페이지 중 시작 페이지
			int startPage = (int)(currentPage-1)/blockSize*blockSize + 1;
			int startPage3 = (int)(currentPage-1) + 1;//테스트용
			System.out.println("startPage : " + startPage);
			System.out.println("startPage3 : " + startPage3);
			
			//이동 가능한 페이지 중 마지막 페이지
			int endPage = startPage + blockSize - 1;
			if(endPage > pageCnt) endPage = pageCnt;

			request.setAttribute("totCnt", totCnt);
			request.setAttribute("boardList", boardList);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "list.jsp";
	}

}
