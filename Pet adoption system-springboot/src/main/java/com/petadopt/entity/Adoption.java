package com.petadopt.entity;

import lombok.Getter;

@Getter
public class Adoption {
    private int id;
    private String user_name;
    private String user_username;
    private String pet_name;
    private String pet_type;

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public void setPet_name(String pet_name) {
        this.pet_name = pet_name;
    }

    public void setPet_type(String pet_type) {
        this.pet_type = pet_type;
    }

    @Override
    public String toString() {
        return "Adoption{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", user_username='" + user_username + '\'' +
                ", pet_name='" + pet_name + '\'' +
                ", pet_type='" + pet_type + '\'' +
                '}';
    }
}


