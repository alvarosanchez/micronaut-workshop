package com.example.micronaut.clubs;

import jakarta.inject.Singleton;

import java.util.Collection;
import java.util.Optional;

@Singleton
public class ClubService {

    private final ClubRepository repository;
    private final ClubMapper mapper;

    public ClubService(ClubRepository repository, ClubMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ClubDto save(ClubDto clubDto) {
        Club club = repository.save(mapper.toEntity(clubDto));
        return mapper.toDto(club);
    }

    public Optional<ClubDto> update(ClubDto clubDto) {
        return repository.findById(clubDto.id())
                .map(club -> new Club(clubDto.id(), clubDto.name(), club.stadium()))
                .map(repository::update)
                .map(mapper::toDto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<ClubDto> findById(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }
    public Collection<ClubDto> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}
