package com.example.micronaut;

import com.example.micronaut.clubs.ClubApi;
import com.example.micronaut.clubs.ClubDto;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//tag::test[]
@MicronautTest
@Sql({"classpath:create.sql", "classpath:data.sql"})
@Sql(scripts = "classpath:clean.sql", phase = Sql.Phase.AFTER_ALL)
class ClubControllerTest {

    @Inject
    ClubClient client; // <2>

    @Test
    void testItCanListClubs() {
        var clubs = client.list();

        assertFalse(clubs.isEmpty());
    }

    @Test
    void testItCanGetAClub() {
        var response = client.get(1L);
        assertEquals(200, response.code());

        ClubDto club = response.body();
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
        ClubDto club = ClubDto.of("CD Leganés", "Estadio Municipal de Butarque (Leganés, Spain)");
        var response = client.create(club);

        assertEquals(201, response.code());
        assertNotNull(response.header("Location"));
    }

    @Test
    void itCanUpdateAClub() {
        ClubDto club = createClub("CD Leganés", "Estadio Municipal de Butarque (Leganés, Spain)");
        ClubDto updated = club.withName("Club Deportivo Leganés");
        var response = client.update(updated.id(), updated);

        assertEquals(204, response.code());

        var getResponse = client.get(updated.id()).body();
        assertEquals("Club Deportivo Leganés", getResponse.name());
    }

    @Test
    void itCanDeleteAClub() {
        ClubDto club = createClub("Getafe CF", "Coliseum Alfonso Pérez (Getafe, Spain)");
        var response = client.delete(club.id());
        assertEquals(204, response.code());

        var getResponse = client.get(club.id());
        assertEquals(404, getResponse.code());
    }

    private ClubDto createClub(String name, String stadium) {
        ClubDto club = ClubDto.of(name, stadium);
        var createResponse = client.create(club);
        Long clubId = Long.valueOf(createResponse.header("Location").replace("/clubs/", ""));
        var getResponse = client.get(clubId);
        return getResponse.body();
    }

//tag::clazz[]

    @Client("/clubs") // <1>
    interface ClubClient extends ClubApi {}
}
//end::clazz[]