package com.dcinside.gallery.controller;

import com.dcinside.gallery.domain.Account;
import com.dcinside.gallery.domain.dto.AccountDto;
import com.dcinside.gallery.domain.dto.PostDto;
import com.dcinside.gallery.security.PrincipalDetails;
import com.dcinside.gallery.service.MinorGalleryService;
import com.dcinside.gallery.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MinorGalleryService minorGalleryService;

    @GetMapping("/write")
    public String preWrite(@RequestParam("id") String minorId, Model model) {
        model.addAttribute("postType", minorGalleryService.viewPostType(minorId));
        return "writeForm";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam("id") String minorId,
            @RequestBody @Valid PostDto postDto, BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Account account = null;
        if(principalDetails != null) account = principalDetails.getAccount();
        postService.write(minorId, postDto, account);

        redirectAttributes.addAttribute("id", minorId);
        return "redirect:/lists";
    }

    @GetMapping("/modify")
    public String getModify(
            @RequestParam("id") String minorId, @RequestParam("no") Long postNumber, Model model, HttpServletRequest request) {


        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return "writeForm";
    }
    @PutMapping("/modify")
    public String postModify(
            @RequestParam("id") String minorId,
            @RequestParam("no") Long postNumber,
            @RequestBody @Valid PostDto postDto, BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal PrincipalDetails principalDetails, HttpServletRequest request) {

        postService.modify(minorId, postNumber, postDto);
        redirectAttributes.addAttribute("id", minorId);

        request.getSession().invalidate();
        SecurityContextHolder.clearContext();

        return "redirect:/lists";
    }

    @DeleteMapping("/delete")
    public String delete(
            @RequestParam("id") String minorId,
            @RequestParam("no") Long postNumber,
            RedirectAttributes redirectAttributes) {

        postService.delete(minorId, postNumber);

        redirectAttributes.addAttribute("id", minorId);
        return "redirect:/lists";
    }
}
