package com.example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;

public interface ClubApi {

    @Get
    Iterable<Club> list();

    @Get("/{id}")
    HttpResponse<Club> get(Long id);
}
