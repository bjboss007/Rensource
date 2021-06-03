package com.rensource.rental.video;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VideoAssembler implements RepresentationModelAssembler<VideoDTO, EntityModel<VideoDTO>> {


    private PricingDTO pricingDTO;
    @Override
    public EntityModel<VideoDTO> toModel(VideoDTO video) {
        Pageable pageable = PageRequest.of(0,5);
        return EntityModel.of(video,
                linkTo(methodOn(VideoController.class).getVideo(video.getId())).withSelfRel(),
                linkTo(methodOn(VideoController.class).getAllVideos(pageable)).withRel("all-Videos"),
                linkTo(methodOn(VideoController.class).getPricing(video.getId(), pricingDTO)).withRel("pricing")
                );
    }

    @Override
    public CollectionModel<EntityModel<VideoDTO>> toCollectionModel(Iterable<? extends VideoDTO> entities) {
        Collection<EntityModel<VideoDTO>> entityModelCollection = StreamSupport.stream(entities.spliterator(), false)
                .map(this::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(entityModelCollection);
    }

}
