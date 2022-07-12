package com.example.mycli.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class MessageHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mes_en")
    @SequenceGenerator(name = "mes_en", sequenceName = "mes_en", allocationSize = 1)
    private Long id;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "friend_id")
    private Long friendID;
    @Column(name = "text")
    private String text;
}
