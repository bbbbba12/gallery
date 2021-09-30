package com.dcinside.gallery.controller.api;


import com.dcinside.gallery.domain.Post;
import com.dcinside.gallery.domain.dto.AccountDto;
import com.dcinside.gallery.domain.dto.PostDto;
import com.dcinside.gallery.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @GetMapping("/lists")
    public Page<Post> normalLists(
            @RequestParam("id") String minorId,
            @PageableDefault(size = 50, sort = "postNumber", direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.findNormalPost(minorId, pageable);
    }

    @GetMapping(value = "/lists", params = "exception_mode")
    public Page<Post> recommendLists(
            @RequestParam("id") String minorId,
            @PageableDefault(size = 50, sort = "postNumber", direction = Sort.Direction.DESC) Pageable pageable) {
        return postService.findRecommendPost(minorId, pageable);
    }

    @GetMapping("/view")
    public Post viewPost(
            @RequestParam("id") String minorId,
            @RequestParam("no") Long postNumber) {
        return postService.viewPost(minorId, postNumber);
    }
}
