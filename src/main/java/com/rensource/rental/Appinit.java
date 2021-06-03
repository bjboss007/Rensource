package com.rensource.rental;

import com.rensource.rental.video.Video;
import com.rensource.rental.video.VideoRepository;
import com.rensource.rental.video.VideoType;
import com.rensource.rental.video.VideoTypeRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Appinit implements InitializingBean {

    private VideoRepository repository;
    private VideoTypeRepository videoTypeRepository;

    public Appinit(VideoRepository repository, VideoTypeRepository videoTypeRepository) {
        this.repository = repository;
        this.videoTypeRepository = videoTypeRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if(repository.count() <=0){
            createVideos();
        }
    }

    private void createVideos(){
        VideoType regular = new VideoType();
        regular.setType("Regular");
        regular = videoTypeRepository.save(regular);

        VideoType children = new VideoType();
        children.setType("Children");
        children.setMaxAge(18);
        children = videoTypeRepository.save(children);

        VideoType newRelease = new VideoType();
        newRelease.setType("New_Release");
        newRelease.setReleasedDate("2021");
        newRelease = videoTypeRepository.save(newRelease);



        Video video1 = new Video();
        video1.setTitle("Coco");
        video1.setGenre(Video.Genre.DRAMA.getGenre());
        video1.setType(children);

        Video video2 = new Video();
        video2.setTitle("Frozen");
        video2.setGenre(Video.Genre.DRAMA.getGenre());
        video2.setType(children);

        Video video3 = new Video();
        video3.setTitle("Wrath of Man");
        video3.setGenre(Video.Genre.ACTION.getGenre());
        video3.setType(regular);

        Video video4 = new Video();
        video4.setTitle("The Vault");
        video4.setGenre(Video.Genre.ACTION.getGenre());
        video4.setType(regular);

        Video video5 = new Video();
        video5.setTitle("Mr Macoba");
        video5.setGenre(Video.Genre.COMEDY.getGenre());
        video5.setType(regular);

        Video video6 = new Video();
        video6.setTitle("After We Collide");
        video6.setGenre(Video.Genre.ROMANCE.getGenre());
        video6.setType(newRelease);

        Video video7 = new Video();
        video7.setTitle("Army of the Dead");
        video7.setGenre(Video.Genre.HORROR.getGenre());
        video7.setType(newRelease);

        Video video8 = new Video();
        video8.setTitle("Crazy about Her");
        video8.setGenre(Video.Genre.COMEDY.getGenre());
        video8.setType(newRelease);

        repository.saveAll(Arrays.asList(video1, video2, video3, video4, video5, video6, video7, video8));

    }
}
