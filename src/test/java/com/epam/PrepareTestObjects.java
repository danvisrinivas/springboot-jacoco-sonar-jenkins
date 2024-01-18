package com.epam;

import com.epam.dtos.AssociateRequest;
import com.epam.dtos.AssociateResponse;
import com.epam.dtos.BatchRequest;
import com.epam.dtos.BatchResponse;
import com.epam.entities.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrepareTestObjects {

    public static Associates getAssociate(){
        Associates associate = new Associates();
        associate.setName("osama khan");
        associate.setEmail("osama@gmail.com");
        associate.setStatus(Status.ACTIVE);
        associate.setGender(Gender.MALE);
        associate.setCollege("VIIT");
        associate.setBatch(getBatch());
        return associate;
    }
    public static AssociateRequest getAssociateRequest(){
        AssociateRequest associateRequest = new AssociateRequest();
        associateRequest.setName("osama khan");
        associateRequest.setEmail("osama@gmail.com");
        associateRequest.setStatus(Status.ACTIVE);
        associateRequest.setGender(Gender.MALE);
        associateRequest.setCollege("VIIT");
        associateRequest.setBatchId(1l);
        return associateRequest;
    };

    public static AssociateResponse getAssociateResponse(){
        AssociateResponse associateResponse = new AssociateResponse();
        associateResponse.setAssociateId(0l);
        associateResponse.setName("osama khan");
        associateResponse.setEmail("osama@gmail.com");
        associateResponse.setStatus(Status.ACTIVE);
        associateResponse.setGender(Gender.MALE);
        associateResponse.setCollege("VIIT");
        associateResponse.setBatch("RD-23 : DOTNET");
        return associateResponse;
    }

    public static Batches getBatch(){
        Batches batch = new Batches();
        batch.setBatchId(1l);
        batch.setBatchName("RD-23");
        batch.setAssociatesList(new ArrayList<>());
        batch.setPractice(Practice.DOTNET);
        batch.setStartDate(new Date());
        batch.setEndDate(new Date(2024, 05,26));
        return batch;
    }

    public static BatchRequest getBatchRequest(){
        BatchRequest batchRequest = new BatchRequest();
        batchRequest.setBatchName("RD-23");
        batchRequest.setPractice(Practice.DOTNET);
        batchRequest.setStartDate(new Date());
        batchRequest.setEndDate(new Date(2024, 05,26));
        return batchRequest;
    }

    public static BatchResponse getBatchResponse(){
        BatchResponse batchResponse = new BatchResponse();
        batchResponse.setBatchId(0l);
        batchResponse.setBatchName("RD-23");
        batchResponse.setAssociateList(List.of(getAssociateResponse()));
        batchResponse.setPractice(Practice.DOTNET);
        batchResponse.setStartDate(new Date());
        batchResponse.setEndDate(new Date(2024, 05,26));
        return batchResponse;
    }
}
