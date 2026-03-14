package com.example.self_management.model.dto.auth;

public class LoginRequest {
    private String username;
    private String password;

    //Added for device tracking
    private String deviceId;

    public LoginRequest(){}

    public LoginRequest(String username, String password, String deviceId) {
        this.password = password;
        this.username = username;
        this.deviceId = deviceId;
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

    public String getDeviceId(){
        return deviceId;
    }
    public void setDeviceId(String deviceId){
        this.deviceId = deviceId;
    }
}
