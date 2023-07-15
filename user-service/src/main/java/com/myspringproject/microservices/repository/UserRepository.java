package com.myspringproject.microservices.repository;

import com.myspringproject.microservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

