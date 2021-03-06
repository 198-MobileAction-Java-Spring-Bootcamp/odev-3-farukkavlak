package com.farukkavlak.loginsystem.Dao;

import com.farukkavlak.loginsystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//User repository
@Repository
public interface UserDao extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
