package com.lcdlv;

import scenario.Meals;

import java.util.Objects;

public class Cover {
    private Diet diet;

    public Cover(Diet diet, Integer total, Integer cold, Meals thursdayEvening) {
        this.diet = diet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cover cover = (Cover) o;
        return diet == cover.diet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diet);
    }
}
