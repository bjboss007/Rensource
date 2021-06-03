package com.rensource.rental.video;





import com.rensource.rental.common.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class VideoType extends AbstractEntity {
    private String type;
    private int maxAge;
    private String releasedDate;

    public VideoType() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public enum Type {
        REGULAR("Regular"),
        CHILDREN("Children"),
        NEW_RELEASE("New_Release");

        private final String type;

        public String getType() {
            return type;
        }

        Type(String t) {
            this.type = t;
        }
    }
}

