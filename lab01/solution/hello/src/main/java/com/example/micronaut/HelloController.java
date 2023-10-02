package com.example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

//tag::class[]
@Controller // <1>
public class HelloController {

    @Get("/hello/{name}") // <2>
    public String sayHello(String name) { // <3>
        return "Hello " + name;

    }
}
//end::class[]