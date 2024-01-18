package com.epam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Associates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long associateId;
    private String name;
    private String email;
    private String college;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn
    private Batches batch;
}
