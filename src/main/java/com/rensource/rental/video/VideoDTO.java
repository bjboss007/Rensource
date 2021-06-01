package com.rensource.rental.video;

public class VideoDTO {

    private Long id;
    private String title;
    private String genre;
    private String type;

    public VideoDTO(Long id, String title, String genre, String type) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.type = type;
    }


    public VideoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
