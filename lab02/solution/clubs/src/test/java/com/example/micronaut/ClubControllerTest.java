package com.example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//tag::test[]
@MicronautTest
class ClubControllerTest {

    @Inject
    ClubClient client; // <2>

    @Test
    void testItCanListTeams() {
        Iterable<Club> clubs = client.list();

        assertEquals(10, ((Collection<Club>)clubs).size());
    }

    @Test
    void testItCanGetATeam() {
        HttpResponse<Club> response = client.get(1L);

        assertEquals(200, response.code());

        Club club = response.body();
        assertNotNull(club);
        assertEquals("Real Madrid", club.name());
    }

    @Client("/clubs") // <1>
    interface ClubClient extends ClubApi {}
}
//end::test[]