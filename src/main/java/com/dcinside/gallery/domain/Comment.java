package com.dcinside.gallery.domain;

import com.dcinside.gallery.domain.base.BaseEntity;
import com.dcinside.gallery.domain.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Comment parent;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
    /**
     * dc 갤러리에는 대댓글이 1번밖에 안됨
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    private String content;
    private String password;
    private boolean authenticated = false;

    public boolean matches(String password) {
        if(authenticated == false && this.password == password) return true;
        return false;
    }

    public void addPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public void nestCommentOn(Comment comment) {
        this.parent = comment;
        comment.getChildren().add(this);
    }

    public void update(CommentDto commentDto) {
        this.content = commentDto.getContent();
    }
}
