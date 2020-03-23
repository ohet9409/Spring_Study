package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
// Test for Controller
@WebAppConfiguration

//@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@ContextConfiguration(classes= {org.zerock.config.RootConfig.class, org.zerock.config.ServletConfig.class})
@Log4j
public class BoardControllerTests {

	@Setter(onMethod_= @Autowired)
	private WebApplicationContext ctx;
	
	// ��¥MVC ����: ��¥�� url�� �Ķ���� ���� ���������� ����ϴ� ��ó�� ���� Controller�� ����
	private MockMvc mockMvc;
	
	// ��� �׽�Ʈ ���� �Ź� ����Ǵ� �޼���
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	//@Test
	public void testList() throws Exception{
		// get ����� ȣ��
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap());
	}
	
	//@Test
	public void testRegister() throws Exception {
		
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
				.param("title", "�׽�Ʈ ���� ���� 200319")
				.param("content", "�׽�Ʈ ���� ���� 200319")
				.param("writer", "user00")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	//@Test
	public void testGet() throws Exception{
		
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/board/get")
				.param("bno", "23822606")
				).andReturn()
				.getModelAndView().getModelMap());
	}
	
	//@Test
	public void testModify() throws Exception{
		
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/modify")
						.param("bno", "23822606")
						.param("title", "������ �׽�Ʈ ���� ���� 200319")
						.param("content", "������ �׽�Ʈ ���� ���� 200319")
						.param("writer", "user00"))
				.andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
	
	@Test
	public void testRemove() throws Exception{
		//������ �����ͺ��̽��� �Խù� ��ȣ Ȯ���� ��
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "23822606")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}
}