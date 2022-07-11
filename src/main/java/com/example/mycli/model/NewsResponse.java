package com.example.mycli.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NewsResponse {
    private String fullName;
    private LocalDate date;
    private String news;
}
