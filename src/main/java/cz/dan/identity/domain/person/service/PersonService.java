package cz.dan.identity.domain.person.service;

import cz.dan.avro.fetcher.outbox.PersonOutboxPayload;
import cz.dan.identity.domain.person.controller.dto.SearchPersonDto;

import java.util.List;

public interface PersonService {

    void save(PersonOutboxPayload payload);

    List<SearchPersonDto> search(String name);

}
