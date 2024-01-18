package com.epam.controllers;

import com.epam.dtos.APIResponse;
import com.epam.dtos.AssociateRequest;
import com.epam.dtos.AssociateResponse;
import com.epam.entities.Gender;
import com.epam.services.AssociateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rd/associates")
@Slf4j
@RequiredArgsConstructor
public class AssociateController {

    public static final String SUCCESS = "Success";
    private final AssociateService associateService;


    @PostMapping
    public ResponseEntity<APIResponse<AssociateResponse>> createAssociate(@RequestBody @Valid AssociateRequest associateRequest)
    {
        log.info("AssociateController::createAssociate invoked");
        AssociateResponse associateResponse = associateService.createAssociate(associateRequest);
        APIResponse<AssociateResponse> responseDTO = APIResponse
                .<AssociateResponse>builder()
                .status(SUCCESS)
                .results(associateResponse)
                .build();
        log.info("AssociateController::createAssociate response {}",responseDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("{gender}")
    public ResponseEntity<APIResponse<Page<AssociateResponse>>> getAllAssociates(@RequestParam int pageNumber, @RequestParam int pageSize,@PathVariable String gender)
    {
        log.info("AssociateController::getAllAssociates invoked");
        Page<AssociateResponse> associateResponses = associateService.getAssociatesByGender(pageNumber-1,pageSize,gender);
        APIResponse<Page<AssociateResponse>> responseDTO = APIResponse
                .<Page<AssociateResponse>>builder()
                .status(SUCCESS)
                .results(associateResponses)
                .build();
        log.info("AssociateController::getAllAssociates response {}",responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("get-associate/{associateId}")
    public ResponseEntity<APIResponse<AssociateResponse>> getAssociateById(@PathVariable long associateId)
    {
        log.info("AssociateController::getAssociateById invoked");
        AssociateResponse associateResponse = associateService.getAssociateById(associateId);
        APIResponse<AssociateResponse> responseDTO = APIResponse
                .<AssociateResponse>builder()
                .status(SUCCESS)
                .results(associateResponse)
                .build();
        log.info("AssociateController::getAssociateById response {}",responseDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("{associateId}")
    public ResponseEntity<APIResponse<AssociateResponse>> updateAssociate(@RequestBody @Valid AssociateRequest associateRequest,@PathVariable long associateId)
    {
        log.info("AssociateController::updateAssociate invoked");
        AssociateResponse associateResponse = associateService.updateAssociate(associateRequest,associateId);
        APIResponse<AssociateResponse> responseDTO = APIResponse
                .<AssociateResponse>builder()
                .status(SUCCESS)
                .results(associateResponse)
                .build();
        log.info("AssociateController::updateAssociate response {}",responseDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("{associateId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteBatch(@PathVariable int associateId) {
        associateService.deleteAssociate(associateId);
    }



}
