package com.manli.manli_java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/member")
public class MemberController {
    @RequestMapping(value = "get")
    public String get() {
        return "json get";
    }

    @RequestMapping(value = "save")
    public String save() {
        return "json save";
    }

    @RequestMapping(value = "ismember")
    public String ismember() {
        return "json ismember";
    }
}
