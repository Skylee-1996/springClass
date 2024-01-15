package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO {
	
	private int pageNo; //현재 페이지
	private int qty; //한페이지당 표시될 리스트 개수
	
	private String type;   //검색에 사용될 멤버변수 추가..
	private String keyword;
	
	public PagingVO() {
		this.pageNo = 1;
		this.qty = 10;
		
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	//시작번지 구하기
	public int getPageStart() {
		return (this.pageNo-1)*qty;
	}
	
	//여러가지의 타입을 같이 검색하기 위해서 타입을 배열로 구분
	public String[] getTypeToArray() { 
		return this.type == null ? new String[]{} :this.type.split(""); 
	}
	
}
