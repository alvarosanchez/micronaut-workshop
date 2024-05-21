package com.example.micronaut;

import com.example.micronaut.clubs.ClubRepository;
import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

//tag::test[]
@MicronautTest
@Sql({"classpath:create.sql", "classpath:data.sql"}) // <1>
@Sql(scripts = "classpath:clean.sql", phase = Sql.Phase.AFTER_ALL)
class ClubRepositoryTest {

    @Test
    void testItWorks(ClubRepository clubRepository) { // <2>
        long clubCount = clubRepository.count();
        assertTrue(clubCount > 0);
    }
}
//end::test[]