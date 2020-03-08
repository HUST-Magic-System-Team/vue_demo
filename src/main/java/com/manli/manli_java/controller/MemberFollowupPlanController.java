package com.manli.manli_java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/member_history_summary")
public class MemberFollowupPlanController {
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


    @RequestMapping(value = "shouldalert")
    public String shouldalert() {
        return "json shouldalert";
    }


}
