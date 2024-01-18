package com.epam.dtos;

import com.epam.entities.Gender;
import com.epam.entities.Status;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssociateResponse {
    private long associateId;
    private String name;
    private String email;
    private String college;
    private Gender gender;
    private Status status;
    private String batch;
}
