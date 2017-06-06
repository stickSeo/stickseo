package com.b2b.service;

import java.util.List;
import java.util.Map;

public interface SampleService {

	// 게시목록 조회
	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;
	// 게시글 개수
	Map<String, Object> selectListCnt(Map<String, Object> map) throws Exception;

	 
                                                                              
}
