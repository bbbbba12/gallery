package com.dcinside.gallery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Builder
@NoArgsConstructor @AllArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    /**
     * dc 갤러리에는 대댓글이 1번밖에 안됨
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> children = new ArrayList<>();

    private String content;
    
    // 임시 아이디, 비번 --> 포스트에도 마찬가지로 적용, 나중에 리팩토링 대상.
    private String author;
    private String password;

    private LocalDateTime createdAt;

    public void addPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

}
