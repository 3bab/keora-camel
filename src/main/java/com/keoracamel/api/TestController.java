package com.keoracamel.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camel")
public class TestController {

    @GetMapping("/test")
    public String get() {
        return "labukas";
    }
}
