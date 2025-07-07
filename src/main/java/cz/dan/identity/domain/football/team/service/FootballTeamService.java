package cz.dan.identity.domain.football.team.service;

import cz.dan.identity.domain.football.team.controller.dto.SearchTeamDto;

import java.util.List;

public interface FootballTeamService {

    List<SearchTeamDto> search(String name);

}
