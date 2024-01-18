package com.epam.services;

import com.epam.dtos.AssociateRequest;
import com.epam.dtos.AssociateResponse;

import org.springframework.data.domain.Page;

public interface AssociateService {


    public AssociateResponse createAssociate(AssociateRequest associateRequest);

    public Page<AssociateResponse> getAssociatesByGender(int pageNumber, int pageSize,String gender);

    public AssociateResponse getAssociateById(long associateId);

    public AssociateResponse updateAssociate(AssociateRequest associateRequest,long associateId);

    public void deleteAssociate(long associateId);
}
