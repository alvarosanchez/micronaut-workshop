package com.example.micronaut.clubs;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public record Stadium(@Id @GeneratedValue Long id, String name, String city, String country) {

    public static Stadium of(String name, String city, String country) {
        return new Stadium(null, name, city, country);
    }

    @Override
    public String toString() {
        return name + " (" + city + ", " + country + ")";
    }
}
