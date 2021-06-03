package com.rensource.rental.video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> getVideosByGenre(String genre);
    List<Video> getVideosByType_Type(String type);
}
