package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// ����¡ ó���� ���� Ŭ���� 
@Getter
@Setter
@ToString
public class Criteria {

	// ����¡ ó���� ���� ����
	private int pageNum;
	private int amount;
	
	// �˻��� ���� ����
	private String type;
	private String keyword;
	
	public Criteria() {
		// �⺻���� 1������, 10���� �����ؼ� ó��
		this(1,10);
	}

	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// �˻� ������ �� ����(T, W, C)�� �����Ǿ� �����Ƿ� �˻� ������ �迭�� ���� �� ���� ó���ϱ� ����
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	public String getListLink() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.amount)
				.queryParam("type", this.type)
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
}
