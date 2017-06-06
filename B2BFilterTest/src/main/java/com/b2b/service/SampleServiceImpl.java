package com.b2b.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.b2b.dao.SampleDAO;

@Service
public class SampleServiceImpl implements SampleService{

	@Resource(name="sampleDAO")
    private SampleDAO sampleDAO;
	
	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sampleDAO.selectList(map);
	}

	@Override
	public Map<String, Object> selectListCnt(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sampleDAO.selectListCnt(map);
	}
}
