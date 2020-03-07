package org.zerock.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
// SampleDTO를 여러개 전달받아서 처리하고 싶을때 사용
public class SampleDTOList {
	private List<SampleDTO> list;
	
	public SampleDTOList(){
		list = new ArrayList<SampleDTO>();
	}
}
