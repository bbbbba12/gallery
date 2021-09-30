package com.dcinside.gallery.service;

import com.dcinside.gallery.domain.Account;
import com.dcinside.gallery.domain.dto.MinorGalleryDto;

public interface MinorGalleryService {
    /**
     * 1. 갤러리 생성
     */
    void createGallery(MinorGalleryDto minorGalleryDto, Account account);
}
