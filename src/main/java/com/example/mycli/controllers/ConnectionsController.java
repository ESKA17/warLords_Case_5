package com.example.mycli.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connections")
//@SecurityRequirement(name = "basicauth")

@CrossOrigin("http://localhost:3000")
public class ConnectionsController {
}
