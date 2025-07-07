package cz.dan.identity.domain.football.team.service;

import cz.dan.identity.domain.football.team.controller.dto.SearchTeamDto;
import cz.dan.identity.domain.football.team.entity.FootballTeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FootballTeamServiceImpl implements FootballTeamService {

    private final FootballTeamRepository footballTeamRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SearchTeamDto> search(String name) {
        return footballTeamRepository.search(name);
    }

}
