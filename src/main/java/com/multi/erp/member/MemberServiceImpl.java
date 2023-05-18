package com.multi.erp.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MemberServiceImpl implements MemberService {

	MemberDAO dao;
	
	public MemberServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public MemberServiceImpl(MemberDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<MemberDTO> getTreeEmpList(String deptno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(MemberDTO user) {
		return dao.insert(user);
	}
	
//	@Override
//	public int insert(MemberDTO user, MultipartFile file, String realpath, String filename) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public boolean idCheck(String id) {
		return dao.idCheck(id);
	}

	@Override
	public List<MemberDTO> getMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberDTO read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberDTO> search(String column, String search, String pass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(MemberDTO user) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// Controller -> Service -> DAO
	// Controller <- Service <- DAO
	@Override
	public MemberDTO login(MemberDTO loginUser) {
		MemberDTO user = dao.login(loginUser);
		System.out.println("service : " + user);
		// DB에서 가져온 값에서 menupath 를 가공해서 view 의 이름을 menupath 에 세팅
		if(user!=null) {
			String menupath = user.getMenupath();
			menupath = menupath.substring(menupath.indexOf("/")+1, menupath.indexOf("_"));
			user.setMenupath(menupath); // 잘라낸 분자열이 viewname 이므로 다시 menupath에 세팅하는 작업
		}
		System.out.println("서비스=++++++++++++++++"+user);
		return user;
	}

}
