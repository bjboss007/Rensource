package com.rensource.rental;

import com.rensource.rental.common.exceptions.ObjectNotFoundException;
import com.rensource.rental.video.*;
import com.rensource.rental.video.dto.VideoDTO;
import com.rensource.rental.video.rentedVideo.RentedVideo;
import com.rensource.rental.video.rentedVideo.RentedVideoRepo;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class VideoRentalApplicationTests {

    @InjectMocks
    private VideoServiceImpl videoService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private VideoTypeRepository videoTypeRepository;

    @Mock
    private RentedVideoRepo rentedVideoRepo;

    VideoRentalApplicationTests() {
    }

    @Before
    private void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetVideo() {
        VideoType regular = new VideoType();
        regular.setType("Regular");

        Video video = new Video();
        video.setTitle("Wrath of Man");
        video.setGenre(Video.Genre.ACTION.getGenre());
        video.setType(regular);

        Mockito.when(videoTypeRepository.save(any(VideoType.class))).thenReturn(regular);
        Mockito.when(videoRepository.findById(1L)).thenReturn(Optional.of(video));
        Mockito.when(videoRepository.getById(1L)).thenReturn(video);

        videoTypeRepository.save(regular);
        videoRepository.save(video);
        VideoDTO video1 = videoService.getVideo(1L);

        Assertions.assertEquals(video1.getTitle(),"Wrath of Man");

    }

    @Test
    void expectedException(){

        VideoType regular = new VideoType();
        regular.setType("Regular");

        Video video = new Video();
        video.setTitle("Wrath of Man");
        video.setGenre(Video.Genre.ACTION.getGenre());
        video.setType(regular);

        Mockito.when(videoTypeRepository.save(any(VideoType.class))).thenReturn(regular);
        Mockito.when(videoRepository.findById(1L)).thenReturn(Optional.of(video));
        Mockito.when(videoRepository.getById(1L)).thenReturn(video);

        videoTypeRepository.save(regular);
        videoRepository.save(video);

        Exception exception = assertThrows(
                ObjectNotFoundException.class,
                () -> videoService.getVideo(2L));

        assertTrue(exception.getMessage().contains("Not Found"));
    }

    @Test
    void testGetVideos(){
        List<Video> videos = new ArrayList<>();

        VideoType regular = new VideoType();
        regular.setType("Regular");

        Video video = new Video();
        video.setTitle("Wrath of Man");
        video.setGenre(Video.Genre.ACTION.getGenre());
        video.setType(regular);

        Video video2 = new Video();
        video2.setTitle("The Vault");
        video2.setGenre(Video.Genre.ACTION.getGenre());
        video2.setType(regular);

        Video video3 = new Video();
        video3.setTitle("Mr Macoba");
        video3.setGenre(Video.Genre.COMEDY.getGenre());
        video3.setType(regular);

        videos.add(video);
        videos.add(video2);
        videos.add(video3);

        Mockito.when(videoRepository.findAll()).thenReturn(videos);

        List<Video> videoList = videoRepository.findAll();

        assertNotNull(videoList);
        assertEquals(3, videoList.size());
    }

    @Test
    void testCalculatePrice(){
        VideoType regular = new VideoType();
        regular.setType("Regular");

        VideoType children = new VideoType();
        children.setType("Children");
        children.setMaxAge(18);

        Video video = new Video();
        video.setTitle("Frozen");
        video.setGenre(Video.Genre.DRAMA.getGenre());
        video.setType(children);


        RentedVideo rentedVideo = new  RentedVideo();
        rentedVideo.setUsername("Habib");
        rentedVideo.setPrice(new BigDecimal("89"));
        rentedVideo.setTitle("Frozen");

        Mockito.when(videoRepository.findById(1L)).thenReturn(Optional.of(video));
        Mockito.when(rentedVideoRepo.save(any(RentedVideo.class))).thenReturn(rentedVideo);
        Mockito.when(videoRepository.getById(1L)).thenReturn(video);
//        Mockito.when(videoService.calculatePrice(1L, "Habib", 10)).thenReturn(rentedVideo);
        RentedVideo test = videoService.calculatePrice(1L, "Habib", 10);

        assertEquals(test.getPrice(), BigDecimal.valueOf(89.0));
    }

}
