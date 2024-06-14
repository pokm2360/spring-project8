package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberServicelmpl implements MemberService{

	@Autowired
	MemberRepository repository;
	
	@Override
	public Page<MemberDTO> getList(int pageNumber) {
		
		int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;
		
		Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("regDate").descending());
		
		Page<Member> entityPage = repository.findAll(pageable);
		
		Page<MemberDTO> dtoPage = entityPage
									.map( entity -> entityToDto(entity) );
		return dtoPage;
	}

	
}
