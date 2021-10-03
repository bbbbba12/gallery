package com.dcinside.gallery.service.Impl;

import com.dcinside.gallery.domain.Account;
import com.dcinside.gallery.domain.Post;
import com.dcinside.gallery.domain.dto.MinorGalleryDto;
import com.dcinside.gallery.domain.dto.PostDto;
import com.dcinside.gallery.repository.PostRepository;
import com.dcinside.gallery.service.MinorGalleryService;
import com.dcinside.gallery.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceImplTest {

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @Autowired
    MinorGalleryService minorGalleryService;

    @Autowired
    EntityManager em;

}