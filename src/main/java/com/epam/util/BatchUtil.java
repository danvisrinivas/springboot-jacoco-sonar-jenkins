package com.epam.util;


import com.epam.dtos.BatchRequest;
import com.epam.dtos.BatchResponse;

import com.epam.entities.Batches;

import java.util.ArrayList;


public class BatchUtil {
    private BatchUtil(){}

    public static Batches convertToEntity(BatchRequest batchRequest)
    {
        return Batches.builder()
                .batchName(batchRequest.getBatchName())
                .practice(batchRequest.getPractice())
                .startDate(batchRequest.getStartDate())
                .endDate(batchRequest.getEndDate())
                .associatesList(new ArrayList<>())
                .build();


    }
    public static BatchResponse convertToBatchResponse(Batches batch){
        return BatchResponse.builder()
                .batchId(batch.getBatchId())
                .batchName(batch.getBatchName())
                .startDate(batch.getStartDate())
                .endDate(batch.getEndDate())
                .practice(batch.getPractice())
                .associateList(batch.getAssociatesList().stream()
                .map(AssociateUtil::convertToAssociateResponse).toList())
                .build();

    }


}
