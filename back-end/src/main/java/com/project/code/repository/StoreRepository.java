package com.project.code.repository;

import com.project.code.domain.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {
    List<Store> findByNameContainingIgnoreCase(String name);
    Optional<Store> findById(Long id);
    boolean existsByNameIgnoreCase(String name);
}
