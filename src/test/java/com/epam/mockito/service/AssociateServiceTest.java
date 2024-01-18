package com.epam.mockito.service;


import com.epam.PrepareTestObjects;
import com.epam.dao.AssociateRepo;
import com.epam.dao.BatchRepo;
import com.epam.dtos.AssociateRequest;
import com.epam.dtos.AssociateResponse;
import com.epam.entities.Associates;
import com.epam.entities.Batches;
import com.epam.entities.Gender;
import com.epam.entities.Status;
import com.epam.exception.AssociateException;
import com.epam.services.implementations.AssociateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AssociateServiceTest {

    @Mock
    BatchRepo batchRepo;
    @Mock
    AssociateRepo associateRepo;

    @InjectMocks
    AssociateServiceImpl associateService;


    @Test
    void createValidAssociate(){
        AssociateRequest associateRequest = PrepareTestObjects.getAssociateRequest();
        AssociateResponse associateResponse = PrepareTestObjects.getAssociateResponse();
        Associates associates = PrepareTestObjects.getAssociate();
        Batches batch = PrepareTestObjects.getBatch();

        Mockito.when(batchRepo.findById(1l)).thenReturn(Optional.of(batch));
        Mockito.when(associateRepo.save(any())).thenReturn(associates);
        AssociateResponse response = associateService.createAssociate(associateRequest);

        assertEquals(response,associateResponse);

        Mockito.verify(batchRepo, times(1)).findById(associateRequest.getBatchId());
        Mockito.verify(associateRepo, times(1)).save(any(Associates.class));
    }

    @Test
    void runtimeExceptionTest(){
        AssociateRequest associateRequest = PrepareTestObjects.getAssociateRequest();
        AssociateResponse associateResponse = PrepareTestObjects.getAssociateResponse();

        Associates associates = PrepareTestObjects.getAssociate();
        Batches batch = PrepareTestObjects.getBatch();

        Mockito.when(batchRepo.findById(1l)).thenReturn(Optional.of(batch));
        Mockito.when(associateRepo.save(any())).thenReturn(associates);
        AssociateResponse response = associateService.createAssociate(associateRequest);

        assertEquals(response,associateResponse);

        Mockito.verify(batchRepo, times(1)).findById(associateRequest.getBatchId());
        Mockito.verify(associateRepo, times(1)).save(any(Associates.class));
    }




    @Test
    void invalidAssociateCreation(){
        AssociateRequest associateRequest = PrepareTestObjects.getAssociateRequest();
        Mockito.when(batchRepo.findById(1l)).thenReturn(Optional.empty());

        assertThrows(AssociateException.class,() -> associateService.createAssociate(associateRequest));
        Mockito.verify(batchRepo, times(1)).findById(associateRequest.getBatchId());
        Mockito.verifyNoInteractions(associateRepo);
    }

    @Test
    void getAllMaleAssociates(){
        List<AssociateResponse> maleAssociatesResponse = List.of(PrepareTestObjects.getAssociateResponse());
        List<Associates> maleAssociates = List.of(PrepareTestObjects.getAssociate());
        int pageNumber = 1;
        int pageSize = 5;
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<Associates> associatePage = new PageImpl<>(maleAssociates, pageRequest, maleAssociates.size());
        Page<AssociateResponse> associateResponsePage = new PageImpl<>(maleAssociatesResponse, pageRequest, 1l);

        Mockito.when(associateRepo.findAllByGender(Gender.MALE, pageRequest)).thenReturn(associatePage);

        Page<AssociateResponse> resultPage = associateService.getAssociatesByGender(pageNumber, pageSize, "MALE");

        assertEquals(resultPage,associateResponsePage);
    }



    @Test
    void testGetAssociateById() {

        long associateId = 1l;

        Associates associate = PrepareTestObjects.getAssociate();
        AssociateResponse associateResponse = PrepareTestObjects.getAssociateResponse();
        Mockito.when(associateRepo.findById(associateId)).thenReturn(Optional.of(associate));
        AssociateResponse result = associateService.getAssociateById(associateId);
        assertEquals(associateResponse,result);
        Mockito.verify(associateRepo, times(1)).findById(associateId);
    }

    @Test
    void testGetAssociateByIdNotFound() {
        long associateId = 100l;
        Mockito.when(associateRepo.findById(associateId)).thenReturn(Optional.empty());
        assertThrows(AssociateException.class, () -> associateService.getAssociateById(associateId));
        Mockito.verify(associateRepo, times(1)).findById(associateId);
    }



    @Test
    void validUpdateAssociate(){
        long associateId = 1l;
        AssociateRequest associateRequest = PrepareTestObjects.getAssociateRequest();
        associateRequest.setName("Update Name");
        AssociateResponse associateResponse = PrepareTestObjects.getAssociateResponse();
        Associates associates = PrepareTestObjects.getAssociate();
        Batches batch = PrepareTestObjects.getBatch();

        Mockito.when(associateRepo.findById(associateId)).thenReturn(Optional.of(associates));
        Mockito.when(batchRepo.findById(batch.getBatchId())).thenReturn(Optional.of(batch));
        Mockito.when(associateRepo.save(any())).thenReturn(associates);

        AssociateResponse result  = associateService.updateAssociate(associateRequest,associateId);
        assertEquals(result,associateResponse);

        Mockito.verify(associateRepo, times(1)).findById(associateId);
        Mockito.verify(associateRepo, times(1)).save(any());
        Mockito.verify(batchRepo, times(1)).findById(associateRequest.getBatchId());

    }

    @Test
    void invalidUpdateAssociate(){
        long associateId = 1l;
        AssociateRequest associateRequest = PrepareTestObjects.getAssociateRequest();
        associateRequest.setName("Update Name");
        AssociateResponse associateResponse = PrepareTestObjects.getAssociateResponse();
        Associates associates = PrepareTestObjects.getAssociate();
        Batches batch = PrepareTestObjects.getBatch();
        batch.setBatchId(2000l);

        Mockito.when(associateRepo.findById(associateId)).thenReturn(Optional.of(associates));
        Mockito.when(batchRepo.findById(batch.getBatchId())).thenReturn(Optional.empty());


        assertThrows(AssociateException.class,() -> associateService.updateAssociate(associateRequest,associateId));

        Mockito.verify(associateRepo, times(1)).findById(associateId);
        Mockito.verify(batchRepo, times(1)).findById(associateRequest.getBatchId());

    }

    @Test
    void deleteTest(){
        long associateId = 1l;

        Mockito.doNothing().when(associateRepo).deleteById(associateId);
        assertDoesNotThrow(() -> associateService.deleteAssociate(associateId));
    }

}
