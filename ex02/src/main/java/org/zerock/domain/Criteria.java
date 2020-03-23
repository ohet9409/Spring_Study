package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 페이징 처리를 위한 클래스 
@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum;
	private int amount;
	
	public Criteria() {
		// 기본값을 1페이지, 10개로 지정해서 처리
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
}
