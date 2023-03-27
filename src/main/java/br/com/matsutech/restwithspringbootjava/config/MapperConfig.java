package br.com.matsutech.restwithspringbootjava.Config;

import br.com.matsutech.restwithspringbootjava.maperCustom.PersonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MapperConfig {
    @Bean
    public PersonMapper personMapper() {
        return new PersonMapper();
    }
}
