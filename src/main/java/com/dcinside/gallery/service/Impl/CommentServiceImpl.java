package com.dcinside.gallery.service.Impl;

import com.dcinside.gallery.domain.Comment;
import com.dcinside.gallery.domain.dto.CommentDto;
import com.dcinside.gallery.repository.CommentRepository;
import com.dcinside.gallery.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void comment(String minorId, Long postNumber, CommentDto commentDto) {

    }

    @Override
    public void update(Long commentId, CommentDto commentDto) {

    }

    @Override
    public void delete(Long commentId) {

    }

    @Override
    public void nestedComment(Long commentId, CommentDto commentDto) {

    }

    @Override
    public Comment viewComment(String minorId, Long postNumber) {
        return null;
    }
}
