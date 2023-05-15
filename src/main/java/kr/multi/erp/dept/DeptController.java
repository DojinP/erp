package kr.multi.erp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dept")
public class DeptController {
	@Autowired
	DeptService service;
	
	// insert 를 하기위해 view를 볼 수 있는 메소드
	@RequestMapping("/register")
	public String showPage() {
		return "dept/dept_register";
	}
	
	@RequestMapping("/insert.do")
	public String insert(DeptDTO dept) {
		service.insert(dept);
		return "index";
	}
	
	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView("dept/list");
		List<DeptDTO> deptlist = service.select();
		mav.addObject("deptlist", deptlist);
		return mav;
	}
	
	@RequestMapping("/read.do")
	public ModelAndView read(String deptno, String state) {
		ModelAndView mav = new ModelAndView();
		DeptDTO dept = service.read(deptno);
		mav.addObject("dept", dept);
		String view = "";
		if(state.equals("READ")) {
			view = "dept/dept_read_jstl";
		}else {
			view = "dept/dept_update";
		}
		mav.setViewName(view);
		return mav;
	}
	
	@RequestMapping("/delete.do")
	public String delete(String deptno) {
		service.delete(deptno);
		//return "/dept/list.do";		 // 기본이 forward
		return "redirect:/dept/list.do"; // redirect 방법 
	}
	
	@RequestMapping("/update.do")
	public String update(DeptDTO dept) {
		System.out.println(dept);
		service.update(dept);
		return "redirect:/dept/list.do"; 
	}
}
