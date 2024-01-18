package com.epam.util;

import com.epam.dtos.AssociateRequest;
import com.epam.dtos.AssociateResponse;
import com.epam.entities.Associates;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AssociateUtil {
    private AssociateUtil(){}

    public static Associates convertToEntity(AssociateRequest associateRequest)
    {
        return Associates.builder()
                .name(associateRequest.getName())
                .college(associateRequest.getCollege())
                .email(associateRequest.getEmail())
                .status(associateRequest.getStatus())
                .gender(associateRequest.getGender())
                .build();
    }

    public static AssociateResponse convertToAssociateResponse(Associates associate)
    {
        AssociateResponse associateResponse =  AssociateResponse.builder()
                .associateId(associate.getAssociateId())
                .name(associate.getName())
                .college(associate.getCollege())
                .email(associate.getEmail())
                .status(associate.getStatus())
                .gender(associate.getGender())
                .build();
        if(associate.getBatch() != null)
        {
            associateResponse.setBatch(associate.getBatch().getBatchName().concat(" : ").concat(associate.getBatch().getPractice().toString()));
        }

        return associateResponse;
    }
}
