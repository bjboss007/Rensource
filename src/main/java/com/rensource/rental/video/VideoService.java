package com.rensource.rental.video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VideoService {

    VideoDTO getVideo(Long id);
    List<VideoDTO> getVideoByType(String type);
    List<VideoDTO> getVideoByGenre(String genre);
    List<VideoDTO> getVideoList();
    Page<VideoDTO> getVideoPageable(Pageable pageable);

}
