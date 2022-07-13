package com.example.mycli.controllers;

import com.example.mycli.entity.UserEntity;
import com.example.mycli.model.FilterSearchRequest;
import com.example.mycli.services.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("filter_search")
public class FilterController {

    private final UserService userService;

    @GetMapping("/all")
    public List<Long> showAll() {
        return userService.findAllReturnID();
    }

    @GetMapping("/by_id")
    public UserEntity getUser(Long id) {
        return userService.findUserByID(id);
    }

    @GetMapping("/by_filter")
    public List<Long> filterSearch(@RequestBody FilterSearchRequest filterSearchRequest) {
        return userService.filter(filterSearchRequest);
    }
}
