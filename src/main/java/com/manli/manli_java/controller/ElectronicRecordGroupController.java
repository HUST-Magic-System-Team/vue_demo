package com.manli.manli_java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/electronic_record_group")
public class ElectronicRecordGroupController {
    @RequestMapping(value = "bulksetgroup")
    public String bulksetgroup() {
        return "json bulksetgroup";
    }

    @RequestMapping(value = "isvalidgroup")
    public String isvalidgroup() {
        return "json isvalidgroup";
    }

    @RequestMapping(value = "groupinfo")
    public String groupinfo() {
        return "json groupinfo";
    }

}
