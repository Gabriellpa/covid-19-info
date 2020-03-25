package com.spring.cov19.models;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import lombok.Builder;
import lombok.Data;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static com.spring.cov19.util.constUtils.*;



@Data
@Builder
public class LocationStats {
	private String state;
	private String country;
	private int latestTotalCases;
	private String date;
	
	public static List<LocationStats> extractCsvData(String data) throws IOException {
		StringReader csvReader = new StringReader(data);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
		List<LocationStats> stats = new ArrayList<>();
		for (CSVRecord record : records) {
			LocationStats locationStat = LocationStats.builder()
					.state(record.get("Province/State"))
					.country(record.get("Country/Region"))
					.latestTotalCases(getTotalCases(record))
					.date(getLastUpdateDate(record))
					.build();
			stats.add(locationStat);
		}
		return stats;
	}
	
	private static int getTotalCases(CSVRecord record) {
		try {
			return Integer.parseInt(record.get(getDate(0)));
		} catch (Exception e) {
			return Integer.parseInt(record.get(getDate(1)));
		}
	}
	
	private static String getLastUpdateDate(CSVRecord record) {
		try {
			if(EMPTY.equals(record.get(getDate(TODAY)))) {
				return getDate(YESTERDAY);
			}
			return getDate(TODAY);
		} catch (Exception e) {
			// HEADER DO NOT EXIST YET
			return getDate(YESTERDAY);
		}
	}
	//TODO: CHANGE TO HEADER OF CSV (:
	private static String getDate(int from) {
		LocalDate today = LocalDate.now();
		return String.format("%s/%s/%s", String.valueOf(today.getMonthValue()), String.valueOf(today.getDayOfMonth() - from), String.valueOf(today.getYear()).substring(2));
	}
}
