package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Board;


@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository repository;
	
	@Test
	public void 게시물30개추가() {
		
		for(int i=1; i<=30; i++) {
			
			Board board = Board.builder()
							.title(i+"번글")
							.content("안녕하세요")
							.writer("둘리")
							.build();
			
			repository.save(board);
		}
	}
	
	@Test
	public void 페이지테스트() {
		
//		no 필드값을 기준으로 역정렬
		Sort sort = Sort.by("no").ascending();
		
//		of메소드: PageRequest의 객체를 생성하는 함수
		Pageable pageable = PageRequest.of(0, 5, sort); //페이지번호, 데이터개수, 정렬
		
		Page<Board> result = repository.findAll(pageable);
		
		List<Board> list = result.getContent();
		
		System.out.println(list);
	}
}























//	@Test
//	void 게시물등록() {
//
//		//시간은 입력할 필요없음
//		Board board = Board.builder()
//						.title("1번글").content("내용입니다").writer("둘리")
//						.build();
//		repository.save(board);
//	}
//
//	@Test
//	void 게시물목록조회() {
//		List<Board> list = repository.findAll();
//		for(Board board : list) {
//			System.out.println(board);
//		}
//	}
//	
//	@Test
//	void 게시물단건조회() {
//		Optional<Board> result = repository.findById(1);
//		if(result.isPresent()) {
//			Board board = result.get();
//			System.out.println(board);
//		}
//	}
//
//	@Test
//	void 게시물수정() {
//		Optional<Board> result = repository.findById(1);
//		Board board = result.get();
//		board.setContent("내용이수정되었습니다~");
//		repository.save(board);
//	}
//	
//	@Test
//	void 게시물삭제() {
//		repository.deleteById(1);
//	}


