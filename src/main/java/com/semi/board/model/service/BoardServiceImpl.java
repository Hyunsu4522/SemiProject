package com.semi.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.semi.board.model.dao.BoardDao2;
import com.semi.board.model.vo.Board;
import com.semi.common.Template;
import com.semi.common.model.vo.PageInfo;

public class BoardServiceImpl implements BoardService2{
private BoardDao2 bDao = new BoardDao2();
	
	



	

	@Override
	public int selectSearchCount(HashMap<String, String> map) {
		SqlSession sqlSession = Template.getSqlSession();
		int searchCount = bDao.selectSearchCount(sqlSession, map);
		
		sqlSession.close();
		return searchCount;
	}

	@Override
	public ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		ArrayList<Board> list = bDao.selectSearchList(sqlSession, map, pi);
	
		sqlSession.close();
		return list;
	}
	
}
