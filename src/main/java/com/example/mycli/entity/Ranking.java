package com.example.mycli.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "ranking_table")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ranking_en")
    @SequenceGenerator(name = "ranking_en", sequenceName = "ranking_en", allocationSize = 1)
    private Long id;
    @Column(name = "overal_ranking", nullable = false)
    private Integer overallRanking;
    @Column(name = "info_given_about_program")
    private Integer infoGivenAboutProgram;
    @Column(name = "user_accessibility")
    private Integer userAccessibility;
    @Column(name = "user_knowledge")
    private Integer userKnowledge;
    @Column(name = "user_communication_skills")
    private Integer userCommunicationSkills;
    @Column(name = "satisfaction_with_program")
    private Integer satisfactionWithProgram;
    @Column(name = "goals_reaching_level")
    private Integer goalsReachingLevel;
    @Column(name = "comments")
    private String comments;
}
