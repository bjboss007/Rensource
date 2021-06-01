package com.rensource.rental.video;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> getVideosByGenre(String genre);
    List<Video> getVideosByType_Name(String type);
}
