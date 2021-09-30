package com.dcinside.gallery.service.Impl;

import com.dcinside.gallery.domain.Account;
import com.dcinside.gallery.domain.MinorGallery;
import com.dcinside.gallery.domain.dto.MinorGalleryDto;
import com.dcinside.gallery.repository.MinorGalleryRepository;
import com.dcinside.gallery.service.MinorGalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MinorGalleryServiceImpl implements MinorGalleryService {

    private final MinorGalleryRepository minorGalleryRepository;

    @Override
    public void createGallery(MinorGalleryDto minorGalleryDto, Account account) {
        MinorGallery gallery = MinorGallery.builder()
                .name(minorGalleryDto.getName())
                .minorId(minorGalleryDto.getMinorId())
                // .manager(account.getUsername())
                .totalPost(0)
                .build();
        minorGalleryRepository.save(gallery);
    }
}
