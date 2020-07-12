package com.manli.manli_java.service;

import com.manli.manli_java.model_auto.UserTaskEntity;
import com.manli.manli_java.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private UserTaskRepository userTaskRepository;

    public List<UserTaskEntity> getList(Integer userId) {
        return userTaskRepository.findAllByUserIdAndStatusOrderByTaskTimeAsc(userId, Byte.valueOf("0"));
    }

    public void regenUserTask(Integer userId, Date startTime) {
        userTaskRepository.deleteAllByUserIdAndStatus(userId, Byte.valueOf("0"));

        List<Integer> numArr = new ArrayList<>();
        numArr.add(10);
        numArr.add(20);
        numArr.add(30);

        List<UserTaskEntity> list = new ArrayList<>();
        for (Integer num : numArr) {
            Long start = startTime.getTime();
            Long end = start + num * 1000 * 60 * 60 * 24;
            java.sql.Date endDate = new java.sql.Date(end);
            UserTaskEntity userTaskEntity = new UserTaskEntity();
            userTaskEntity.setUserId(userId);
            userTaskEntity.setTaskTime(endDate);
            userTaskEntity.setTaskType("1");
            userTaskEntity.setStatus(Byte.valueOf("0"));
            list.add(userTaskEntity);
        }

        userTaskRepository.saveAll(list);

    }

}
