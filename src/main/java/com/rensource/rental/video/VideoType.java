package com.rensource.rental.video;





import com.rensource.rental.common.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class VideoType extends AbstractEntity {
    private String type;
    private String maxAge;
    private String releasedDate;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public enum Type {
        REGULAR("regular"),
        CHILDREN("children"),
        NEW_RELEASE("new_release");

        private final String type;

        public String getType() {
            return type;
        }

        Type(String t) {
            this.type = t;
        }
    }
}

