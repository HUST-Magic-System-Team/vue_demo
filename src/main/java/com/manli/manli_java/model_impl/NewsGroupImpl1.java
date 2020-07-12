package com.manli.manli_java.model_impl;

import com.manli.manli_java.model_auto.NewsGroupEntity;

public class NewsGroupImpl1 {
    private Integer groupId;
    private String  group;
    private Integer unreadCount;
    public NewsGroupImpl1() {

    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Integer getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Integer unreadCount) {
        this.unreadCount = unreadCount;
    }


}
