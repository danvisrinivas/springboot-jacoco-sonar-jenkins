package com.epam.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Batches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long batchId;
    private String batchName;
    @Enumerated(value = EnumType.STRING)
    private Practice practice;
    private Date startDate;
    private Date endDate;
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Associates> associatesList = new ArrayList<>();

}
