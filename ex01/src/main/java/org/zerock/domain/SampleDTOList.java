package org.zerock.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
// SampleDTO�� ������ ���޹޾Ƽ� ó���ϰ� ������ ���
public class SampleDTOList {
	private List<SampleDTO> list;
	
	public SampleDTOList(){
		list = new ArrayList<SampleDTO>();
	}
}
