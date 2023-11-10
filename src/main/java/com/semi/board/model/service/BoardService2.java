package com.semi.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.semi.board.model.vo.Board;
import com.semi.common.model.vo.PageInfo;

public interface BoardService2 {
	int selectSearchCount(HashMap<String, String> map);
	
	ArrayList<Board> selectSearchList(HashMap<String, String> map, PageInfo pi);
}
