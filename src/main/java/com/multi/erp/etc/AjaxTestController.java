package com.multi.erp.etc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.erp.board.BoardDTO;
import com.multi.erp.board.BoardService;

@Controller
@RequestMapping("/ajax")
public class AjaxTestController {
	BoardService service;
	
	@Autowired
	public AjaxTestController(BoardService service) {
		super();
		this.service = service;
	}
	
@Controller
@RequestMapping("/ajax")
public class AjaxTestController {
	@RequestMapping("/noajax")
	public String noajax(String id, Model model) {
		String msg = "";
		if(id.equals("jang")) {
			msg = "사용불가아이디"; // 기존에 DB에 저장되어 있는 ID
		}else {
			msg = "사용가능아이디";
		}
		model.addAttribute("msg",msg);
		return "etcview/ajax"; // forward 방식
	}
	
	// Ajax 요청으로 실행되는 컨트롤러로 String 결과를 클라이언트로 전송
	// response는 기존의 방식처럼 view를 response 하는 것이 아니므로 response 할 데이터의 형식을 리턴타입에 정의
	// view 를 리턴하지 않고 명시된 타입으로 리턴하겠다는 의미
	// produces 속성에 response 되는 데이터의 형식 (MIME TYPE) 과 인코딩을 명시
	@RequestMapping(value = "/ajaxtest01", produces = "application/text;charset=utf-8")
	@ResponseBody 	// String이 response body라고 명시해야 view 로 인식하지 않는다. (반드시 설정!)
					// 리턴 타입 앞에 명시해도 됨 
	public String ajaxtest(String id) {
		String msg = "";
		if(id.equals("jang")) {
			msg = "사용불가아이디"; // 기존에 DB에 저장되어 있는 ID
		}else {
			msg = "사용가능아이디";
		}
		return msg;
	}
	
	@RequestMapping(value = "/gugu", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String getgugu(String dan) {
		System.out.println("dan:"+dan);
		String print = "";
		
		for(int i=1; i<=9; i++) {
			print += dan + " * " + i + " = " + (Integer.parseInt(dan) * i) + "<br/>";
		}
		
		System.out.println(print);
		
		return print;
	}
	
	@ResponseBody
	@RequestMapping(value="/exam01", produces = "application/json;charset=utf-8")	// view에서 받는거라면 필요 (MIME Type 설정-text인지 json인지)
	public BoardDTO responseObj(String boardno) {
		// return을 DTO로 명시하면 jackson-databind 라이브러리가 자동으로 json object로 변환해서 리턴한다
		return service.getBoardInfo(boardno);
	}
	
	@ResponseBody
	@RequestMapping(value = "/exam02/getjsondata", produces = "application/json;charset=utf-8")
	public List<BoardDTO> responseJsonArr() {
		// return을 List로 명시하면 jackson-databind 라이브러리가 자동으로 json Array로 변환해서 리턴한다
		return service.boardList();
	}
}
