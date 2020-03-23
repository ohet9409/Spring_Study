package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// ����¡ ó���� ���� Ŭ���� 
@Getter
@Setter
@ToString
public class Criteria {

	private int pageNum;
	private int amount;
	
	public Criteria() {
		// �⺻���� 1������, 10���� �����ؼ� ó��
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	
}
