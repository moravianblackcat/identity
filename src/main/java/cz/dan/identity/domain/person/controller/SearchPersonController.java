package cz.dan.identity.domain.person.controller;

import cz.dan.identity.domain.person.controller.dto.SearchPersonDto;
import cz.dan.identity.domain.person.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@RequiredArgsConstructor
public class SearchPersonController {

    private final PersonService personService;

    @GetMapping
    public List<SearchPersonDto> search(@RequestParam("name") String name) {
        return personService.search(name);
    }
}
