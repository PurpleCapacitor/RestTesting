package com.example.demo.dto;

import java.io.Serializable;

public class StudentDTO implements Serializable {

    private Integer id;
    private String name;
    private String accountName;
    private String password;

    public StudentDTO() {
    }

    public StudentDTO(Integer id, String name, String accountName, String password) {
        this.id = id;
        this.name = name;
        this.accountName = accountName;
        this.password = password;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
