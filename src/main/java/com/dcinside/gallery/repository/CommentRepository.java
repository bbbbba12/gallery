package com.dcinside.gallery.repository;

import com.dcinside.gallery.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
