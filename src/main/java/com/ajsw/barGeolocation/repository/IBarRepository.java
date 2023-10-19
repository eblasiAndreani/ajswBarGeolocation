package com.ajsw.barGeolocation.repository;

import com.ajsw.barGeolocation.domain.entity.BarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface IBarRepository extends JpaRepository<BarEntity, Integer> {

}