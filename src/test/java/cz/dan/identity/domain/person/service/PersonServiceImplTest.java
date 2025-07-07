package cz.dan.identity.domain.person.service;

import cz.dan.avro.fetcher.outbox.PersonOutboxPayload;
import cz.dan.identity.domain.person.entity.Person;
import cz.dan.identity.domain.person.entity.mapper.PersonMapper;
import cz.dan.identity.domain.person.entity.mapper.PersonMapperImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Spy
    private final PersonMapper personMapper = new PersonMapperImpl();

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private PersonServiceImpl sut;

    @Captor
    private ArgumentCaptor<Person> personCaptor;

    @Test
    void savesPersonPayload() {
        PersonOutboxPayload payload = PersonOutboxPayload.newBuilder()
                .setId(1L)
                .setFirstName("Raymond")
                .setLastName("Kopa")
                .setName("Raymond Kopaszewski")
                .setDisplayName("Raymond Kopa")
                .setNationality("FRA")
                .setDateOfBirth(LocalDate.of(1931, 10, 13))
                .build();

        sut.save(payload);

        verify(entityManager, times(1)).persist(personCaptor.capture());
        assertThat(personCaptor.getValue())
                .isNotNull()
                .extracting(
                        Person::getId,
                        Person::getFirstName,
                        Person::getLastName,
                        Person::getName,
                        Person::getDisplayName,
                        Person::getNationality,
                        Person::getDateOfBirth
                )
                .containsExactly(
                        1L,
                        "Raymond",
                        "Kopa",
                        "Raymond Kopaszewski",
                        "Raymond Kopa",
                        "FRA",
                        LocalDate.of(1931, 10, 13)
                );
    }

}