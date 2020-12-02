package com.example.leesanghoon.writesongproject;

public class User {
    String id;
    String password;
    String name;
    String seq;


    public User(String id, String password, String name,String seq) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.seq=seq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswoard() {
        return password;
    }

    public void setPasswoard(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeq() {
        return seq;
    }
    public void setSeq(String seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", seq=" + seq + "]";
    }
}
