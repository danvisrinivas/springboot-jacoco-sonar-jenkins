package com.epam.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;


@Builder
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    String time;
    String path;
    List<FieldError> fieldErrors;
}
