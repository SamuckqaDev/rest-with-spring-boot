package br.com.matsutech.restwithspringbootjava.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.matsutech.restwithspringbootjava.data.vo.v1.BookVO;
import br.com.matsutech.restwithspringbootjava.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.matsutech.restwithspringbootjava.util.MediaType;

@RestController
@RequestMapping("/book")
@Tag(name = "Book", description = "Endpoint for book management")
public class BookController {

	@Autowired
	private BookServices bookServices;

	@GetMapping(produces = { MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML, 
			MediaType.APPLICATION_YML })
	@Operation(summary = "Finds all book", 
	description = "Finds all book", 
	tags = { "Book" }, 
	responses = {
			@ApiResponse(responseCode = "200", content = {
					@Content(
							mediaType = "application/json",
							array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))
			 }),
			@ApiResponse(responseCode = "203", content = @Content),
			@ApiResponse(responseCode = "400", content = @Content),
			@ApiResponse(responseCode = "401", content = @Content),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content) })	
	public List<BookVO> findAll() {
		return bookServices.findAll();
	}

	@GetMapping(value = "/{id}", 
	produces = { MediaType.APPLICATION_JSON, 
		MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML })
	@Operation(summary = "Find book by id", 
	description = "Adds a new book by passing in a JSON, XML or YML representation of book.", 
	tags = { "Book" }, 
	responses = {
			@ApiResponse(responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class))),
			@ApiResponse(responseCode = "204", content = @Content),
			@ApiResponse(responseCode = "400", content = @Content),
			@ApiResponse(responseCode = "401", content = @Content),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content) })	
	public BookVO findById(@PathVariable Long id) {
		return bookServices.findById(id);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML }, 
			produces = { MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML })
	@Operation(summary = "Delete book by id", 
	description = "Delete book by id", 
	tags = { "Book" }, 
	responses = {
			@ApiResponse(responseCode = "201", 
					content = @Content(schema = @Schema(implementation = BookVO.class))),
			@ApiResponse(responseCode = "400", content = @Content),
			@ApiResponse(responseCode = "401", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content) })	
	public BookVO create(@RequestBody BookVO book) {
		return bookServices.create(book);
	}


	@PutMapping(consumes = { MediaType.APPLICATION_JSON, 
		MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML }, 
			produces = { MediaType.APPLICATION_JSON, 
				MediaType.APPLICATION_XML,
					MediaType.APPLICATION_YML })
	@Operation(summary = "Updates book by id", 
	description = "Updates book by id", 
	tags = { "Book" }, 
	responses = {
			@ApiResponse(responseCode = "200", 
					content = @Content(schema = @Schema(implementation = BookVO.class))),
			@ApiResponse(responseCode = "400", content = @Content),
			@ApiResponse(responseCode = "401", content = @Content),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content) })	
	public BookVO update(@RequestBody BookVO book) 
	throws InvocationTargetException, IllegalAccessException {
		return bookServices.update(book);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes Book by id", 
	description = "Deletes Book by id", 
	tags = { "Book" }, 
	responses = {
			@ApiResponse(responseCode = "200", 
					content = @Content),
			@ApiResponse(responseCode = "204", content = @Content),
			@ApiResponse(responseCode = "400", content = @Content),
			@ApiResponse(responseCode = "401", content = @Content),
			@ApiResponse(responseCode = "404", content = @Content),
			@ApiResponse(responseCode = "500", content = @Content) })	
	public ResponseEntity<?> delete(@PathVariable Long id) {
		bookServices.delete(id);
		return ResponseEntity.noContent().build();
	}
}
