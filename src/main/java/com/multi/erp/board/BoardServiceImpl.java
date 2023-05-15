package com.multi.erp.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	BoardDAO dao;
	
	public BoardServiceImpl() {

	}
	
	@Autowired
	public BoardServiceImpl(BoardDAO dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	public int insert(BoardDTO board) {
		return dao.insert(board);
	}
	
	@Override
	public List<BoardDTO> boardList() {
		return dao.boardList();
	}

	@Override
	public BoardDTO getBoardInfo(String board_no) {
		return dao.read(board_no);
	}

	@Override
	public int update(BoardDTO board) {
		return dao.update(board);
	}

	@Override
	public int delete(String board_no) {
		return dao.delete(board_no);
	}

	@Override
	public List<BoardDTO> search(String data) {
		return dao.search(data);
	}

	@Override
	public List<BoardDTO> search(String tag, String data) {
		return dao.search(tag, data);
	}

	@Override
	public List<BoardDTO> findByCategory(String category) {
		List<BoardDTO> list = null;
		if(category!=null) {
			if(category.equals("all")) {
				list = dao.boardList();
			}else {
				list = dao.findByCategory(category);
			}
		}
		
		return list;
	}

	// 게시글 등록 버튼을 눌렀을 때 실행될 메소드
	// 두 개의 작업을 처리
	// 1. 일반적인 게시글의 Text 데이터를 저장하는 (BoardDTO to tbboard) 메소드
	// 2. 파일을 저장하는 (BoardFileDTO to board_file) 메소드
	// DAO에 정의된 메소드 2개를 호출
	// 서비스 메소드에서 호출되는 2개의 메소드가 모두 정상처리 되어야 DB에 반영될 수 있도록 처리해야 한다.
	//  - 만약 두 작업 중에 하나의 작업만 처리가 되고 오류가 발생되면 모든 작업이 취소되도록 처리해야 한다.
	//    => 트랜잭션 처리 
	//  - 논리적인 작업(작업 한 개)
	@Override
	public int insert(BoardDTO board, List<BoardFileDTO> boardfiledtolist) {
		dao.insert(board);
		dao.insertFile(boardfiledtolist);
		return 0;
	}

	@Override
	public List<BoardFileDTO> getFileList(String boardno) {
		return dao.getFileList(boardno);
	}

	@Override
	public BoardFileDTO getFile(BoardFileDTO inputdata) {
		return dao.getFile(inputdata);
	}
	
	
	
}
