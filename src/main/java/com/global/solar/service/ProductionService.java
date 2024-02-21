package com.global.solar.service;

import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.solar.model.Production;
import com.global.solar.repository.ProductionRepository;

@Service
public class ProductionService {
	
	@Autowired
	ProductionRepository productionRepository;
	
	public Production findProductionByDateAndClientId(Date date1,int clientId) {
		return productionRepository.findProductionByDateAndClientId(date1, clientId);
	}
	
	public void saveProduction(Production production) {
		productionRepository.save(production);
	}

}
