package com.example.mycli.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chats_table")
public class Chats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "mentor", referencedColumnName = "id")
    private UserEntity mentor;

    @OneToOne
    @JoinColumn(name = "mentee", referencedColumnName = "id")
    private UserEntity mentee;

    @Column(name = "chat")
    private String chat;


}
