package com.finances.finT.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Tests {
    @GetMapping("/test")
    public String testPage() {
        return "test";
    }
}