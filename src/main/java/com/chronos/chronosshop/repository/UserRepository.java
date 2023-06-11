package com.chronos.chronosshop.repository;

import com.chronos.chronosshop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    @Query("Select u from Users u where u.email like ?1")
    Users getUserByEmail(String email);

    List<Users> findByEmail(String email);
    Optional<Users> findByEmailAndPasswordNotNull(String email);
    @Query("Select u from Users u where u.email like ?1 and u.password is not null")
    List<Users>  getByEmailAndPasswordNotNull(String email);

    @Query("select count(u) from Users u")
    Integer countUser();

    Optional<Users> findByUserIdAndPassword(String id,String password);
}
