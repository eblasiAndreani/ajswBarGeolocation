package com.ajsw.barGeolocation.repository.impl;

import com.ajsw.barGeolocation.domain.entity.BarEntity;
import com.ajsw.barGeolocation.repository.IBarRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BarRepository extends SimpleJpaRepository<BarEntity, Integer> implements IBarRepository {

    public BarRepository(EntityManager em) {
        super(BarEntity.class, em);
    }
}