package com.dcinside.gallery.repository;

import com.dcinside.gallery.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByMinorGalleryIdAndId(String minorId, Long Id);
    Post findByMinorGalleryIdAndPostNumber(String minorId, Long postNumber);

    void deleteByMinorGalleryIdAndPostNumber(String minorId, Long postNumber);

    /**
     * minor post 찾기 + 최신순으로 조회(postNumber 가 큰 순으로)
     */
    Page<Post> findByMinorGalleryId(String minorId, Pageable pageable);

    /**
     * 개념글 찾기:
     * 1. 추천이 10이상인 글
     * 2. 최신순으로 조회(postNumber 가 큰 순으로)
     */
    @Query("select p from Post p where p.likes >= 10")
    Page<Post> findFamousPost(String minorId, Pageable pageable);
}
