package com.uoi.blog.service;

import com.uoi.blog.model.Board;
import com.uoi.blog.model.User;
import com.uoi.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user) { // title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
}
