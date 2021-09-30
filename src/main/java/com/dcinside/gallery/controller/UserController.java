package com.dcinside.gallery.controller;

import com.dcinside.gallery.domain.dto.AccountDto;
import com.dcinside.gallery.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(AccountDto accountDto) {
        userService.signUp(accountDto);
        return "redirect:/";
    }
}