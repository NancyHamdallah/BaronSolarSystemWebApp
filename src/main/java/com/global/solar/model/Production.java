package com.global.solar.model;



import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name="production")
public class Production {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	
	private Date date;
	private Double quantity;

	@ManyToOne
	@JoinColumn(name="fk_systemid",insertable = false,updatable = false)
	private System systemtable;
	private int fk_systemid;
	
	@ManyToOne
	@JoinColumn(name="fk_clientid",insertable=false,updatable = false)
	private Client client;
	private int fk_clientid;
	

}
