package models;

import lombok.Data;

@Data
public class User {
    private String name;
    private String job;

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
