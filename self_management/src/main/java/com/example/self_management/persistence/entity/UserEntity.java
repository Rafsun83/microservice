package com.example.self_management.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String location;
    private String password;

    @Column(unique = true)
    private String username;
    private Date createdAt;

    public UserEntity(){}

    public UserEntity( String email, String name, String location, String password, String username, Date createdAt){
        this.email = email;
        this.name = name;
        this.location = location;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;

    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location=location;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public Date getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
