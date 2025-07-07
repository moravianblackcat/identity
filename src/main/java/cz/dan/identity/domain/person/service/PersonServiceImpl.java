package cz.dan.identity.domain.person.service;

import cz.dan.avro.fetcher.outbox.PersonOutboxPayload;
import cz.dan.identity.domain.person.controller.dto.SearchPersonDto;
import cz.dan.identity.domain.person.entity.PersonRepository;
import cz.dan.identity.domain.person.entity.mapper.PersonMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final EntityManager entityManager;

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public void save(PersonOutboxPayload payload) {
        entityManager.persist(personMapper.from(payload));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SearchPersonDto> search(String name) {
        return personRepository.search(name);
    }

}
