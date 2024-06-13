package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository repository;

    @Override
    public int register(BoardDTO dto) {

        System.out.println(dto);

        Board entity = dtoToEntity(dto);
        repository.save(entity);
        int newNo = entity.getNo();

        System.out.println(entity);

        return newNo;
    }

    @Override
    public BoardDTO read(int no) {

        Optional<Board> result = repository.findById(no);

        if (result.isPresent()) {
            Board board = result.get();
            BoardDTO boardDTO = entityToDto(board);
            return boardDTO;
        } else {
            return null;
        }

    }

    @Override
    public void modify(BoardDTO dto) {
        Optional<Board> result = repository.findById(dto.getNo()); 
        if (result.isPresent()) {
            Board entity = result.get();

            entity.setTitle(dto.getTitle());
            entity.setContent(dto.getContent());

            repository.save(entity);
        }

    }

    @Override
    public int remove(int no) {

        Optional<Board> result = repository.findById(no);

        if (result.isPresent()) {
            repository.deleteById(no);
            return 1; 
        } else {
            return 0;
        }

    }

	@Override
	public Page<BoardDTO> getList(int pageNumber) {
		
		int pageNum = (pageNumber == 0) ? 0 : pageNumber - 1;
		
		Sort sort = Sort.by("no").descending();
		
		Pageable pageable = PageRequest.of(pageNum, 10, sort);
		
		Page<Board> entityPage = repository.findAll(pageable);
		
		Page<BoardDTO> dtoPage = entityPage
									.map( entity -> entityToDto(entity) );
		return dtoPage;
	}

}
