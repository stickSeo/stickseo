package com.b2b.service;

import java.util.List;
import java.util.Map;

public interface SampleService {

	// �Խø�� ��ȸ
	List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception;
	// �Խñ� ����
	Map<String, Object> selectListCnt(Map<String, Object> map) throws Exception;

	 
                                                                              
}
