package com.example.micronaut;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//tag::test[]
@MicronautTest
class ClubRepositoryTest {

    @Test
    void testItWorks(ClubRepository clubRepository) { // <1>
        long clubCount = clubRepository.count();
        assertEquals(10, clubCount);
    }
}
//end::test[]