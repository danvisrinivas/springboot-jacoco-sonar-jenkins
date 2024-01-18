package com.epam.dtos;

import com.epam.entities.Gender;
import com.epam.entities.Status;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssociateRequest {

    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Email(message = "please provide proper email")
    private String email;
    @NotBlank(message = "Please provide the college name")
    private String college;
    @NotNull(message = "Please Provide the gender properly(MALE/FEMALE)")
    private Gender gender;
    @NotNull(message = "Please provide the status properly(ACTIVE/INACTIVE)")
    private Status status;
    private long batchId;
}
