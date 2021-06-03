package com.rensource.rental.video;

import com.rensource.rental.common.AbstractEntity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class RentedVideo extends AbstractEntity {
    private String username;
    private String title;
    private BigDecimal price;

    public RentedVideo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
