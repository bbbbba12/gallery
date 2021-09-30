package com.dcinside.gallery.domain;

import com.dcinside.gallery.domain.dto.PostDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Builder @Getter
@NoArgsConstructor @AllArgsConstructor
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MINORGALLERY_ID")
    private MinorGallery minorGallery;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private String name;
    private String content;
    private String author;
    private String password;
    private String postType;
    private Long postNumber;
    private Long views;
    private Long likes;
    private Long hates;

    private LocalDateTime createdAt;

    public void update(PostDto postDto) {
        this.name = postDto.getName();
        this.content = postDto.getContent();
    }

    public void addMinorGallery(MinorGallery minorGallery) {
        this.minorGallery = minorGallery;
        minorGallery.getPosts().add(this);
    }

}
