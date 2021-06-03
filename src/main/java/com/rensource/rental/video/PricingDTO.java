package com.rensource.rental.video;

public class PricingDTO {
    private String name;
    private int days;

    public PricingDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "PricingDTO{" +
                "name='" + name + '\'' +
                ", days=" + days +
                '}';
    }
}
