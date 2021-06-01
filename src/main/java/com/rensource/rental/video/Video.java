package com.rensource.rental.video;

import com.rensource.rental.common.AbstractEntity;

import javax.persistence.*;

@Entity
public class Video extends AbstractEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private VideoType type;
    private String genre;

    public Video() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public VideoType getType() {
        return type;
    }

    public void setType(VideoType type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public enum Genre {
        ACTION("action"),
        DRAMA("drama"),
        ROMANCE("romance"),
        COMEDY("comedy"),
        HORROR("horror");

        private final String genre;

        public String getGenre() {
            return genre;
        }

        Genre(String g) {
            this.genre = g;
        }
    }
}
