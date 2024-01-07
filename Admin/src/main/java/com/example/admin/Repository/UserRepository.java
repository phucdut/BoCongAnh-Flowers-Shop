package com.example.admin.Repository;

import com.example.admin.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndDeletedFalse(String username);

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAllByRole(String staff);

}
