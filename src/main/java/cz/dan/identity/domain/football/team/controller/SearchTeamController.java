package cz.dan.identity.domain.football.team.controller;

import cz.dan.identity.domain.football.team.controller.dto.SearchTeamDto;
import cz.dan.identity.domain.football.team.service.FootballTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
public class SearchTeamController {

    private final FootballTeamService footballTeamService;

    @GetMapping
    public List<SearchTeamDto> search(@RequestParam("name") String name) {
        return footballTeamService.search(name);
    }

}
