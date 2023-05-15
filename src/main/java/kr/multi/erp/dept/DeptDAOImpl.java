package kr.multi.erp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDAOImpl implements DeptDAO {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<DeptDTO> getDeptName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(DeptDTO dept) {
		String sql = "insert into dept values (?,?,?,null,?,?,?,?,?,?,?)";
		return template.update(sql, dept.getDeptno(), 
									dept.getDeptname(), 
									dept.getDeptStartDay(), 
									dept.getDeptlevel(), 
									dept.getDeptstep(),
									dept.getDeptuppercode(), 
									dept.getJob_category(), 
									dept.getMgr_id(), 
									dept.getDeptaddr(), 
									dept.getDepttel());
	}

	@Override
	public List<DeptDTO> select() {
		// jdbc 작업할 때 작성했던 모든 코드가 query 메소드 내부에서 자동으로 처리
		// 매번 달라지는 sql 문과 조회한 레코드를 어떤 DTO에 매핑할 것인지 정보를 담고있는 RowMapper 객체만 넘겨주면
		// 자동으로 모든 레코드를 List로 만들어서 리턴
		String sql = "select * from dept";
		return template.query(sql, new DeptRowMapper());
	}

	@Override
	public int delete(String deptno) {
		return template.update("delete from dept where deptno=?", deptno);
	}

	@Override
	public DeptDTO read(String deptno) {
		String sql = "select * from dept where deptno = ?";
		return template.queryForObject(sql, new Object[] {deptno}, new DeptRowMapper());
	}

	@Override
	public int update(DeptDTO dept) {
		String sql = "update dept set mgr_id=?, deptaddr=?, depttel=? where deptno=?";
		return template.update(sql, dept.getMgr_id(), dept.getDeptaddr(), dept.getDepttel(), dept.getDeptno());
	}

}
