package com.multi.erp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.erp.board.BoardDTO;
import com.multi.erp.board.BoardService;

@Controller
public class IndexController {
	BoardService service;
	
	@Autowired
	public IndexController(BoardService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/index.do")
	public String index(Model model) {
		// 첫 페이지가 로딩될 때 필요한 데이터를 가져오기 위해서 서비스 연결 (게시판 데이터)
		/* List<BoardDTO> boardlist = service.boardList(); */
		List<BoardDTO> boardlist = service.findByCategory("게시판");
		
		model.addAttribute("boardlist", boardlist);
		return "index";
	}
	
	@RequestMapping("/menu/board.do")
	public String menu_board() {
		return "menu/board";
	}
}
