package com.spring.cov19.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cov19.models.Country;
import com.spring.cov19.models.LocationStats;
import com.spring.cov19.services.Cov19DataService;

@RestController
@RequestMapping("/cases")
public class Cov19DataController {

	@Autowired
	Cov19DataService covService;
	
	@GetMapping("/confirmed")
	public List<LocationStats> confirmedCases() throws IOException{
		return covService.confirmedCases();
	}
	
	@GetMapping("/death")
	public List<LocationStats> deathCases() throws IOException{
		return covService.deathsCases();
	}
	
	@GetMapping("/recovered")
	public List<LocationStats> recoveredCases() throws IOException{
		return covService.recoveredCases();
	}
	
	@GetMapping("/{country}")
	public Country confirmedCasesByCountry(@PathVariable("country") String country) throws IOException{
		return covService.confirmedCasesByCountry(country);
	}
}
