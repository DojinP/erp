package com.multi.erp.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO {

	// MyBatis 의 핵심 클래스를 이용해서 SQL 문을 실행
	SqlSession sqlSessionTemplate;
	public BoardDAOImpl() {
		
	}
	
	@Autowired
	public BoardDAOImpl(SqlSession sqlSessionTemplate) {
		super();
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
	@Override
	public int insert(BoardDTO board) {
		// SqlSession의 insert 메소드는 insert SQL 명령문을 실행하기 위해 제공되는 메소드
		// insert (statement, parameter 객체)
		// statement 가 mapper 에 정의한 SQL 문을 구분하는 id 명 = namespace명.id명
		// parameter 객체 - 사용자가 입력한 값이 저장된 DTO
		return sqlSessionTemplate.insert("com.multi.erp.board.insert", board);
	}

	@Override
	public List<BoardDTO> boardList() {
		return sqlSessionTemplate.selectList("com.multi.erp.board.selectall", sqlSessionTemplate);
	}

	@Override
	public BoardDTO read(String board_no) {
//		System.out.println(board_no);
		return sqlSessionTemplate.selectOne("com.multi.erp.board.read", board_no);
	}

	@Override
	public int update(BoardDTO board) {
		return sqlSessionTemplate.update("com.multi.erp.board.update", board);
	}

	@Override
	public int delete(String board_no) {
		return sqlSessionTemplate.delete("com.multi.erp.board.delete", board_no);
	}

	@Override
	public List<BoardDTO> search(String data) {
		return sqlSessionTemplate.selectList("com.multi.erp.board.search", data);
	}

	@Override
	public List<BoardDTO> search(String tag, String data) {
		List<BoardDTO> list = null;
		System.out.println("tag : " + tag + ", data : " + data);
		Map<String, String> map = new HashMap<String, String>();
		map.put("tag", tag);
		map.put("data", data);
		list = sqlSessionTemplate.selectList("com.multi.erp.board.dynamic_search", map);
		return list;
	}

	@Override
	public List<BoardDTO> findByCategory(String category) {
		return sqlSessionTemplate.selectList("com.multi.erp.board.categorySearch", category);
	}

	@Override
	public int insertFile(List<BoardFileDTO> boardfiledtolist) {
		return sqlSessionTemplate.insert("com.multi.erp.board.fileinsert", boardfiledtolist);
	}

	@Override
	public List<BoardFileDTO> getFileList(String boardno) {
		return sqlSessionTemplate.selectList("com.multi.erp.board.fileselect", boardno);
	}

	@Override
	public BoardFileDTO getFile(BoardFileDTO inputdata) {
		return sqlSessionTemplate.selectOne("com.multi.erp.board.getfileinfo", inputdata);
	}
	
	

}
