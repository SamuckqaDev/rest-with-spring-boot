package br.com.matsutech.restwithspringbootjava;


import br.com.matsutech.restwithspringbootjava.config.PasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestWithSpringBootJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWithSpringBootJavaApplication.class, args);

		System.out.println(new PasswordEncoder().passEncoder().encode("123"));
	}

}
