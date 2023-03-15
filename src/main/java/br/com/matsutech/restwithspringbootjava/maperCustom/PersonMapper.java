package br.com.matsutech.restwithspringbootjava.maperCustom;

import br.com.matsutech.restwithspringbootjava.model.Person;
import br.com.matsutech.restwithspringbootjava.vo.v2.PersonVOV2;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class PersonMapper {

    public PersonVOV2 convertEntityToVo(Person person)
            throws IllegalAccessException, InvocationTargetException {
        var vo = new PersonVOV2();
        vo.setBirthDay(new Date());
        BeanUtils.copyProperties( vo, person);
        return vo;
    }

    public Person convertVoToEntity(PersonVOV2 personVOV2)
            throws IllegalAccessException, InvocationTargetException{
        var entity = new Person();
        BeanUtils.copyProperties( entity, personVOV2);
        return entity;
    }

}
