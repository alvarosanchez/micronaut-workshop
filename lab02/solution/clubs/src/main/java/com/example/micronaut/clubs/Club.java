package com.example.micronaut.clubs;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public record Club(@Id @GeneratedValue Long id,
                   String name,
                   @OneToOne(cascade = CascadeType.PERSIST) @JoinColumn Stadium stadium) {}
