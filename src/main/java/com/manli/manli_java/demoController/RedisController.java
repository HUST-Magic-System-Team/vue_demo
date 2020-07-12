package com.manli.manli_java.demoController;

import com.manli.manli_java.util.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/demo/redis")
public class RedisController {
    @Autowired
    RedisTemplate redis1Template;

    @Autowired
    RedisTemplate redis2Template;


    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @RequestMapping(value = "get")
    public ResultBean get() {
        Map<String, Object> data = new HashMap<>();

        redis1Template.opsForValue().set("key1", "value1");
        String value1 = (String) (redis1Template.opsForValue().get("key1"));
        data.put("key1_on_11_72", value1);

        redis2Template.opsForValue().set("key2", "value222");
        String value2 = (String) (redis2Template.opsForValue().get("key2"));
        data.put("key1_on_sandbox", value2);

        return new ResultBean(data);

    }
}
