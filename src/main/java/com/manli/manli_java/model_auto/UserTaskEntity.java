package com.manli.manli_java.model_auto;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "user_task")
public class UserTaskEntity {
    private int    id;
    private int    userId;
    private Date   taskTime;
    private String taskType;
    private Byte   status;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "taskTime")
    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }

    @Basic
    @Column(name = "taskType")
    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    @Basic
    @Column(name = "status")
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTaskEntity that = (UserTaskEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(taskTime, that.taskTime) &&
                Objects.equals(taskType, that.taskType) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, taskTime, taskType, status);
    }
}
