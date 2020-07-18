package com.manli.manli_java.controller;

import com.manli.manli_java.model_auto.UserTaskEntity;
import com.manli.manli_java.service.TaskService;
import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @RequestMapping(value = "list")
    public ResultBean list(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("userId");
        List<UserTaskEntity> list = taskService.getList(userId);
        Map<String, Object> data = new HashMap<>();
        if (list != null) {
            data.put("list", list);
        } else {
            data.put("list", new ArrayList<>());
        }

        return new ResultBean(data);
    }
}
