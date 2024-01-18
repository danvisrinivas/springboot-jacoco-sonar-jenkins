package com.epam.dtos;

import com.epam.entities.Practice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchResponse {

    private long batchId;
    private String batchName;
    private Practice practice;
    private Date startDate;
    private Date endDate;
    private List<AssociateResponse> associateList = new ArrayList<>();
}
