package com.example.micronaut.clubs;

import io.micronaut.context.annotation.Mapper.Mapping;
import jakarta.inject.Singleton;

//tag::mapper[]
@Singleton
public interface ClubMapper {

    @Mapping(to = "stadium", from = "#{ club.stadium().toString() }") // <1>
    ClubDto toDto(Club club);

    @Mapping(to = "stadium", from = "#{ this.getStadium(club) }") // <2>
    Club toEntity(ClubDto club);

    default Stadium getStadium(ClubDto clubDto) { // <3>
        String[] parts = clubDto.stadium().split(" \\(");
        String[] location = parts[1].replace(")", "").split(", ");
        return Stadium.of(parts[0], location[0], location[1]); // <4>
    }
}
//end::mapper[]