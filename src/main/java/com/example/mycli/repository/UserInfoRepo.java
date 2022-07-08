package com.example.mycli.repository;

import com.example.mycli.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInformation, Long> {
    UserInformation findByEmail(String email);
    void deleteById(Long id);
}
