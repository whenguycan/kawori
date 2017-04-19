package com.whenguycan.kawori.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author whenguycan
 * @date 2017年4月11日 上午10:00:33
 */
public class Page<T> {

	private int displayLength = 6;
	private int pageNo = 1;
	private int pageSize = 15;
	private int totalCount = 0;
	private List<T> result = new ArrayList<T>();
	private String pagination = "";
	private Order order;
	
	class Order{
		private String order;
		private String field;
		public String order(){
			return this.order;
		}
		public String field(){
			return this.field;
		}
	}
	
	public Page<T> asc(String orderField){
		if(StringUtils.isNotBlank(orderField)){
			Order o = new Order();
			o.order = "ASC";
			o.field = orderField;
			this.order = o;
		}
		return this;
	}
	
	public Page<T> desc(String order, String orderField){
		if(StringUtils.isNotBlank(orderField)){
			Order o = new Order();
			o.order = "DESC";
			o.field = orderField;
			this.order = o;
		}
		return this;
	}
	
	public Page(){
		
	}
	
	public Page(int pageNo, int pageSize){
		if(pageNo != 0){
			this.pageNo = pageNo;
		}
		if(pageSize != 0){
			this.pageSize = pageSize;
		}
	}
	
	public Page(int pageNo){
		if(pageNo != 0){
			this.pageNo = pageNo;
		}
	}
	
	public void generatePager(){
		String html = "";
		if(totalCount == 0){
			html += "<li><a href='#' onclick='go(1)'>Previous</a></li>";
			html += "<li><a href='#' onclick='go(1)'>1</a></li>";
			html += "<li><a href='#' onclick='go(1)'>Next</a></li>";
		}else{
			int pageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
			int top = 1;
			int bottom = pageCount;
			int pre = pageNo-1>0?pageNo-1:1;
			int next = pageNo+1>pageCount?pageCount:pageNo+1;
			int start = 0;
			int end = 0;
			if(pageCount > displayLength){
				if(pageNo - displayLength/2 < 1){
					start = 1;
					end = start + displayLength - 1;
				}else if(pageNo + displayLength/2 > pageCount){
					end = pageCount;
					start = end - displayLength + 1;
				}else{
					start = pageNo - displayLength/2;
					end = pageNo + displayLength/2;
					if(displayLength%2==0){
						start++;
					}
				}
			}else{
				start = 1;
				end = pageCount;
			}
			html += "<li><a href='#' onclick='go("+top+")'>&lt;&lt;</a></li>";
			html += "<li><a href='#' onclick='go("+pre+")'>&lt;</a></li>";
			for(int i=start;i<end+1;i++){
				html += "<li class="+(pageNo==i?"active":"")+"><a href='#' onclick='go("+i+")'>"+i+"</a></li>";
			}
			html += "<li><a href='#' onclick='go("+next+")'>&gt;</a></li>";
			html += "<li><a href='#' onclick='go("+bottom+")'>&gt;&gt;</a></li>";
		}
		this.pagination = html;
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
	public String getPagination() {
		this.generatePager();
		return pagination;
	}
	public Order getOrder() {
		return order;
	}
	
}
