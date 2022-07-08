package com.example.mycli.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Alumni {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alumni")
    @SequenceGenerator(name = "alumni", sequenceName = "alumni", allocationSize = 1)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "iin")
    private Integer iin;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "graduation_year")
    private Integer graduation;

    @Column(name = "bachelor")
    private String bachelor;

    @Column(name = "master")
    private String master;

}
