package com.manli.manli_java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/suggestion")
public class SuggestionController {
    @RequestMapping(value = "add")
    public String add() {
        return "json add";
    }

    @RequestMapping(value = "del")
    public String del() {
        return "json del";
    }

}
