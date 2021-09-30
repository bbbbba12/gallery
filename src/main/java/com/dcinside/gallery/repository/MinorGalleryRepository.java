package com.dcinside.gallery.repository;

import com.dcinside.gallery.domain.MinorGallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinorGalleryRepository extends JpaRepository<MinorGallery, Long> {
    MinorGallery findByMinorId(String minorId);
}
