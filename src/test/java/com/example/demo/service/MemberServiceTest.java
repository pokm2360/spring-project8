package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.dto.MemberDTO;

@SpringBootTest
public class MemberServiceTest {
	
	@Autowired
	MemberService service;
	
	@Test
	public void 회원목록조회() {
		
//		사용할 객체 - 필요한 메소드 고르기 - 들어가서 타입 가져오기 - 변수명 설정
		Page<MemberDTO> page = service.getList(1);
		
		List<MemberDTO> list = page.getContent();
		
		for(MemberDTO dto : list) {
			System.out.println(dto);
		}
	}
	
	@Test
	public void 회원등록() {
		
		MemberDTO dto = MemberDTO.builder()
								.id("user33")
								.name("둘리")
								.password("1234")
								.build();
		boolean isSuccess = service.register(dto);	
		
		if (isSuccess) {
			System.out.println("사용 가능한 아이디입니다.");
		} else {
			System.out.println("사용 중인 아이디입니다.");
		}
	}
}
