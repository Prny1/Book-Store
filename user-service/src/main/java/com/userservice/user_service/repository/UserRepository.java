package com.userservice.user_service.repository;

import com.userservice.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity , Long> {
}
