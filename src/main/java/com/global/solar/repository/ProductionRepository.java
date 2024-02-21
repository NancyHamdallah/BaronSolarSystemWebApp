package com.global.solar.repository;

import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.solar.model.Production;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Integer> {
	
	@Query(value="select * from barondb.production where production.date=? and production.fk_clientid=?",nativeQuery=true)
	public Production findProductionByDateAndClientId(Date date1,int clientId);

}
