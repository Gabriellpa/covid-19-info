package com.spring.cov19.models;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import static org.apache.commons.lang3.StringUtils.isNumeric;

import lombok.Data;

@Data
public class Country {

	private String country;
	private List<State> state;
	private List<CasesByDay> cases;
	private static List<String> headers;
	private static Map<String, Integer> statsCountryTotal;

	public static Country extractCsvDataCountry(String data, String country) throws IOException {
		StringReader csvReader = new StringReader(data);
		CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
		Iterable<CSVRecord> records = format.parse(csvReader);

		Country countryStat = new Country();
		countryStat.setCountry(country);
		statsCountryTotal = new HashMap<String, Integer>();
		List<State> stateStats = new ArrayList<State>();
		for (CSVRecord record : records) {
			headers = record.getParser().getHeaderNames();
			if(country.contentEquals(record.get("Country/Region"))) {
				stateStats.add(getState(record));
			}
		}

		List<CasesByDay> casesByDayContryTotalList = new ArrayList<CasesByDay>();
		for(int i = 4; i < headers.size(); i++) {
			CasesByDay casesByDayContryTotal = CasesByDay.builder()
					.date(headers.get(i))
					.numberOfCases(statsCountryTotal.get(headers.get(i)))
					.build();
			casesByDayContryTotalList.add(casesByDayContryTotal);
		}
		countryStat.setState(stateStats);
		countryStat.setCases(casesByDayContryTotalList);
		return countryStat;
	}
	
	private static State getState(CSVRecord record) {
		return State.builder()
				.state(record.get("Province/State"))
				.cases(getCases(record)).build();
	}
	
	private static List<CasesByDay> getCases(CSVRecord record) {
		List<CasesByDay> casesByday = new ArrayList<>();

		for(int i = 4; i< record.size(); i++) {
			CasesByDay cases = null;
			if(isNumeric(record.get(i)) ) {
				cases = CasesByDay.builder().numberOfCases(Integer.parseInt(record.get(i)))
						.date(headers.get(i)).build();	
				AddToSet(headers.get(i),Integer.parseInt(record.get(i)));
			}
			casesByday.add(cases);
		}
		
		return casesByday;
	}
	
	private static void AddToSet(String key, Integer value) {
		if(!statsCountryTotal.containsKey(key)) {
			statsCountryTotal.put(key, value);
		} else {
			statsCountryTotal.put(key, statsCountryTotal.get(key) + value);
		}
	}
}
