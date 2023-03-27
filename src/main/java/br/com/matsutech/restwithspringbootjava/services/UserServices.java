package br.com.matsutech.restwithspringbootjava.services;

import br.com.matsutech.restwithspringbootjava.controller.PersonController;
import br.com.matsutech.restwithspringbootjava.data.vo.v1.PersonVO;
import br.com.matsutech.restwithspringbootjava.exceptions.ResourceNotFoundEntityException;
import br.com.matsutech.restwithspringbootjava.maperCustom.PersonMapper;
import br.com.matsutech.restwithspringbootjava.mapper.Mapper;
import br.com.matsutech.restwithspringbootjava.model.Person;
import br.com.matsutech.restwithspringbootjava.repositories.PersonRepository;
import br.com.matsutech.restwithspringbootjava.repositories.UserRepository;
import br.com.matsutech.restwithspringbootjava.vo.v2.PersonVOV2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServices implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    private UserRepository repository;

    private final PersonMapper personMapper;

    public UserServices() {
        personMapper = new PersonMapper();
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        logger.info("Finding one user By name!");

        var user = repository.findByUsername(username);
        if(user != null){
           return user;
        }else{
            throw new UsernameNotFoundException("Username "+ username + " not found!");
        }
    }
}
