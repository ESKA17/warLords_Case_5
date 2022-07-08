package com.example.mycli.repository;

import com.example.mycli.entity.AuthData;
import com.example.mycli.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends JpaRepository<AuthData, Long> {

    UserEntity findByEmail(String login);

    void deleteById(Long id);

}
