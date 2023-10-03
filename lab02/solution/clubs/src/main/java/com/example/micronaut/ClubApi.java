package com.example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

public interface ClubApi {

    @Get
    Iterable<Club> list();

    @Get("/{id}")
    HttpResponse<Club> get(Long id);

    @Post
    HttpResponse<Void> create(Club club);

    @Put("/{id}")
    HttpResponse<Void> update(Long id, Club club);

    @Delete("/{id}")
    HttpResponse<Void> delete(Long id);
}
