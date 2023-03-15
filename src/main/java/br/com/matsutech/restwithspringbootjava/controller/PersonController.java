package br.com.matsutech.restwithspringbootjava.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.matsutech.restwithspringbootjava.data.vo.v1.PersonVO;
import br.com.matsutech.restwithspringbootjava.services.PersonServices;
import br.com.matsutech.restwithspringbootjava.vo.v2.PersonVOV2;
import br.com.matsutech.restwithspringbootjava.util.MediaType;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public List<PersonVO> findAll() {
        return personServices.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public PersonVO findById(@PathVariable Long id) {
        return personServices.findById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    public PersonVO create(@RequestBody PersonVO person) {
        return personServices.create(person);
    }

    @PostMapping(value = "/v2", consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 personVOV2)
            throws InvocationTargetException, IllegalAccessException {
        return personServices.createV2(personVOV2);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    public PersonVO update(@RequestBody PersonVO person)
            throws InvocationTargetException, IllegalAccessException {
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
