package com.epam.mockito.controller;


import com.epam.PrepareTestObjects;
import com.epam.dtos.BatchRequest;
import com.epam.dtos.BatchResponse;
import com.epam.exception.BatchException;
import com.epam.services.AssociateService;
import com.epam.services.BatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BatchControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BatchService batchService;

    @Test
    void validCreateBatch() throws Exception {
        BatchRequest batchRequest = PrepareTestObjects.getBatchRequest();
        BatchResponse batchResponse = PrepareTestObjects.getBatchResponse();
        Mockito.when(batchService.createBatch(any())).thenReturn(batchResponse);
        assertNotNull(batchResponse);
        mockMvc.perform(post("/rd/batches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(batchRequest)))
                .andExpect(status().isCreated());
        Mockito.verify(batchService).createBatch(any());
    }



}
