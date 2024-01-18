package com.epam.dao;

import com.epam.entities.Batches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepo extends JpaRepository<Batches,Long> {
}
