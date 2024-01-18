package com.epam.dao;

import com.epam.entities.Associates;

import com.epam.entities.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssociateRepo extends JpaRepository<Associates,Long> {
    Optional<List<Associates>> findAllByBatchBatchId(long batchId);
    Page<Associates> findAllByGender(Gender gender, Pageable page);
}
