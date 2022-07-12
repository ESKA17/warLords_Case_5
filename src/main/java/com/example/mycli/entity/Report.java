package com.example.mycli.entity;

import lombok.*;
import net.bytebuddy.ByteBuddy;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report_table")
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reports_en")
    @SequenceGenerator(name = "reports_en", sequenceName = "reports_en", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reporter", referencedColumnName = "id")
    private UserEntity reporter;

    @OneToOne
    @JoinColumn(name = "harasser", referencedColumnName = "id")
    private UserEntity harasser;



    @Column(name = "reason")
    private String reason;


    @Column(name = "ignore")
    private Boolean ignore;


}
