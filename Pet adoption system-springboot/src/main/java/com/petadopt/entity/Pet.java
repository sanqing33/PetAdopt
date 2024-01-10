package com.petadopt.entity;

import lombok.Getter;

@Getter
public class Pet {

    private int id;
    private String name;
    private String species;
    private int age;
    private String description;
    private int available;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Pets{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", available=" + available +
                '}';
    }
}

