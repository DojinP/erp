package kr.multi.erp.dept;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*@Setter
@Getter
@ToString*/
// Setter, Getter 등에 직접 로직을 넣고 싶을 때는 따로 선언해주면 된다.
@Data
@AllArgsConstructor // 모든 멤버 매개변수로 하는 생성자 생성
@NoArgsConstructor	// 기본 생성자 생성
@RequiredArgsConstructor // 매개변수를 선택 @NonNull 으로 멤버 선택
public class DeptDTO {
	@NonNull
	private String deptno;
	@NonNull
	private String deptname;
	private String deptStartDay;
	private String deptEndDay;
	private String deptlevel;
	private String deptstep;
	private String deptuppercode;
	private String job_category;
	private String mgr_id;
	private String deptaddr;
	private String depttel;
	
}
