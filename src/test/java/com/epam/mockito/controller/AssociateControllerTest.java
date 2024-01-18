package com.epam.mockito.controller;


import com.epam.PrepareTestObjects;
import com.epam.controllers.AssociateController;
import com.epam.dtos.APIResponse;
import com.epam.dtos.AssociateRequest;
import com.epam.dtos.AssociateResponse;

import com.epam.entities.Gender;
import com.epam.entities.Status;
import com.epam.exception.AssociateException;
import com.epam.exception.BatchException;
import com.epam.services.AssociateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;

import static com.epam.controllers.AssociateController.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AssociateControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    AssociateService associateService;


    @Test
    void validCreateAssociate() throws Exception {
        AssociateResponse associateResponse = PrepareTestObjects.getAssociateResponse();
        AssociateRequest associateRequest = PrepareTestObjects.getAssociateRequest();
        Mockito.when(associateService.createAssociate(any())).thenReturn(associateResponse);
        mockMvc.perform(post("/rd/associates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(associateRequest)))
                .andExpect(status().isCreated());
        Mockito.verify(associateService).createAssociate(any());
    }

    @Test
    public void testGetAllAssociates() throws Exception {
        // Test data
        int pageNumber = 1;
        int pageSize = 10;
        String gender = "M";
        Page<AssociateResponse> associateResponsePage = createMockAssociateResponsePage();

        Mockito.when(associateService.getAssociatesByGender(pageNumber - 1, pageSize, gender)).thenReturn(associateResponsePage);

        // Perform the GET request
        MvcResult mvcResult = mockMvc.perform(get("/rd/associates/{gender}", gender)
                        .param("pageNumber", String.valueOf(pageNumber))
                        .param("pageSize", String.valueOf(pageSize))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andReturn();


        String responseBody = mvcResult.getResponse().getContentAsString();

    }



    @Test
    void handleMethodArgumentExceptionTest() throws Exception {

        AssociateRequest associateRequest = new AssociateRequest();
        associateRequest.setEmail("osama@gmail.com");
        associateRequest.setStatus(Status.ACTIVE);
        associateRequest.setGender(Gender.MALE);
        associateRequest.setCollege("VIIT");
        associateRequest.setBatchId(1l);
        mockMvc.perform(post("/rd/associates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(associateRequest)))
                .andExpect(status().isBadRequest());

    }




    @Test
    void getAssociateById() throws Exception {

        AssociateResponse associateResponse = PrepareTestObjects.getAssociateResponse();

        Mockito.when(associateService.getAssociateById(1L)).thenReturn(associateResponse);
        mockMvc.perform(get("/rd/associates/get-associate/1"))
                .andExpect(status().isOk());
        Mockito.verify(associateService).getAssociateById(1L);
    }

    @Test
    void validUpdateAssociate() throws Exception {
        AssociateResponse associateResponse = PrepareTestObjects.getAssociateResponse();
        AssociateRequest associateRequest = PrepareTestObjects.getAssociateRequest();
        long associateId = 1L;
        Mockito.when(associateService.updateAssociate(associateRequest,associateId)).thenReturn(associateResponse);

        mockMvc.perform(put("/rd/associates/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(associateRequest)))
                .andExpect(status().isOk());
        Mockito.verify(associateService).updateAssociate(associateRequest,associateId);
    }

    @Test
    void associateExceptionTest() throws Exception {
        AssociateResponse associateResponse = PrepareTestObjects.getAssociateResponse();
        AssociateRequest associateRequest = PrepareTestObjects.getAssociateRequest();
        long associateId = 1000L;
        Mockito.when(associateService.updateAssociate(associateRequest,associateId)).thenThrow(AssociateException.class);

        mockMvc.perform(put("/rd/associates/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(associateRequest)))
                .andExpect(status().isOk());
        Mockito.verify(associateService).updateAssociate(associateRequest,associateId);
    }




    @Test
    void testDeleteBatch() throws Exception{
        Mockito.doNothing().when(associateService).deleteAssociate(any(Long.class));
        mockMvc.perform(delete("/rd/associates/1"))
                .andExpect(status().isNoContent());
        Mockito.verify(associateService).deleteAssociate(any(Long.class));
    }

    private Page<AssociateResponse> createMockAssociateResponsePage() {
        Page<AssociateResponse> mockPage = mock(Page.class);
        return mockPage;
    }

}





