package cz.dan.identity.infra.kafka;

import cz.dan.avro.fetcher.outbox.PersonOutboxPayload;
import cz.dan.identity.domain.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PersonListener {

    private final PersonService personService;

    @KafkaListener(topics = "fetcher.person")
    public void handle(@Payload PersonOutboxPayload payload) {
        log.info("Received person payload for {} {}.", payload.getFirstName(), payload.getLastName());
        personService.save(payload);
        log.info("Person ({} {}) payload handled.", payload.getFirstName(), payload.getLastName());
    }

}
