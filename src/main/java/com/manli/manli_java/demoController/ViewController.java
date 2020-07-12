package com.manli.manli_java.demoController;

import com.manli.manli_java.demoController.Domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RequestMapping(value = "/api/demo")
@RestController
public class ViewController {

    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/view1", method = RequestMethod.GET)
    public ModelAndView view1(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view1");

        mv.addObject("message", "test message");
        Student student = new Student("男", 20, true);
        mv.addObject("student", student);


        String message = messageSource.getMessage("home.welcome", null, Locale.getDefault());
        System.out.println("message=" + message);
        mv.addObject("home_welcome", message);

        return mv;
    }

    @RequestMapping(value = "/view2", method = RequestMethod.GET)
    public ModelAndView view2(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view2");

        mv.addObject("message", "test message");
        Student student = new Student("男", 20, true);
        mv.addObject("student", student);

        return mv;
    }


}
