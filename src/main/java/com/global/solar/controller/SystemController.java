package com.global.solar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.global.solar.model.System;
import com.global.solar.service.SystemService;

@Controller
public class SystemController {

	@Autowired
	private SystemService systemService;



	@GetMapping("/getSystems")
	public String getAllSystems(Model model) {

		List<System> mySystems = systemService.getAllSystems();
		model.addAttribute("systems",mySystems);
		return "mysystems";
	}



	@GetMapping("/newSystem")
	public String addNewSystem() {
		return "addnewsystem";

	}

	@PostMapping("/getSystems")
	public String savePlant(System system) {
		systemService.saveSystems(system);
		return "redirect:/getSystems";

	}
}
