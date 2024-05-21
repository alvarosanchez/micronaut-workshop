package com.example.micronaut.clubs;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record ClubDto(Long id, String name, String stadium) {

    public ClubDto withName(String name) {
        return new ClubDto(id, name, stadium);
    }

    public static ClubDto of(String name, String stadium){
        return new ClubDto(null, name, stadium);

    }
    public static ClubDto of(Long id, ClubDto club) {
        return new ClubDto(id, club.name(), club.stadium());
    }
}
