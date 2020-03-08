package com.manli.manli_java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/member_followup_plan")
public class MemberHistorySummaryController {
    @RequestMapping(value = "list")
    public String list() {
        return "json list";
    }

    @RequestMapping(value = "add")
    public String add() {
        return "json add";
    }

    @RequestMapping(value = "del")
    public String del() {
        return "json del";
    }

    @RequestMapping(value = "update")
    public String update() {
        return "json update";
    }

    @RequestMapping(value = "read")
    public String read() {
        return "json read";
    }

    @RequestMapping(value = "isread")
    public String isread() {
        return "json isread";
    }


}
