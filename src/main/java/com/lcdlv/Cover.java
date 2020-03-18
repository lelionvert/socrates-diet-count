package com.lcdlv;

import scenario.Meal;

import java.util.Objects;

public class Cover {
    private Diet diet;
    private Integer total;
    private Integer totalCold;
    private Meal meal;

    public Cover(Diet diet, Integer total, Integer totalCold, Meal meal) {
        this.diet = diet;
        this.total = total;
        this.totalCold = totalCold;
        this.meal = meal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cover cover = (Cover) o;
        return diet == cover.diet &&
                total.equals(cover.total) &&
                totalCold.equals(cover.totalCold) &&
                meal == cover.meal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diet, total, totalCold, meal);
    }
}
