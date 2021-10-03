package com.dcinside.gallery.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Builder @Getter
@NoArgsConstructor  @AllArgsConstructor
public class MinorGallery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String minorId;
    private String name;
    private String manager;
    private String postType;
    private int totalPost;

    @OneToMany(mappedBy = "minorGallery", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;
}
