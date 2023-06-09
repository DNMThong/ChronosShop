package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    @Query("Select u from Users u where u.email like ?1")
    Users getUserByEmail(String email);

    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailAndPasswordNotNull(String email);

}
