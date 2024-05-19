package com.example.micronaut;

import io.micronaut.test.annotation.Sql;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//tag::test[]
@MicronautTest
@Sql({"classpath:create.sql", "classpath:data.sql"}) // <1>
@Sql(scripts = "classpath:clean.sql", phase = Sql.Phase.AFTER_ALL)
class ClubRepositoryTest {

    @Test
    void testItWorks(ClubRepository clubRepository) { // <2>
        long clubCount = clubRepository.count();
        assertEquals(10, clubCount);
    }
}
//end::test[]