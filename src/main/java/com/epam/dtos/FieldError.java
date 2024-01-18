package com.epam.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;



@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class FieldError {
    private String field;
    private String errorMessage;
}
