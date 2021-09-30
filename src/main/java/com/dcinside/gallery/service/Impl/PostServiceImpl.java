package com.dcinside.gallery.service.Impl;

import com.dcinside.gallery.domain.MinorGallery;
import com.dcinside.gallery.domain.Post;
import com.dcinside.gallery.domain.dto.PostDto;
import com.dcinside.gallery.repository.MinorGalleryRepository;
import com.dcinside.gallery.repository.PostRepository;
import com.dcinside.gallery.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MinorGalleryRepository minorGalleryRepository;

    @Override
    @Transactional
    public void write(String minorId, PostDto postDto) {
        MinorGallery minorGallery = minorGalleryRepository.findByMinorId(minorId);

        Post post = Post.builder()
                .name(postDto.getName())
                .content(postDto.getContent())
                .postType(postDto.getPostType())
                .build();

        post.addMinorGallery(minorGallery);
    }


    /**
     * author, pw 변경 x + postNumber도 변경 x
     * 제목과 컨텐츠만 변경이 가능하다.
     */
    @Override
    @Transactional
    public void update(String minorId, Long postNumber, PostDto postDto) {
        Post post = postRepository.findByMinorGalleryIdAndPostNumber(minorId, postNumber);
        post.update(postDto);
    }
    /**
     * 삭제
     */
    @Override
    @Transactional
    public void delete(String minorId, Long postNumber) {
        postRepository.deleteByMinorGalleryIdAndPostNumber(minorId, postNumber);
    }

    @Override
    public Page<Post> findNormalPost(String minorId, Pageable pageable) {
        return postRepository.findByMinorGalleryId(minorId, pageable);
    }

    @Override
    public Post viewPost(String minorId, Long postNumber) {
        return postRepository.findByMinorGalleryIdAndPostNumber(minorId, postNumber);
    }

    @Override
    public Page<Post> findRecommendPost(String minorId, Pageable pageable) {
        return postRepository.findFamousPost(minorId, pageable);
    }
}
