package com.example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

@Controller("/clubs")
public class ClubController implements ClubApi {

    private final ClubRepository repository;

    public ClubController(ClubRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Club> list() {
        return repository.findAll();
    }

    @Override
    public HttpResponse<Club> get(Long id) {
        return repository
                .findById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}
