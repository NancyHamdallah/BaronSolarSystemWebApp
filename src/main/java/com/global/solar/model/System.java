package com.global.solar.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="systemtable")
public class System{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String type;

	private Double size;

	public System(String type, Double size) {
		
		this.type = type;
		this.size = size;
	}

	@OneToMany(mappedBy = "systemtable")
	//@JoinColumn(name="fk_clientid",referencedColumnName="id")
	private List<Client> client;

	@OneToMany(mappedBy = "systemtable")
	private List<Production> production;


}