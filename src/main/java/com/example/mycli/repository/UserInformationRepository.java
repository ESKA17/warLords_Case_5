package com.example.mycli.repository;

import com.example.mycli.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {
    UserInformation findByEmail(String email);
    void deleteById(Long id);
}
