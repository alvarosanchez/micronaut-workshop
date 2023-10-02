package com.example.micronaut;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Serdeable
public record Club(@Id @GeneratedValue Long id, String name, String stadium) { }
