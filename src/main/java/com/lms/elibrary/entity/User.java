package com.lms.elibrary.entity;

import javax.persistence.*;

@Entity
@Table(name = "usermaster")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    long adhar;
    long mob;
    String email;
    String password;
    String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdhar() {
        return adhar;
    }

    public void setAdhar(long adhar) {
        this.adhar = adhar;
    }

    public long getMob() {
        return mob;
    }

    public void setMob(long mob) {
        this.mob = mob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
