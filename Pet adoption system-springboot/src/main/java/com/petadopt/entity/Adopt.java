package com.petadopt.entity;

import lombok.Getter;

@Getter
public class Adopt {
    private int id;
    private String name;
    private String age;
    private String address;
    private String phone;
    private String petType;
    private String reason;
    private String created_at;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Adopt{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", petType='" + petType + '\'' +
                ", reason='" + reason + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
