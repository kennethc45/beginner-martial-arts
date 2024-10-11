package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/test")
public class martialArtAPI {

    @GetMapping("/hello")
    public String helloWorld() {
        System.out.println("hello endpoint works");
        System.out.println("hello endpoint works");
        return "Hello World!";
    }

}