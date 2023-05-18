package com.multi.erp.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.multi.erp.board.FileUploadLogicService;

import kr.multi.erp.dept.DeptDTO;
import kr.multi.erp.dept.DeptService;
//@SessionAttributes("user") user 는 세션에 저장하는 어트리뷰트 명
//컨트롤러에서 user라는 이름으로 Model 객체에 저장된 어트리뷰트가 있으면 이를 세션에도 저장해준다.
@Controller
@RequestMapping("/emp")
@SessionAttributes("user")	// 파라미터 : 데이터 공유명
public class MemberController {
	
	MemberService m_service;
	DeptService d_service;
	FileUploadLogicService fileuploadservice;
	
	public MemberController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public MemberController(MemberService m_service, DeptService d_service, FileUploadLogicService fileuploadservice) {
		super();
		this.m_service = m_service;
		this.d_service = d_service;
		this.fileuploadservice = fileuploadservice;
	}
	
	@RequestMapping("/insert")
	public String insertPage(Model model) {
		// DeptService 등록
		List<DeptDTO> deptlist = d_service.select();
		model.addAttribute("deptlist", deptlist);
		return "member/insertPage";
	}
	
	@PostMapping("/insert.do")
	public String insert(MemberDTO member, HttpSession session) throws IllegalStateException, IOException {
		MultipartFile userImage = member.getUserImage();
		// ServletContext 객체는 프로젝트에 대한 정보를 담은 객체
		String path = WebUtils.getRealPath(session.getServletContext(), "/WEB-INF/upload");
		System.out.println("수정전"+member);
		// 파일 저장 경로에 파일을 저장
		fileuploadservice.uploadFile(userImage, path);
		if(member.getMarry()==null) {
			member.setMarry("0");
		}
		member.setGender(member.getSsn().substring(6,7)); // 7번째 위치의 값을 추출
		System.out.println("수정후"+member);
		// MemberDTO 테이블에 신규 user 레코드 저장
		m_service.insert(member);
		// m_service.insert(member, userImage, path, userImage.getOriginalFilename());
		
		return "redirect:/emp/mypage";
	}
	
	@GetMapping("/login.do")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login.do")
	public ModelAndView login(MemberDTO loginUserInfo, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String view = "";
		MemberDTO user = m_service.login(loginUserInfo);
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
			// 사용하던 세션을 메모리에서 제거
			session.invalidate();
		}
		return view;
	}
	
	// 스프링에서 제공되는 기능을 이용한 로그인
	@RequestMapping("/spring/login")
	public String springlogin(MemberDTO loginUserInfo, Model model) {
		System.out.println("스프링이 제공하는 @SessionAttributes를 이용해서 세션처리하기");
		MemberDTO user = m_service.login(loginUserInfo);
		String view = "";
		if(user!=null) {
			model.addAttribute("user", user);
			view = user.getMenupath();
		}else {
			view = "redirect:/emp/login.do";
		}
		return view;
	}

	// 스프링에서 제공되는 기능을 이용한 로그아웃
	@RequestMapping("/spring/logout")
	public String springlogout(SessionStatus status) {
		System.out.println("SessionStatus를 이용한 로그아웃 처리하기");
		status.setComplete(); // 세션에 있는 객체를 제거
		return "redirect:/index.do";
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
	
	@PostMapping(value = "/idcheck", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String idCheck(String id) {
		String msg = "";
		boolean check = m_service.idCheck(id);
		if(!check) {
			msg = "사용가능아이디";
		}else {
			msg = "사용불가능아이디";
		}
		return msg;
	}
	
	@RequestMapping("/dept/tree.do")
	public String showTree(Model model) {
		List<DeptDTO> deptlist = d_service.select();
		model.addAttribute("deptlist", deptlist);
		return "dept/tree";
	}
}
