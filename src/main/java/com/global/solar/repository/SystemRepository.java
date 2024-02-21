package com.global.solar.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.solar.model.System;

@Repository
public interface SystemRepository extends JpaRepository<System, Integer> {

	@Query(value = "Select systemtable.id from barondb.systemtable WHERE systemtable.size=?1 and systemtable.type=?2",nativeQuery = true)	
	Integer findSystemIdBySizeAndType(Double size,String type);

}
