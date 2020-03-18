package com.lcdlv;

import scenario.Meals;

import java.util.Objects;

public class Cover {
    private Diet diet;
    private Integer total;
    private Integer cold;
    private Meals thursdayEvening;

    public Cover(Diet diet, Integer total, Integer cold, Meals thursdayEvening) {
        this.diet = diet;
        this.total = total;
        this.cold = cold;
        this.thursdayEvening = thursdayEvening;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cover cover = (Cover) o;
        return diet == cover.diet &&
                total.equals(cover.total) &&
                cold.equals(cover.cold) &&
                thursdayEvening == cover.thursdayEvening;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diet, total, cold, thursdayEvening);
    }
}
