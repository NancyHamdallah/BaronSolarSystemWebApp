package com.global.solar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.solar.model.Client;
import com.global.solar.repository.ClientRepository;
import com.global.solar.repository.UserOpenProjection;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;



	public List<Client> getAllClients() {
		return clientRepository.findAll();

	}
	
	public Client findClientByName(String name) {
		return clientRepository.findClientByName(name);
	}
	
	public Integer findIdByName(String name) {
		return clientRepository.findIdByName(name);
	}
	
	public void saveClient(Client client) {
		clientRepository.save(client);
	}
	
	public List<Object[]> findAllClientsNames(){
		return clientRepository.findAllClientsNames();
	}
	
	public Object[] findAllProductionDetails(int id){
		return clientRepository.findAllProductionDetails(id);
	}
	
	public Object[] findClientDetailsWithSystem(int id){
		return clientRepository.findClientDetailsWithSystem(id);
	}

	public Object[] findAllProductionDetailsByMonth(int id, int month) {
		
		return clientRepository.findAllProductionDetailsByMonth(id,month);
	}

}
