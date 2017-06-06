package com.b2b.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.service.SampleServiceImpl;
import com.b2b.pagecomponent.PageComponent;

@Controller
public class BbsController {

	 @Autowired
	 private SampleServiceImpl sampleService;
	 
	 // 게시판 조회
	 @RequestMapping(value=PageComponent.boaldList)
	 public ModelAndView openSampleBoardList(Map<String,Object> commandMap, HttpServletRequest request) throws Exception{
	   
		ModelAndView mv = new ModelAndView(PageComponent.boaldList);
		
		 int startPnum = 1;
		 if ( request.getParameter("startPnum")!=null ) 
		 {	 
			 startPnum = Integer.parseInt( request.getParameter("startPnum") ); 
		 }
		commandMap.put("pageNum", startPnum);
		commandMap.put("pageRow", 5);
		
		
		Map<String,Object> total_cnt = sampleService.selectListCnt(commandMap);
	    List<Map<String,Object>> list = sampleService.selectBoardList(commandMap);
	    
	    mv.addObject("total_cnt", total_cnt);
	    mv.addObject("list", list);
	    
	    // map 초기화
	 	commandMap.clear();
	 	
	    return mv;
	 }
	 
	 // 게시판 검색
	 @RequestMapping(value=PageComponent.searchList)
	 public ModelAndView searchBoardList(Map<String,Object> commandMap, HttpServletRequest request) throws Exception{
	   
		ModelAndView mv = new ModelAndView(PageComponent.boaldList);
		
		// 검색어
		commandMap.put("searchKey", request.getParameter("searchKey")); 
		int startPnum = 1;
		 if ( request.getParameter("startPnum")!=null ) 
		 {	 
			 startPnum = Integer.parseInt( request.getParameter("startPnum") ); 
		 }
		commandMap.put("pageNum", startPnum);
		commandMap.put("pageRow", 5);
		
		Map<String,Object> total_cnt = sampleService.selectListCnt(commandMap);
	    List<Map<String,Object>> list = sampleService.selectBoardList(commandMap);
	    
	    
	    // map 초기화
		commandMap.clear();
	    
	    mv.addObject("total_cnt", total_cnt);
	    mv.addObject("list", list);
	     
	    return mv;
	 }
	 
}
