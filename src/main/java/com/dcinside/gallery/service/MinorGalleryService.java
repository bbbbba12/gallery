package com.dcinside.gallery.service;

import com.dcinside.gallery.domain.Account;
import com.dcinside.gallery.domain.dto.MinorGalleryDto;

import java.util.List;

public interface MinorGalleryService {
    /**
     * 1. 갤러리 생성
     */
    void createGallery(MinorGalleryDto minorGalleryDto, Account account);

    String viewPostType(String minorId);
}
