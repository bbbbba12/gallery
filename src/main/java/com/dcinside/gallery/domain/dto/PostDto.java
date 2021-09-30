package com.dcinside.gallery.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class PostDto {
    private String name;
    private String content;
    private String author;
    private String password;
    private String postType;
}
