package com.dcinside.gallery.repository;

import com.dcinside.gallery.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p join fetch p.minorGallery m where m.minorId = :minorId and p.postNumber = :postNumber")
    Post findByMinorIdAndPostNumber(@Param("minorId") String minorId, @Param("postNumber") Long postNumber);

    @Modifying
    @Query("delete from Post p where p.minorGallery.minorId = :minorId and p.postNumber = :postNumber")
    void deleteByMinorIdAndPostNumber(@Param("minorId") String minorId, @Param("postNumber") Long postNumber);

    /**
     * minor post 찾기 + 최신순으로 조회(postNumber 가 큰 순으로)
     */
    @Query(value = "select p from Post p join fetch p.minorGallery m where m.minorId = :minorId",
            countQuery = "select count(p) from Post p join p.minorGallery m where m.minorId = :minorId")
    Page<Post> findByMinorId(@Param("minorId") String minorId, Pageable pageable);

    /**
     * 개념글 찾기:
     * 1. 추천이 10이상인 글
     * 2. 최신순으로 조회(postNumber 가 큰 순으로)
     */
    @Query(value = "select p from Post p join fetch p.minorGallery m where m.minorId = :minorId and p.likes >= 10",
            countQuery = "select count(p) from Post p join p.minorGallery m where m.minorId = :minorId and p.likes >= 10")
    Page<Post> findFamousPost(@Param("minorId") String minorId, Pageable pageable);
}
