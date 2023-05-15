package com.multi.erp.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/emp")
public class MemberController {
	@Autowired
	MemberService service;
	
	public MemberController() {
		// TODO Auto-generated constructor stub
	}
	
	public MemberController(MemberService service) {
		super();
		this.service = service;
	}

	@GetMapping("/login.do")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login.do")
	public ModelAndView login(MemberDTO loginUserInfo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String view = "";
		MemberDTO user = service.login(loginUserInfo);
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			view = user.getMenupath();
		}else {
			view = "redirect:/emp/login.do";
		}
		
		mav.setViewName(view);
		return mav;
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		String view = "redirect:/emp/login.do";
		if(session != null) {
			session.invalidate();
		}
		return view;
	}
	
	@RequestMapping("/mypage")
	public String mypage(HttpSession session) {
		// 추후에 가장 복잡한 컨트롤러가 될 수 있다
		// 결재에 대한 진행상황
		// 스케줄표 - 업무스케줄, 미팅, 휴가일정
		// 진행중인 메인업무에 대한 내용
		MemberDTO user = (MemberDTO)session.getAttribute("user");
		return user.getMenupath();
	}
}
