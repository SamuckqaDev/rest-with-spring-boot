package br.com.matsutech.restwithspringbootjava.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.matsutech.restwithspringbootjava.repositories.PersonRepository;
import br.com.matsutech.restwithspringbootjava.services.PersonServices;
import br.com.matsutech.restwithspringbootjava.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

	MockPerson input;

	@InjectMocks
	private PersonServices service; // This person service to inject mocks inside it

	@Mock
	PersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		var entity = input.mockEntity((long) 1);

		entity.getIdPerson(1L);
		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findById(1L);
		System.out.println(result.toString());
		assertNotNull(result.getIdPerson());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));

		assertEquals("Address Test1", result.getAddress());
		assertEquals("First name Test1", result.getFirstName());
		assertEquals("Last name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testCreate() {
		var entity = input.mockEntity((long) 1);
		
		var persisted = entity;
		entity.setId(1L);
		
		var vo = input.mockVO(1);
		vo.setIdPerson(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getIdPerson());
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));

		assertEquals("Address Test1", result.getAddress());
		assertEquals("First name Test1", result.getFirstName());
		assertEquals("Last name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
		
		
		
	
	@Test
	void testPersonServices() {
		fail("Not yet implemented");
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}


	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateV2() {
		fail("Not yet implemented");
	}

}
