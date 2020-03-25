package com.spring.cov19.services;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.cov19.clients.Cov19Client;
import com.spring.cov19.models.Country;
import com.spring.cov19.models.LocationStats;
import static com.spring.cov19.models.LocationStats.extractCsvData;
import static com.spring.cov19.models.Country.extractCsvDataCountry;

@Component
public class Cov19DataService {

	@Autowired
	Cov19Client covClient;
	
	public List<LocationStats> confirmedCases() throws IOException {
		return extractCsvData(covClient.getCovClient().confirmedCases());
	}
	
	public  List<LocationStats> deathsCases() throws IOException {
		return extractCsvData(covClient.getCovClient().deathsCases());
	}
	
	public List<LocationStats> recoveredCases() throws IOException {
		return extractCsvData(covClient.getCovClient().recoveredCases());
	}
	
	public Country confirmedCasesByCountry(String country) throws IOException {
		return extractCsvDataCountry(covClient.getCovClient().confirmedCases(), country);
	}
}
