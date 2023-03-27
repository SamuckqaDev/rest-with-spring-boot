package br.com.matsutech.restwithspringbootjava.services;

import br.com.matsutech.restwithspringbootjava.controller.BookController;
import br.com.matsutech.restwithspringbootjava.data.vo.v1.BookVO;
import br.com.matsutech.restwithspringbootjava.exceptions.ResourceNotFoundEntityException;
import br.com.matsutech.restwithspringbootjava.mapper.Mapper;
import br.com.matsutech.restwithspringbootjava.model.Book;
import br.com.matsutech.restwithspringbootjava.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    private BookRepository repository;


    public List<BookVO> findAll() {
        logger.info("Finding all people!");

        var Books = Mapper.parseListObject(repository.findAll(), BookVO.class);
        Books.stream().forEach(Book -> Book.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookController.class)
                        .findById(Book.getId())).withSelfRel()));
        return Books;
    }

    public BookVO findById(Long id) {

        logger.info("Finding one Book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundEntityException("No records found for this ID!"));

        var vo = Mapper.parseObject(entity, BookVO.class);

        vo.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookController.class)
                        .findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO Book) {

        logger.info("Creating one Book!");
        var entity = Mapper.parseObject(Book, Book.class);
        var vo = Mapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookController.class)
                        .findById(vo.getId())).withSelfRel());

        return vo;
    }

    public BookVO update(BookVO Book) throws InvocationTargetException, IllegalAccessException {

        logger.info("Updating one Book!");

        var entity = repository.findById(Book.getId())
                .orElseThrow(() -> new ResourceNotFoundEntityException("No records found for this ID!"));

        BeanUtils.copyProperties(Book, entity);

        var vo = Mapper.parseObject(repository.save(entity), BookVO.class);

        vo.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(BookController.class)
                        .findById(vo.getId())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one Book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundEntityException("No records found for this ID!"));
        repository.delete(entity);
    }

  
}
