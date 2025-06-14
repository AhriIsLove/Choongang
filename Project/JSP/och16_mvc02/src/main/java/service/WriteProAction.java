package service;

import java.io.IOException;
import java.sql.SQLException;

import dao.Board;
import dao.BoardDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 1. num, pageNum, writer, email, subject, passwd, content
		// 2. Board board 생성하고 Value Setting
		// num, ref = MAX(num)+1
		// re_level, re_step, readcount = 0
		// reg_date = sysdate
		// 나머진는 입력 받은것 그대로
        String pageNum = request.getParameter("pageNum");
        Board board = new Board();
		board.setNum(Integer.parseInt(request.getParameter("num")));
        board.setWriter(request.getParameter("writer"));
        board.setEmail(request.getParameter("email"));
        board.setSubject(request.getParameter("subject"));
        board.setPasswd(request.getParameter("passwd"));
		board.setRef(Integer.parseInt(request.getParameter("ref")));
		board.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		board.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		board.setContent(request.getParameter("content"));
		//IP Set
		board.setIp(request.getRemoteAddr());
		
		// 3. BoardDao bd Instance
		BoardDao bd = BoardDao.getInstance();
		
		// 4. int result = bd.insert(board);
		int result = 0;
		try {
			result = bd.insert(board);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 5. requset 객체에 result, num, pageNum
		request.setAttribute("result", result);
		request.setAttribute("num", board.getNum());
		request.setAttribute("pageNum", pageNum);
		
		return "writePro.jsp";
	}

}
