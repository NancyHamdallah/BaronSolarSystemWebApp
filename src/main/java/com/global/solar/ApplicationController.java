package com.global.solar;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.global.solar.model.Client;
import com.global.solar.model.Production;
import com.global.solar.repository.UserOpenProjection;
import com.global.solar.service.ClientService;
import com.global.solar.service.ProductionService;
import com.global.solar.service.SystemService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

@Controller
public class ApplicationController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private ProductionService productionService;

	@GetMapping("/login")
	public String LoginPage() {

		return "login";
	}
	@GetMapping("/logout")
	public String LogoutPage() {

		return "mainpage";
	}

	@PostMapping("/login")
	public String SuccessLogin() {

		return "redirect:/index";
	}
	@GetMapping("/main")
	public String goMainPage() {
		return "mainpage";
	}
	
	@GetMapping("/index")
	public String goHome(Model model) throws IOException, ParseException, InterruptedException, CsvValidationException {
		
	
	 
	showTableFromDatabase(model);
	    
	    
	    

	 return "index";
	}
	
	public void showTableFromDatabase(Model model) {
		// show table from database
		  List<Double> status = new ArrayList();
	    List<Object[]> myClients = clientService.findAllClientsNames();
	    List<Object[]> listOfProdForClient = new ArrayList<>();
	    for(int i=0;i<myClients.size();i++) {
	    	Object[] currentClient = myClients.get(i);
	    	int id = ((Integer) currentClient[0]).intValue();
	    Object[] productionDetails = clientService.findAllProductionDetails(id);
	    listOfProdForClient.add(productionDetails);
		
	    }
	    for(int i=0;i<myClients.size();i++) {
	    	System.out.println(myClients.get(i)[0]+"+"+myClients.get(i)[1]+"+"+myClients.get(i)[2]+"+"+myClients.get(i)[3]+"+"+myClients.get(i)[4]+"+"+myClients.get(i)[5]);
	    	Double statusFraction = ((Double)(myClients.get(i)[5])).doubleValue() /((Double)(myClients.get(i)[3])).doubleValue();
    		//double statusFraction =Double.parseDouble(new DecimalFormat(".##").format(dailyProd/systemSize));
    		status.add(statusFraction);

	    }
	    String[] statusesArray = checkStatus(status);
		model.addAttribute("clients",myClients);
		model.addAttribute("productiondetails",listOfProdForClient);
		model.addAttribute("statusesArray",statusesArray);
		
		
		
		
	}
	
	String[] checkStatus(List<Double> statusValues){
		List<Double> fractionValues = new ArrayList<>();
		String[] statusesArray = new String[statusValues.size()];
		//check max number
		double max = statusValues.get(0);
		for(int i=0;i<statusValues.size();i++) {
			if(statusValues.get(i)>max)
				max = statusValues.get(i);
		}
		for(int i=0;i<statusValues.size();i++) {
			fractionValues.add(i, Math.abs(max-statusValues.get(i)));
			if(fractionValues.get(i)<=0.3)
				statusesArray[i] = "Green";
			else if(fractionValues.get(i)<=0.6 && statusValues.get(i)>0.3) 
				statusesArray[i] = "Orange";
			else
				statusesArray[i] = "Red";
		}
		
		return statusesArray;
	}
	
	
}
