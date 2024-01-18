package com.epam.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;





@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class APIResponse<T> {

    private String status;
    private T results;

}
