package com.example.micronaut.clubs;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;

import java.util.Collection;

public interface ClubApi {

    @Get
    Collection<ClubDto> list();

    @Get("/{id}")
    HttpResponse<ClubDto> get(Long id);

    @Post
    HttpResponse<Void> create(ClubDto club);

    @Put("/{id}")
    HttpResponse<Void> update(Long id, ClubDto club);

    @Delete("/{id}")
    HttpResponse<Void> delete(Long id);
}
