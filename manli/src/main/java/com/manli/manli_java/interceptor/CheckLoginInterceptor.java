package com.manli.manli_java.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manli.manli_java.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


public class CheckLoginInterceptor implements HandlerInterceptor {
    @Autowired
    LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if (null == token) {
            Map<String, Object> map = new HashMap<>();
            map.put("errno", -4);
            map.put("errmsg", "缺少token");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(jsonStr);
            return false;
        } else if (loginService.isLogin(token) == false) {
            Map<String, Object> map = new HashMap<>();
            map.put("errno", -5);
            map.put("errmsg", "没有登录");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStr = objectMapper.writeValueAsString(map);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(jsonStr);
            return false;
        }

        Integer userId = loginService.getUserIdByToken(token);
        request.setAttribute("userId", userId);
        request.setAttribute("token", token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}


