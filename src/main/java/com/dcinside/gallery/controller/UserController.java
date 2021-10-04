package com.dcinside.gallery.controller;

import com.dcinside.gallery.domain.Post;
import com.dcinside.gallery.domain.dto.AccountDto;
import com.dcinside.gallery.service.Impl.UserService;
import com.dcinside.gallery.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;

    @PostMapping("/signup")
    public String signup(AccountDto accountDto) {
        userService.signUp(accountDto);
        return "redirect:/";
    }

    @GetMapping("/auth")
    public String auth(@RequestParam("id") String minorId, @RequestParam("no") Long postNumber, Model model) {
        Post post = postService.viewPost(minorId, postNumber);
        model.addAttribute("postId", post.getId());
        return "/auth";
    }
}
