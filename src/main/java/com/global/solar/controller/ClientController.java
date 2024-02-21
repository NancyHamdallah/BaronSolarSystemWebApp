package com.global.solar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.global.solar.model.Client;
import com.global.solar.service.ClientService;



@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;
	private int clientId;

	@GetMapping("/clientProd")
	
	public String clientDetails(@RequestParam int id,Model model) {
		Object[] clientDetails = clientService.findClientDetailsWithSystem(id);
		Object[] productionDetails = clientService.findAllProductionDetails(id);
		model.addAttribute("clientdetails",clientDetails);
		model.addAttribute("productiondetails",productionDetails);
		this.clientId=id;
		
		return "clientdetails";
		
	}
	
@GetMapping("/clientprodmonth")
	
	public String clientDetailsMonthly(@RequestParam String month,Model model) {
		System.out.println(month);
		month = month.substring(5);
		System.out.println(month);
		int monthInt = Integer.parseInt(month);
		System.out.println(((Object)(this.clientId)).getClass().getSimpleName());
		System.out.println(this.clientId);
		Object[] clientDetails = clientService.findClientDetailsWithSystem(this.clientId);
		model.addAttribute("clientdetails",clientDetails);
		Object[] productionDetails = clientService.findAllProductionDetailsByMonth(this.clientId,monthInt);
		model.addAttribute("productiondetails",productionDetails);
		
		return "clientdetails";
		
	}
	
	
	@GetMapping("/getClients")
	public String getAllClients(Model model) {

		List<Client> myClients = clientService.getAllClients();
		model.addAttribute("clients",myClients);

		return "myclients";

	}
	

	@GetMapping("/newClient")
	public String addNewClient() {


		return "addnewclient";

	}

}
