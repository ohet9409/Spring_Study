package com.spring.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

// ���������� �����ؾ� �ϴ� ������� ǥ��
@Component
//lombok�� setter�� �����ϴ� ��ɰ� ������, toString()�� �ڵ����� �����ϴ� ������̼�
@Data
public class Restaurant {
	
	@Setter(onMethod_= @Autowired)
	private Chef chef;
}
