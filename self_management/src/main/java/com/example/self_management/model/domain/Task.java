package com.example.self_management.model.domain;


import java.util.Date;


public class Task {


    private Long id;
    private String title;
    private String description;
    private String TaskType;
    private Number status;
    private String coverImage;
    private Date createTime;
    private Date updateTime;


    public Task() {}

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public Date getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    public String getTaskType(){
        return TaskType;
    }
    public void setTaskType(String taskType){
        TaskType = taskType;
    }
    public String getCoverImage(){
        return coverImage;
    }
    public void setCoverImage(String coverImage){
        this.coverImage = coverImage;
    }
    public Number getStatus(){
        return status;
    }
    public void setStatus(Number status){
        this.status = status;
    }
}
