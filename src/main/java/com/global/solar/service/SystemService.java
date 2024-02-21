package com.global.solar.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.solar.model.System;
import com.global.solar.repository.SystemRepository;

@Service
public class SystemService {

	@Autowired
	private SystemRepository systemRepository;

	public List<System> getAllSystems(){
		return systemRepository.findAll();
	}

	public Integer getSystemIdBySizeAndType(Double size,String type) {
		return systemRepository.findSystemIdBySizeAndType(size,type);
	}
	public void saveSystems(System system) {
		systemRepository.save(system);
	}

}
