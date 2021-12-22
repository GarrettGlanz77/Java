package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findAll();
}