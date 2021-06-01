package com.rensource.rental.video;


import com.rensource.rental.common.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoAssembler assembler;

    private PagedResourcesAssembler<VideoDTO> pagedResourcesAssembler;

    public VideoController(PagedResourcesAssembler<VideoDTO> pagedResourcesAssembler) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("")
    public ResponseEntity<PagedModel<EntityModel<VideoDTO>>> getAllVideos(Pageable pageable){
        Page<VideoDTO> videos = videoService.getVideoPageable(pageable);
        PagedModel<EntityModel<VideoDTO>> videoEntities = pagedResourcesAssembler
                .toModel(videos, assembler);
        return ResponseEntity.ok(videoEntities);
    }

    @GetMapping("{id}")
    public EntityModel<VideoDTO> getVideo(Long id){
        VideoDTO video = videoService.getVideo(id);
        return assembler.toModel(video);
    }

    @PutMapping("/{id}/pricing")
    public ResponseEntity getPricing(Long id, @RequestBody(required = false) String name){
        VideoDTO video = videoService.getVideo(id);
        RentedVideo rentedVideo = videoService.calculatePrice(video, name);
        return ResponseEntity.ok();
    }
}
