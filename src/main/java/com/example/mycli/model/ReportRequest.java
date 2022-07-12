package com.example.mycli.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class ReportRequest {
    @NotNull
    private String reason;
    @NotNull
    private String reporter;
    @NotNull
    private String ignore;
    @NotNull
    private String mentor;
    @NotNull
    private Long reportedPersonId;
    @NotNull
    private String reportedPerson;

}
