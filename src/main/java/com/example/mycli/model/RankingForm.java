package com.example.mycli.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RankingForm {
    private Integer overallRanking;
    private Integer infoGivenAboutProgram;
    private Integer userAccessibility;
    private Integer userKnowledge;
    private Integer userCommunicationSkills;
    private Integer satisfactionWithProgram;
    private Integer goalsReachingLevel;
    private String comments;
}
