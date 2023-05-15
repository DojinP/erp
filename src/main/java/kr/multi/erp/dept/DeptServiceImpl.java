package kr.multi.erp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	DeptDAO dao;
	
	@Override
	public int insert(DeptDTO dept) {
		dao.insert(dept);
		return 0;
	}

	@Override
	public List<DeptDTO> getDeptName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeptDTO> select() {
		List<DeptDTO> dept = dao.select();
		return dept;
	}

	@Override
	public int delete(String deptno) {
		return dao.delete(deptno);
	}

	@Override
	public DeptDTO read(String deptno) {
		return dao.read(deptno);
	}

	@Override
	public int update(DeptDTO dept) {
		
		return dao.update(dept);
	}

}
