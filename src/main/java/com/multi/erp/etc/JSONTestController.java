package com.multi.erp.etc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.erp.board.BoardDTO;
import com.multi.erp.board.BoardService;

@RestController // controller와 responsebody가 합쳐진
/*@Controller
@ResponseBody*/	// 컨트롤러 내부에서 정의되는 메소드가 모두 JSON이나 String 데이터를 리턴하는 컨트롤러라면 선언부에 한 번만 정의해서 사용할 수 있다.
@RequestMapping("/json")
public class JSONTestController {
	BoardService service;
	
	@Autowired
	public JSONTestController(BoardService service) {
		super();
		this.service = service;
	}
	// 컨트롤러 상단에 @ResponseBody를 선언했으므로 이 메소드 위에 @ResponseBody가 정의되어있는 것과 동일
	// @ResponseBody는 이제 더이상 뷰를 응답하지 않고 ResponseBody에 스트링을 추가해서 reponse하겠다는 의미
//	@RequestMapping(value = "/getString", produces = "text/plane;charset=utf-8")
//	@RequestMapping(value = "/getString", produces = "application/text;charset=utf-8") -> 이거 다운로드창 뜨는데 왜 그런것?
	@RequestMapping("/getString")
	public String responseString() {
		return "text데이터";
	}
	
	@RequestMapping("/getJsonObj")
	public BoardDTO responseObj() {
		// return을 DTO로 명시하면 jackson-databind 라이브러리가 자동으로 json object로 변환해서 리턴한다
		return service.getBoardInfo("32");
	}
	
//	@RequestMapping("/getJsonArr")
	@RequestMapping(value = "/getJsonArr", produces = "application/json;charset=utf-8")
	public List<BoardDTO> responseJsonArr() {
		// return을 List로 명시하면 jackson-databind 라이브러리가 자동으로 json Array로 변환해서 리턴한다
		return service.boardList();
	}
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/json")
public class JSONTestController {
	
}
