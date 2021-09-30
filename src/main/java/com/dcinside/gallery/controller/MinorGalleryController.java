package com.dcinside.gallery.controller;

import com.dcinside.gallery.domain.Account;
import com.dcinside.gallery.domain.dto.MinorGalleryDto;
import com.dcinside.gallery.security.PrincipalDetails;
import com.dcinside.gallery.service.MinorGalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MinorGalleryController {

    private final MinorGalleryService minorGalleryService;

    @PostMapping("/minorGallery/create")
    public String createGallery(@RequestBody MinorGalleryDto minorGalleryDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Account build = Account.builder().build();
        minorGalleryService.createGallery(minorGalleryDto, build);
        return "create!";
    }
}
