package com.lcdlv;

public enum Diet {

    VEGE("vegetarian"),
    OMNI("omnivore"),
    PESCE("pescetarian"),
    VEGAN("vegan");

    private String type;

    Diet(String type) {
        this.type = type;
    }
}
