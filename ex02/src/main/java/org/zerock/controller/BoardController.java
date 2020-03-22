package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
// /board�� �����ϴ� ��� ó���� BoardController�� �ϵ��� ����
@RequestMapping("/board/*")
// ������ ������ �ڵ� ���� ���
@AllArgsConstructor
public class BoardController {

	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model) {
		
		log.info("list");
		model.addAttribute("list", service.getList());
	}
	
	// �Է��������� �� �� �ֵ��� 
	@GetMapping("/register")
	public void register() {
		log.info("���ȭ�� �̵�");
	}
	
	// RedirectAttributes: ����۾��� ���� �� �ٽ� ��� ȭ������ �̵��ϱ� ����
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("register: " + board);
		
		service.register(board);
		
		// rttr.addFlashAttribute�� ������ ���� url�ڿ� ���� �ʴ´�. ��ȸ���̶� ���������� ��� �����Ͱ� �Ҹ��Ѵ�.
		// ���� 2���̻� �� ���, �����ʹ� �Ҹ��Ѵ�. ���� ���� �̿��Ͽ� �ѹ��� �������ؾ��Ѵ�
		rttr.addFlashAttribute("result", board.getBno());
		
		// ���� �ÿ��� 'redirect:' ���ξ ���  �̸� �̿��ϸ� ������ mvc�� ���������� response.sendRedirect()�� ó��
		return "redirect:/board/list";
	}
	
	// ����/����/��ȸ ������ ȭ�� �̵�
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		
		log.info("/get or /modify");
		model.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify: " + board);
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
			
		}
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		log.info("remove...." + bno);
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list";
	}
}
