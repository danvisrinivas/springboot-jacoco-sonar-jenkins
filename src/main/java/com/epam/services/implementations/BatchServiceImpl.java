package com.epam.services.implementations;

import com.epam.dao.AssociateRepo;
import com.epam.dao.BatchRepo;
import com.epam.dtos.BatchRequest;
import com.epam.dtos.BatchResponse;
import com.epam.entities.Associates;
import com.epam.entities.Batches;
import com.epam.exception.AssociateException;
import com.epam.exception.BatchException;
import com.epam.services.BatchService;
import com.epam.util.BatchUtil;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class BatchServiceImpl implements BatchService {
    private final BatchRepo batchRepo;
    @Override
    public BatchResponse createBatch(BatchRequest batchRequest) {
        log.info("BatchServiceImpl:createBatch execution started.");
        Batches batches = BatchUtil.convertToEntity(batchRequest);
        BatchResponse batchResponse = BatchUtil.convertToBatchResponse(batchRepo.save(batches));
        log.debug("BatchServiceImpl:createBatch received response from database {}.",batchRequest);
        return batchResponse;
    }



}
