package com.b2b.dto;

public class BoardDTO {

	private String B_NO;
	private String B_TITLE;
	private String B_NAME;
	private String B_VEIWCNT;
	private String B_DATE;
	// 페이지 시작번호
	private int startPnum = 1;
	// 페이지 끝 번호
	private int endPnum;
	// 목록 수
	static int rowNum = 5;
	// 리스트 총 개수
	private int listCnt;
	
	public String getB_NO() {
		return B_NO;
	}
	public void setB_NO(String b_NO) {
		B_NO = b_NO;
	}
	public String getB_TITLE() {
		return B_TITLE;
	}
	public void setB_TITLE(String b_TITLE) {
		B_TITLE = b_TITLE;
	}
	public String getB_NAME() {
		return B_NAME;
	}
	public void setB_NAME(String b_NAME) {
		B_NAME = b_NAME;
	}
	public String getB_VEIWCNT() {
		return B_VEIWCNT;
	}
	public void setB_VEIWCNT(String b_VEIWCNT) {
		B_VEIWCNT = b_VEIWCNT;
	}
	public String getB_DATE() {
		return B_DATE;
	}
	public void setB_DATE(String b_DATE) {
		B_DATE = b_DATE;
	}
	public int getStartPnum() {
		return startPnum;
	}
	public int getEndPnum() {
		return endPnum;
	}
	public void setEndPnum(int endPnum) {
		this.endPnum = endPnum;
	}
	public int getRowNum() {
		return rowNum;
	}
	public int getListCnt() {
		return listCnt;
	}
	public void setListCnt(int listCnt) {
		this.listCnt = listCnt;
	}
}
