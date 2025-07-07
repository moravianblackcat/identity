package cz.dan.identity.domain.person.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchPersonDto {

    private final long id;

    private final String firstName;

    private final String lastName;

    private final String nationality;

    private final LocalDate dateOfBirth;

    public SearchPersonDto(long id, String firstName, String lastName, String nationality, java.sql.Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth.toLocalDate();
    }

}
