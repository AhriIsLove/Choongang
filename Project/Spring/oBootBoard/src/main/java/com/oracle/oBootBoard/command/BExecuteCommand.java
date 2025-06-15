package com.oracle.oBootBoard.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oracle.oBootBoard.dao.BDao;
import com.oracle.oBootBoard.dto.BDto;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BExecuteCommand {
	private final BDao jdbcDao;
	@Autowired
	public BExecuteCommand(BDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	
	public void bListCmd(Model model) {
		ArrayList<BDto> boardDtoList = jdbcDao.boardList();
		System.out.println("BListCommand boardDtoList.size() : " + boardDtoList.size());
		model.addAttribute("boardList", boardDtoList);
	}

	public void bContentCmd(Model model) {
		// ----- Chapter 1
		// 1. model Map선언
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
//		String bName = request.getParameter("bName");
//		String bTitle = request.getParameter("bTitle");
//		String bContent = request.getParameter("bContent");
		
		BDto board = null;
		try {
			board = jdbcDao.contentView(bId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		model.addAttribute("mvc_board", board);
	}

	public void bModifyCmd(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");

		try {
			jdbcDao.modify(bId, bName, bTitle, bContent);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void bDeleteCmd(Model model) {
//		1)  model이용 , map 선언
//		2) request 이용 ->  bId 추출
//		3) dao  instance 선언
//		4) delete method 이용하여 삭제
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String bId = request.getParameter("bId");

		try {
			jdbcDao.delete(bId);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void bWriteCmd(Model model) {
//		1) model이용 , map 선언
//		2) request 이용 ->  bName  ,bTitle  , bContent  추출
//		3) dao  instance 선언
//		4) write method 이용하여 저장(bName, bTitle, bContent)
//			bid, bGroup,bHit,  bStep, bIndent, bDate -> mvc_board_seq,mvc_board_seq, 0 , 0 , 0, 	sysdate
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");

		try {
			jdbcDao.write(bName, bTitle, bContent);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void bReplyViewCmd(Model model) {
//		1)  model이용 , map 선언
//		2) request 이용 ->  bid  추출
//		3) dao  instance 선언
//		4) reply_view method 이용하여 (bid)
//			- BDto dto = dao.reply_view(bId);
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
		BDto dto = null;
		try {
			dto = jdbcDao.reply_view(bId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("reply_view", dto);
	}

	public void bReplyCmd(Model model) {
//		1)  model이용 , map 선언
//		2) request 이용 -> bid,         bName ,  bTitle,
//		                  bContent ,  bGroup , bStep ,
//		                  bIndent 추출
//		3) dao  instance 선언
//		4) reply method 이용하여 댓글저장 
//		  - dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bGroup = request.getParameter("bGroup");
		String bStep = request.getParameter("bStep");
		String bIndent = request.getParameter("bIndent");
		
		try {
			jdbcDao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
