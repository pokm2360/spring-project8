package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;
	
	@Test
	public void 회원등록() {
//		Member member = new Member("aaa", "1234", "둘리");
		Member member = Member.builder()
								.id("aaa")
								.name("둘리")
								.password("1234")
								.build();
		repository.save(member);
	}
	
	@Test
	public void 회원목록조회() {
		List<Member> list = repository.findAll();
		
		for (Member member : list)
			System.out.println(member);
	}
	
	@Test
	public void 회원단건조회() {
		Optional<Member> result = repository.findById("aaa");
//		Optional은 박스. 안에 Member라는 엔티티가 들어있음
//		get 메소드를 통해 꺼내기 전 내용이 있는지 없는지 확인
		if(result.isPresent()) {
			Member member = result.get();
			System.out.println(member);
		}
	}
	
	@Test
	public void 회원수정() {
		Optional<Member> result = repository.findById("aaa");
		Member member = result.get();
		member.setId("bbb");
		member.setPassword("1111");
		
		repository.save(member);
		System.out.println(member);
	}
	
	@Test
	public void 회원삭제() {
		repository.deleteById("aaa");
	}
	
	@Test
	public void 회원삭제2() {
		repository.deleteAll();
	}
}
