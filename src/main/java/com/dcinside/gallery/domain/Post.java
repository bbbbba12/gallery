package com.dcinside.gallery.domain;

import com.dcinside.gallery.domain.base.BaseEntity;
import com.dcinside.gallery.domain.dto.AccountDto;
import com.dcinside.gallery.domain.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MINORGALLERY_ID")
    @JsonIgnore
    private MinorGallery minorGallery;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    private String name;
    private String content;
    private String postType;
    private Long postNumber;
    private Long views;
    private Long likes;
    private Long hates;
    private String password;
    private boolean authenticated = false;

    public boolean matches(String password) {
        if(authenticated == false && password.equals(this.password)) return true;
        return false;
    }

    public void update(PostDto postDto) {
        this.name = postDto.getName();
        this.content = postDto.getContent();
    }

    public void addMinorGallery(MinorGallery minorGallery) {
        this.minorGallery = minorGallery;
        minorGallery.getPosts().add(this);
    }

    private String createdBy;

    public void anonymousUser(String author, String password) {
        this.createdBy = author;
        this.password = password;
    }

    public void authenticatedUser(Account account) {
        this.account = account;
        account.getPosts().add(this);
        this.authenticated = true;
    }
}