package br.com.matsutech.restwithspringbootjava.repositories;


import br.com.matsutech.restwithspringbootjava.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long > {
}
