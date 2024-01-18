package com.epam.dtos;

import com.epam.entities.Practice;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;


@Data
public class BatchRequest {

    @NotBlank(message = "Name cannot be empty")
    private String batchName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private Date endDate;
    @NotNull(message = "Please provide the status properly(ACTIVE/INACTIVE)")
    private Practice practice;
}
