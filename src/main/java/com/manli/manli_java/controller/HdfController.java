package com.manli.manli_java.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/hdf")
public class HdfController {
    @RequestMapping(value = "hdfurl")
    public String hdfurl() {
        return "json hdfurl";
    }
}
