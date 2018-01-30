package com.formation.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"color", "name", "domesticated"})
public class Cat {
    @JsonProperty("catName")
    private String name;
    private String color;
    private boolean domesticated;

    // json-b needs the default constructor
    public Cat() {
        super();
    }

    public Cat(String name, String color, boolean domesticated) {
        this.name = name;
        this.color = color;
        this.domesticated = domesticated;
    }

    public String getName() {
        return name;
    }

    public Cat setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Cat setColor(String color) {
        this.color = color;
        return this;
    }

    public boolean isDomesticated() {
        return domesticated;
    }

    public Cat setDomesticated(boolean domesticated) {
        this.domesticated = domesticated;
        return this;
    }
}
