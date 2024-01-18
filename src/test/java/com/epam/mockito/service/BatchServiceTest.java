package com.epam.mockito.service;

import com.epam.PrepareTestObjects;
import com.epam.dao.AssociateRepo;
import com.epam.dao.BatchRepo;
import com.epam.dtos.AssociateRequest;
import com.epam.dtos.AssociateResponse;
import com.epam.dtos.BatchRequest;
import com.epam.dtos.BatchResponse;
import com.epam.entities.Associates;
import com.epam.entities.Batches;
import com.epam.exception.AssociateException;
import com.epam.services.implementations.AssociateServiceImpl;
import com.epam.services.implementations.BatchServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class BatchServiceTest {
    @Mock
    BatchRepo batchRepo;


    @InjectMocks
    BatchServiceImpl batchService;


    @Test
    void createValidBatch(){
        BatchRequest batchRequest = PrepareTestObjects.getBatchRequest();
        BatchResponse batchResponse = PrepareTestObjects.getBatchResponse();
        Batches batch = PrepareTestObjects.getBatch();

        Mockito.when(batchRepo.save(any())).thenReturn(batch);

        BatchResponse result = batchService.createBatch(batchRequest);
        batchResponse.setBatchId(1l);
        batchResponse.setAssociateList(new ArrayList<>());

        assertEquals(result,batchResponse);
        Mockito.verify(batchRepo, times(1)).save(any());
    }


}
