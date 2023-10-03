package com.example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

import java.net.URI;
import java.util.Optional;

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

    @Override
    public HttpResponse<Void> create(Club club) {
        Club saved = repository.save(club);
        return HttpResponse.created(URI.create("/clubs/" + saved.id()));
    }

    @Override
    public HttpResponse<Void> update(Long id, Club club) {
        Optional<Club> existing = repository.findById(id);
        if (existing.isPresent()) {
            Club updated = Club.of(id, club);
            repository.update(updated);
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }

    @Override
    public HttpResponse<Void> delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }
}
