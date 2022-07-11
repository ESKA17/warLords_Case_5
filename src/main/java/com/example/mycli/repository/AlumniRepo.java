package com.example.mycli.repository;

import com.example.mycli.entity.Alumni;
import com.example.mycli.entity.AuthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumniRepo extends JpaRepository<Alumni, Long> {



}
