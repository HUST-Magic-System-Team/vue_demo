package com.manli.manli_java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/shouldalert")
public class ShouldAlertController {
    @RequestMapping(value = "shouldalert")
    public String shouldalert() {
        return "json shouldalert";
    }
}
