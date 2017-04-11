package com.whenguycan.kawori.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author whenguycan
 * @date 2017年4月11日 上午10:00:33
 */
public class Page<T> {

	private int pageNo = 1;
	private int pageSize = 15;
	private int totalCount = 0;
	private List<T> result = new ArrayList<T>();
	private String pager = "";
	
	public Page(){
		
	}
	
	public Page(int pageNo, int pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public Page(int pageNo){
		this.pageNo = pageNo;
	}
	
	public void generatePager(){
		String html = "";
		html += "<li><a href='#' onclick='go(0)'>Previous</a></li>";
		html += "<li><a href='#' onclick='go(1)'>1</a></li>";
		html += "<li><a href='#' onclick='go(2)'>Next</a></li>";
		this.pager = html;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	public String getPager() {
		return pager;
	}
	
}
