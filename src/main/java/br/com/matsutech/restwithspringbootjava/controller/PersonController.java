package br.com.matsutech.restwithspringbootjava.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.matsutech.restwithspringbootjava.data.vo.v1.PersonVO;
import br.com.matsutech.restwithspringbootjava.services.PersonServices;
import br.com.matsutech.restwithspringbootjava.vo.v2.PersonVOV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.matsutech.restwithspringbootjava.util.MediaType;

@CrossOrigin
@RestController
@RequestMapping("/person")
@Tag(name = "People", description = "Endpoint for people management")
public class PersonController {

    @Autowired
    private PersonServices personServices;

    @GetMapping(produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML})
    @Operation(summary = "Findds all people",
            description = "Findds all people",
            tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))
                    }),
                    @ApiResponse(responseCode = "400", content = @Content),
                    @ApiResponse(responseCode = "401", content = @Content),
                    @ApiResponse(responseCode = "404", content = @Content),
                    @ApiResponse(responseCode = "500", content = @Content)})
    public List<PersonVO> findAll() {
        return personServices.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Find people by id",
            description = "Adds a new person by passing in a JSON, XML or YML representation of people.",
            tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "204", content = @Content),
                    @ApiResponse(responseCode = "400", content = @Content),
                    @ApiResponse(responseCode = "401", content = @Content),
                    @ApiResponse(responseCode = "404", content = @Content),
                    @ApiResponse(responseCode = "500", content = @Content)})
    public PersonVO findById(@PathVariable Long id) {
        return personServices.findById(id);
    }

    @CrossOrigin(origins = {"http://localhost:8080", "http://matsutech.ti.com.br"})
    @PostMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Delete people by id",
            description = "Delete people by id",
            tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "201",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "400", content = @Content),
                    @ApiResponse(responseCode = "401", content = @Content),
                    @ApiResponse(responseCode = "500", content = @Content)})
    public PersonVO create(@RequestBody PersonVO person) {
        return personServices.create(person);
    }

    @PostMapping(value = "/v2",
            consumes = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 personVOV2)
            throws InvocationTargetException, IllegalAccessException {
        return personServices.createV2(personVOV2);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON,
                    MediaType.APPLICATION_XML,
                    MediaType.APPLICATION_YML})
    @Operation(summary = "Updates people by id",
            description = "Updates people by id",
            tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))),
                    @ApiResponse(responseCode = "400", content = @Content),
                    @ApiResponse(responseCode = "401", content = @Content),
                    @ApiResponse(responseCode = "404", content = @Content),
                    @ApiResponse(responseCode = "500", content = @Content)})
    public PersonVO update(@RequestBody PersonVO person)
            throws InvocationTargetException,
            IllegalAccessException {
        return personServices.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes people by id",
            description = "Deletes people by id",
            tags = {"People"},
            responses = {
                    @ApiResponse(responseCode = "200",
                            content = @Content),
                    @ApiResponse(responseCode = "204", content = @Content),
                    @ApiResponse(responseCode = "400", content = @Content),
                    @ApiResponse(responseCode = "401", content = @Content),
                    @ApiResponse(responseCode = "404", content = @Content),
                    @ApiResponse(responseCode = "500", content = @Content)})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
