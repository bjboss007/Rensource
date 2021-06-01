package com.rensource.rental.video;

import com.rensource.rental.common.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoRepository videoRepo;

    @Override
    public VideoDTO getVideo(Long id) {
        Video video = videoRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Video Not Found"));
        return convertEntity(video);
    }

    @Override
    public List<VideoDTO> getVideoByType(String type) {
        return convertToEntities(videoRepo.getVideosByType_Name(type));
    }

    @Override
    public List<VideoDTO> getVideoByGenre(String genre) {
        return convertToEntities(videoRepo.getVideosByGenre(genre));
    }

    @Override
    public List<VideoDTO> getVideoList() {
        return convertToEntities(videoRepo.findAll());
    }

    @Override
    public Page<VideoDTO> getVideoPageable(Pageable pageable) {
        List<VideoDTO> videoDTOList = getVideoList();
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), videoDTOList.size());
        return new PageImpl<>(videoDTOList.subList(start, end), pageable, videoDTOList.size());
    }

    private VideoDTO convertEntity(Video video){
        return new VideoDTO(
                video.getId(),
                video.getTitle(),
                video.getGenre(),
                video.getType().getType()
        );
    }

    private List<VideoDTO> convertToEntities(List<Video> videoList){
        return videoList.stream()
                .map(this::convertEntity)
                .collect(Collectors.toList());
    }


}
