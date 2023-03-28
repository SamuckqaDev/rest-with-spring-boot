package br.com.matsutech.restwithspringbootjava.repositories;

import br.com.matsutech.restwithspringbootjava.model.Person;
import br.com.matsutech.restwithspringbootjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //JPQL


    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    User findByUsername(@Param("userName") String username);


}


