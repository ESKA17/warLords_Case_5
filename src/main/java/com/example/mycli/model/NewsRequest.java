package com.example.mycli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Setter
@Getter
public class NewsRequest {
    private Long id;
    private String text;
}
