package com.global.solar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.solar.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	@Query(value = "SELECT client.id, client.address, client.name, systemtable.size, systemtable.type, client.daily_production, client.monthly_production\r\n"
			+ "			FROM barondb.client\r\n"
			+ "			INNER JOIN barondb.systemtable where client.fk_systemid = systemtable.id\r\n"
			+ "			ORDER BY client.id ASC;", nativeQuery = true)
	List<Object[]> findAllClientsNames();
	
	@Query(value="SELECT * FROM barondb.client where client.name = ?",nativeQuery = true)
	Client findClientByName(String name);
	
	@Query(value="SELECT client.id FROM barondb.client where client.name = ?",nativeQuery = true)
	Integer findIdByName(String name);
	
	@Query(value="select production.date, production.quantity\r\n"
			+ "from barondb.client\r\n"
			+ "inner join barondb.production where production.fk_clientid = client.id and client.id = ?1 ORDER BY production.date Asc",nativeQuery= true)
	Object[] findAllProductionDetails(int id);
	
	@Query(value="select client.id,client.address,client.name,client.daily_production,client.monthly_production, systemtable.size , systemtable.type\r\n"
			+ "from barondb.client\r\n"
			+ "inner join barondb.systemtable where client.fk_systemid = systemtable.id and client.id = ?1 ",nativeQuery=true)
	Object[] findClientDetailsWithSystem(int id);

	
	@Query(value="select  production.date, production.quantity\r\n"
			+ "from barondb.client\r\n"
			+ "inner join barondb.production where production.fk_clientid = client.id and client.id = ?1 and month(date) =?2 ORDER BY production.date Asc",nativeQuery= true)
	Object[] findAllProductionDetailsByMonth(int id, int month);
	
	
	
	
	

}
