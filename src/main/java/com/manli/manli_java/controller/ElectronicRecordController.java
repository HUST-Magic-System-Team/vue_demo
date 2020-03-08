package com.manli.manli_java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/electronic_record")
public class ElectronicRecordController {
    @RequestMapping(value = "list")
    public String list() {
        return "json list";
    }

}
