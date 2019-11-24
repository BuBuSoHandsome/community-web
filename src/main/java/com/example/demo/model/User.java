package com.example.demo.model;

import java.sql.Timestamp;

public class User {
    private Integer id;
    private String name;
    private String account_id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    //ps:如果一个类有了有参构造，必然也要无参构造，否则mybatis会报错。
    public User() {
    }

    public User(String name, String account_id, Timestamp gmtCreate, Timestamp gmtModified) {
        this.name = name;
        this.account_id = account_id;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }
}
