package com.dexter.labs.digitalfarm.repository;

import com.dexter.labs.digitalfarm.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FieldRepository extends JpaRepository<Field, String>{}
