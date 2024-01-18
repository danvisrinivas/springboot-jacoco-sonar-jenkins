package com.epam.services;

import com.epam.dtos.BatchRequest;
import com.epam.dtos.BatchResponse;


public interface BatchService {
    public BatchResponse createBatch(BatchRequest batchRequest);




}
