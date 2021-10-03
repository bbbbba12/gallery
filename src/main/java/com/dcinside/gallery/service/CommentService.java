package com.dcinside.gallery.service;

import com.dcinside.gallery.domain.Comment;
import com.dcinside.gallery.domain.dto.CommentDto;

import java.util.List;

public interface CommentService {
    /**
     * 1. 댓글 생성
     * 2. 댓글 수정
     * 3. 댓글 삭제
     * 4. 대댓글
     * ++ 모두 임시 아이디, 비번이 필요함.
     * 
     * 5. 조회
     */
    void comment(String minorId, Long postNumber, CommentDto commentDto);
    void update(Long commentId, CommentDto commentDto);
    void delete(Long commentId, String password);
    void nestedComment(Long commentId, CommentDto commentDto);
}
