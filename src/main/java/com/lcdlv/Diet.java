package com.lcdlv;

public enum Diet {

    VEGE("vegetarian"),
    OMNI("omnivore"),
    VEGAN("vegan");

    private String name;

    Diet(String name) {
        this.name = name;
    }
}
