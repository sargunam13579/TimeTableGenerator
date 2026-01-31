package com.yourpkg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yourpkg.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}