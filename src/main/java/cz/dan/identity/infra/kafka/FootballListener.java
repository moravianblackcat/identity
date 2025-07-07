package cz.dan.identity.infra.kafka;

import cz.dan.avro.fetcher.outbox.FootballPlayerOutboxPayload;
import cz.dan.avro.fetcher.outbox.FootballTeamOutboxPayload;
import cz.dan.identity.domain.football.service.FootballService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FootballListener {

    private final FootballService footballService;

    @KafkaListener(topics = "fetcher.response.football.player")
    public void handle(@Payload FootballPlayerOutboxPayload payload) {
        log.info("Received football player payload for {} {}.", payload.getFirstName(), payload.getLastName());
        footballService.save(payload);
        log.info("Football player ({} {}) payload handled.", payload.getFirstName(), payload.getLastName());
    }

    @KafkaListener(topics = "fetcher.response.football.team")
    public void handle(@Payload FootballTeamOutboxPayload payload) {
        log.info("Received football team payload for team {}.", payload.getId());
        footballService.save(payload);
        log.info("Football team ({}) payload handled.", payload.getId());
    }

}
