package com.oracle.oBootBoardWarMaven.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.oracle.oBootBoardWarMaven.dto.BDto;

public interface BDao {
	public ArrayList<BDto> boardList();
	public BDto contentView(String strId) throws SQLException;
	public int modify(String bId, String bName, String bTitle, String bContent) throws SQLException;
	public int write(String bName, String bTitle, String bContent) throws SQLException;
	public int delete(String bId) throws SQLException;
	public BDto reply_view(String bId) throws SQLException;
	public int reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep,
			String bIndent) throws SQLException;
}
