package com.dcinside.gallery.controller;

import com.dcinside.gallery.domain.dto.PostDto;
import com.dcinside.gallery.security.PrincipalDetails;
import com.dcinside.gallery.service.MinorGalleryService;
import com.dcinside.gallery.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MinorGalleryService minorGalleryService;

    @GetMapping("/write")
    public String preWrite(@RequestParam("id") String minorId, Model model) {
        model.addAttribute("postType", minorGalleryService.viewPostType(minorId));
        return "write";
    }

    @PostMapping("/write")
    public String write(
            @RequestParam("id") String minorId,
            @RequestBody @Valid PostDto postDto, BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        postService.write(minorId, postDto);
        // 마이너 갤러리 메인 페이지(lists)로 이동
        redirectAttributes.addAttribute("id", minorId);
        return "redirect:/lists";
    }

    @PutMapping("/modify")
    public String modify(
            @RequestParam("id") String minorId,
            @RequestParam("no") Long postNumber,
            @RequestBody @Valid PostDto postDto, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        postService.modify(minorId, postNumber, postDto);

        redirectAttributes.addAttribute("id", minorId);
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
