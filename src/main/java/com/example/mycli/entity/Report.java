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
@Table(name = "report_table")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "mentor", referencedColumnName = "id")
    private UserEntity mentor;

    @OneToOne
    @JoinColumn(name = "mentee", referencedColumnName = "id")
    private UserEntity mentee;


    @Column(name = "reason")
    private String chat;s

    @OneToOne
    @JoinColumn(name = "reporter", referencedColumnName = "id")
    private UserEntity userEntity;

    @Column(name = "ignore")
    private Boolean ignore;

}
