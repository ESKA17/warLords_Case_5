package com.example.mycli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReportRequest {
    private String reason;
    private String reporter;
    private String ignore;
    private String mentor;
    private Long reportedPersonId;
    private String reportedPerson;

}
