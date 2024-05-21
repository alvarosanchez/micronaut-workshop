package com.example.micronaut.clubs;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;

import java.net.URI;
import java.util.Collection;

@Controller("/clubs")
public class ClubController implements ClubApi {

    private final ClubService service;

    public ClubController(ClubService service) {
        this.service = service;
    }


    @Override
    public Collection<ClubDto> list() {
        return service.findAll();
    }

    @Override
    public HttpResponse<ClubDto> get(Long id) {
        return service
                .findById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Override
    public HttpResponse<Void> create(ClubDto club) {
        ClubDto saved = service.save(club);
        return HttpResponse.created(URI.create("/clubs/" + saved.id()));
    }

    @Override
    public HttpResponse<Void> update(Long id, ClubDto club) {
        ClubDto toBeUpdated = ClubDto.of(id, club);
        var result = service.update(toBeUpdated);
        return result.map(r -> HttpResponse.<Void>noContent()).orElse(HttpResponse.notFound());
    }

    @Override
    public HttpResponse<Void> delete(Long id) {
        if (service.existsById(id)) {
            service.delete(id);
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }
}
