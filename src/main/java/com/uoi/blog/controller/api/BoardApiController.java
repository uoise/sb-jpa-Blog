package com.uoi.blog.controller.api;

import com.uoi.blog.config.auth.PrincipalDetail;
import com.uoi.blog.dto.ResponseDto;
import com.uoi.blog.model.Board;
import com.uoi.blog.model.Reply;
import com.uoi.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
public class BoardApiController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
        boardService.글수정하기(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/api/board/{id}/reply")
    public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal){

        boardService.댓글쓰기(principal.getUser(), boardId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
