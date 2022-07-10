package com.example.mycli.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news_table")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private UserEntity userEntity;

    @Column(name = "news")
    private String news;

    @Column(name = "accepter" )
    private Long accepter_id;

    @Column(name = "accepted")
    private Boolean accepted;
}
