package kr.multi.erp.dept;

import java.util.List;

public interface DeptService {
	int insert(DeptDTO dept);
	List<DeptDTO> getDeptName();
	//전체사원목을 조회하는 메소드
	public List<DeptDTO> select();
	public int delete(String deptno);
	public DeptDTO read(String deptno);
	int update(DeptDTO dept);
}
