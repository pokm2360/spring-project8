package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Board;
import com.example.demo.entity.Member;

import jakarta.transaction.Transactional;

// @Transactional: sql 작업 결과를 commit
@Transactional
public interface BoardRepository extends JpaRepository<Board, Integer> {
	
//	특정 회원이 작성한 모든 게시물을 삭제하는 메소드
//	@Modifying: 조회를 제외한 수정, 삭제 sql을 작성할 때 사용
	@Modifying
	@Query("delete from Board b where b.writer = :member") // jpql 문법
	public void deleteWriter(@Param("member") Member member);
}
