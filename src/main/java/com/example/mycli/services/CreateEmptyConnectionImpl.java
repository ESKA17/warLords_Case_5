package com.example.mycli.services;

import com.example.mycli.entity.Connection;
import com.example.mycli.entity.UserEntity;
import com.example.mycli.repository.ConnectionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class CreateEmptyConnectionImpl implements CreateEmptyConnection{
    private final EmitterService emitterService;
    private final ConnectionRepo connectionRepo;
    @Override
    public void createConnection() {
        Connection connection = Connection.builder()
                .userID(8L)
                .friendID(3L)
                .sseEmitter(emitterService.addEmitter())
                .connectionStatus(2)
                .messageHistory("")
                .build();
        connectionRepo.save(connection);
    }
}
