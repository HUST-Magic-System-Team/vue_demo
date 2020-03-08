package com.manli.manli_java.model_impl;

import com.manli.manli_java.model_auto.UserTaskEntity;

public class UserTaskImpl1 extends UserTaskEntity {

    public String dateString;
    public String content;
    public UserTaskImpl1(UserTaskEntity userTaskEntity) {
        this.setId(userTaskEntity.getId());
        this.setUserId(userTaskEntity.getUserId());
        this.setTaskTime(userTaskEntity.getTaskTime());
        this.setTaskType(userTaskEntity.getTaskType());
        this.setStatus(userTaskEntity.getStatus());
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
