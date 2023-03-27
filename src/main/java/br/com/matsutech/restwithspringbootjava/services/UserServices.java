package br.com.matsutech.restwithspringbootjava.services;

import br.com.matsutech.restwithspringbootjava.controller.PersonController;
import br.com.matsutech.restwithspringbootjava.data.vo.v1.PersonVO;
import br.com.matsutech.restwithspringbootjava.exceptions.ResourceNotFoundEntityException;
import br.com.matsutech.restwithspringbootjava.mapper.Mapper;
import br.com.matsutech.restwithspringbootjava.model.Person;
import br.com.matsutech.restwithspringbootjava.repositories.PersonRepository;
import br.com.matsutech.restwithspringbootjava.vo.v2.PersonVOV2;
import br.com.matsutech.restwithspringbootjava.maperCustom.PersonMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;


    private final PersonMapper personMapper;

    public PersonServices() {
        personMapper = new PersonMapper();
    }


    public List<PersonVO> findAll() {
        logger.info("Finding all people!");

        var persons = Mapper.parseListObject(repository.findAll(), PersonVO.class);
        persons.stream().forEach(person -> person.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(PersonController.class)
                        .findById(person.getIdPerson())).withSelfRel()));
        return persons;
    }

    public PersonVO findById(Long id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundEntityException("No records found for this ID!"));

        var vo = Mapper.parseObject(entity, PersonVO.class);

        vo.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(PersonController.class)
                        .findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating one person!");
        var entity = Mapper.parseObject(person, Person.class);
        var vo = Mapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(PersonController.class)
                        .findById(vo.getIdPerson())).withSelfRel());

        return vo;
    }

    public PersonVO update(PersonVO person) throws InvocationTargetException, IllegalAccessException {

        logger.info("Updating one person!");

        var entity = repository.findById(person.getIdPerson())
                .orElseThrow(() -> new ResourceNotFoundEntityException("No records found for this ID!"));

        BeanUtils.copyProperties(person, entity);

        var vo = Mapper.parseObject(repository.save(entity), PersonVO.class);

        vo.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(PersonController.class)
                        .findById(vo.getIdPerson())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundEntityException("No records found for this ID!"));
        repository.delete(entity);
    }

    public PersonVOV2 createV2(PersonVOV2 personVOV2)
            throws InvocationTargetException, IllegalAccessException {
        logger.info("Creating one person!");

        var entity = personMapper.convertVoToEntity(personVOV2);
        var vo = personMapper.convertEntityToVo(repository.save(entity));

        return vo;
    }
}
