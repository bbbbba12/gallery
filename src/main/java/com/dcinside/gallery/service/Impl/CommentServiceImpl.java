package com.dcinside.gallery.service.Impl;

import com.dcinside.gallery.domain.Comment;
import com.dcinside.gallery.domain.Post;
import com.dcinside.gallery.domain.dto.CommentDto;
import com.dcinside.gallery.repository.CommentRepository;
import com.dcinside.gallery.repository.PostRepository;
import com.dcinside.gallery.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public void comment(String minorId, Long postNumber, CommentDto commentDto) {
        Post post = postRepository.findByMinorIdAndPostNumber(minorId, postNumber);
        Comment comment = Comment.builder()
                // .author(commentDto.getAuthor())
                .content(commentDto.getContent())
//                .password(commentDto.getPassword())
                .build();
        comment.addPost(post);
    }

    @Override
    public void update(Long commentId, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()) throw new IllegalArgumentException("?");

        Comment comment = optionalComment.get();

        comment.update(commentDto);
    }

    @Override
    public void delete(Long commentId, String password) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()) throw new IllegalArgumentException("?");

        Comment comment = optionalComment.get();
        if(!comment.matches(password)) throw new IllegalArgumentException("password 가 잘못됨");
        commentRepository.delete(comment);
    }

    @Override
    public void nestedComment(Long commentId, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()) throw new IllegalArgumentException("?");

        Comment parentComment = optionalComment.get();
        Comment childComment = Comment.builder()
                // .author(commentDto.getAuthor())
                .content(commentDto.getContent())
//                .password(commentDto.getPassword())
                .build();
        childComment.nestCommentOn(parentComment);
    }
}
