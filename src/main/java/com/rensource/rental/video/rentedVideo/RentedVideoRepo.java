package com.rensource.rental.video.rentedVideo;

import com.rensource.rental.video.rentedVideo.RentedVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentedVideoRepo extends JpaRepository<RentedVideo, Long> {
}
