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
    void testItCanListClubs() {
        Iterable<Club> clubs = client.list();

        assertEquals(10, ((Collection<Club>)clubs).size());
    }

    @Test
    void testItCanGetAClub() {
        HttpResponse<Club> response = client.get(1L);

        assertEquals(200, response.code());

        Club club = response.body();
        assertNotNull(club);
        assertEquals("Real Madrid", club.name());
    }

    @Test
    void itReturnsNotFoundForUnknownClub() {
        assertEquals(404, client.get(100L).code());
    }
//end::test[]

    @Test
    void itCanCreateAClub() {
        Club club = new Club("CD Leganés", "Estadio Municipal de Butarque");
        HttpResponse<Void> response = client.create(club);


        assertEquals(201, response.code());
        assertNotNull(response.header("Location"));
    }

    @Test
    void itCanUpdateAClub() {
        Long clubId = createClub("CD Leganés", "Estadio Municipal de Butarque");

        Club club = new Club("Club Deportivo Leganés", "Estadio Municipal de Butarque");
        HttpResponse<Void> response = client.update(clubId, club);

        assertEquals(204, response.code());

        Club updated = client.get(clubId).body();
        assertEquals("Club Deportivo Leganés", updated.name());
    }

    @Test
    void itCanDeleteAClub() {
        Long clubId = createClub("Getafe CF", "Coliseum Alfonso Pérez");
        HttpResponse<Void> response = client.delete(clubId);

        assertEquals(204, response.code());

        HttpResponse<Club> getResponse = client.get(clubId);
        assertEquals(404, getResponse.code());
    }

    private Long createClub(String name, String stadium) {
        Club club = new Club(name, stadium);
        HttpResponse<Void> response = client.create(club);
        Long clubId = Long.valueOf(response.header("Location").replace("/clubs/", ""));
        return clubId;
    }

//tag::clazz[]

    @Client("/clubs") // <1>
    interface ClubClient extends ClubApi {}
}
//end::clazz[]