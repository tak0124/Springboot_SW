package com.tukorea.faq.dto;

public class FaqListDto {
	private String searchCategory;
	private String searchType;
	private String SearchKeyword;
	public String getSearchKeyword() {
		return SearchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		SearchKeyword = searchKeyword;
	}

	private int pageNum;
	
	public FaqListDto() {
		this.pageNum=1;
		this.searchType="question";
		
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	

}
