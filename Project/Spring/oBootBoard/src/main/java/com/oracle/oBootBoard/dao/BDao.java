package com.oracle.oBootBoard.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.oracle.oBootBoard.dto.BDto;

public interface BDao {
	public ArrayList<BDto> boardList();
	public BDto contentView(String strId) throws SQLException;
	public int modify(String bId, String bName, String bTitle, String bContent) throws SQLException;
	public int write(String bName, String bTitle, String bContent) throws SQLException;
	public int delete(String bId) throws SQLException;
}
