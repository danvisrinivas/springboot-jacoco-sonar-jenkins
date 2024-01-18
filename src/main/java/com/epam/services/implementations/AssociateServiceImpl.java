package com.epam.services.implementations;

import com.epam.dao.AssociateRepo;
import com.epam.dao.BatchRepo;
import com.epam.dtos.AssociateRequest;
import com.epam.dtos.AssociateResponse;
import com.epam.entities.Associates;
import com.epam.entities.Batches;
import com.epam.entities.Gender;
import com.epam.exception.AssociateException;
import com.epam.exception.BatchException;
import com.epam.services.AssociateService;
import com.epam.util.AssociateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AssociateServiceImpl implements AssociateService {

    private final BatchRepo batchRepo;
    private final AssociateRepo associateRepo;


    @Override
    public AssociateResponse createAssociate(AssociateRequest associateRequest) {

        AssociateResponse associateResponse;
        try {
            log.info("AssociateServiceImpl:createAssociate execution started.");
            Batches batch = batchRepo.findById(associateRequest.getBatchId())
                    .orElseThrow(() -> new BatchException("Batch not found with id " + associateRequest.getBatchId()));
            Associates associate = AssociateUtil.convertToEntity(associateRequest);
            associate.setBatch(batch);
            associate = associateRepo.save(associate);
            associateResponse = AssociateUtil.convertToAssociateResponse(associate);
            log.debug("AssociateServiceImpl:createAssociate received response from database {}",associateResponse);
        }
        catch (Exception ex)
        {
            log.error("Exception occurred while persisting associate to database , Exception message {}", ex.getMessage());
            throw new AssociateException("Exception occurred while create a new associate: " + ex.getMessage());
        }
        return associateResponse;
    }

    @Override
    public Page<AssociateResponse> getAssociatesByGender(int pageNumber, int pageSize, String gender) {
        log.info("AssociateServiceImpl:getAssociates execution started.");
        Pageable page = PageRequest.of(pageNumber,pageSize);
        Page<Associates> associatePage = associateRepo.findAllByGender(Gender.valueOf(gender),page);
        List<AssociateResponse> associateResponses = associatePage.getContent().stream()
                .map(AssociateUtil::convertToAssociateResponse)
                .toList();
        log.debug("AssociateServiceImpl:getAssociates response from database {}.",associateResponses);

        return new PageImpl<>(associateResponses,associatePage.getPageable(),associatePage.getTotalElements());
    }

    @Override
    public AssociateResponse getAssociateById(long associateId) {
        log.info("AssociateServiceImpl:getAssociateById execution started.");
        AssociateResponse associateResponse;

        Associates associate = associateRepo.findById(associateId)
                .orElseThrow(() -> new AssociateException("Associate not found with id " + associateId));
        associateResponse = AssociateUtil.convertToAssociateResponse(associate);
        log.debug("AssociateServiceImpl:createAssociate received response from database {}",associateResponse);


        return associateResponse;
    }

    @Override
    public AssociateResponse updateAssociate(AssociateRequest associateRequest, long associateId) {
        log.info("AssociateServiceImpl:updateAssociate execution started.");
        AssociateResponse associateResponse;
        try {
            Associates oldAssociate = associateRepo.findById(associateId)
                    .orElseThrow(() -> new AssociateException("Associate not found with id " + associateId));
            Batches batch = batchRepo.findById(associateRequest.getBatchId())
                    .orElseThrow(() -> new BatchException("Batch not found with id " + associateRequest.getBatchId()));

            Associates updatedAssociate = AssociateUtil.convertToEntity(associateRequest);
            updatedAssociate.setAssociateId(oldAssociate.getAssociateId());
            updatedAssociate.setBatch(batch);

            associateResponse = AssociateUtil.convertToAssociateResponse(associateRepo.save(updatedAssociate));
            log.debug("AssociateServiceImpl:updateAssociate received response from database {}",associateResponse);
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
            throw new AssociateException(ex.getMessage());
        }
        return associateResponse;
    }

    @Override
    public void deleteAssociate(long associateId) {
        associateRepo.deleteById(associateId);
    }
}
