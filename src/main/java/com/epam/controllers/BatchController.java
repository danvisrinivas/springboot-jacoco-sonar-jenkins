package com.epam.controllers;

import com.epam.dtos.APIResponse;
import com.epam.dtos.BatchRequest;
import com.epam.dtos.BatchResponse;
import com.epam.services.BatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rd/batches")
@Slf4j
@RequiredArgsConstructor
public class BatchController {
    public static final String SUCCESS = "Success";
    private final BatchService batchService;

    @PostMapping
    public ResponseEntity<APIResponse<BatchResponse>> createBatch(@RequestBody @Valid BatchRequest batchRequest) {

        log.info("BatchController::createBatch invoked");

        BatchResponse associateResponse = batchService.createBatch(batchRequest);


        APIResponse<BatchResponse> responseDTO = APIResponse
                .<BatchResponse>builder()
                .status(SUCCESS)
                .results(associateResponse)
                .build();
        log.info("BatchController::createBatch response ");
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}
