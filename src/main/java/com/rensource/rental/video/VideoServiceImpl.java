package com.rensource.rental.video;

import com.rensource.rental.common.exceptions.BadRequestException;
import com.rensource.rental.common.exceptions.ObjectNotFoundException;
import com.rensource.rental.video.dto.VideoDTO;
import com.rensource.rental.video.rentedVideo.RentedVideo;
import com.rensource.rental.video.rentedVideo.RentedVideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoRepository videoRepo;

    @Autowired
    private RentedVideoRepo rentedVideoRepo;

    @Override
    public VideoDTO getVideo(Long id) {
        Video video = videoRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Video Not Found"));
        return convertEntity(video);
    }

    @Override
    public List<VideoDTO> getVideoByType(String type) {
        return convertToEntities(videoRepo.getVideosByType_Type(type));
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

    @Override
    public RentedVideo calculatePrice(Long id, String name, int days) {
        double price = 0.0;
        RentedVideo rentedVideo = new RentedVideo();
        Video video = videoRepo.findById(id).orElseThrow(()-> new ObjectNotFoundException("Video not found"));
        if(days <= 0)
            throw new BadRequestException("Rental Day(s) should be greater than Zero");
        if(video.getType().getType() !=null){
            if ("regular".equalsIgnoreCase(video.getType().getType())) {
                price = 10 * days;
            } else if ("children".equalsIgnoreCase(video.getType().getType())) {
                price = 8 * days + (video.getType().getMaxAge()/2);
            }else if ("new_release".equalsIgnoreCase(video.getType().getType())){
                int year = Integer.parseInt(video.getType().getReleasedDate().substring(2));
                price = Math.abs(15 * days-year);
            }
        }
        rentedVideo.setPrice(BigDecimal.valueOf(price));
        rentedVideo.setUsername(name);
        rentedVideo.setTitle(video.getTitle());
        rentedVideoRepo.save(rentedVideo);
        return rentedVideo;
    }

    @Override
    public List<RentedVideo> getRentalVideos() {
        return rentedVideoRepo.findAll();
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
