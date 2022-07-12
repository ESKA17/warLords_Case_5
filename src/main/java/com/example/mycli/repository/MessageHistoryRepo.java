package com.example.mycli.repository;

import com.example.mycli.entity.MessageHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageHistoryRepo extends JpaRepository<MessageHistory, Long> {
    Optional<MessageHistory> findByFriendIDAndUserID(Long toWhom, Long from);

}
