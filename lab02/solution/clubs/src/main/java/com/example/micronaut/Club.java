package com.example.micronaut;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Serdeable
public record Club(@Id @GeneratedValue Long id, String name, String stadium) {

    public Club(String name, String stadium) {
        this(null, name, stadium);
    }

    public static Club of(Long id, Club other) {
        return new Club(id, other.name(), other.stadium());
    }

}
