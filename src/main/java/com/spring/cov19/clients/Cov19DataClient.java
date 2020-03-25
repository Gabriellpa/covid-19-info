package com.spring.cov19.clients;

import feign.RequestLine;

public interface Cov19DataClient {
	
	@RequestLine("GET CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv")
    String confirmedCases();
	
	@RequestLine("GET CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv")
	String deathsCases();
	
	@RequestLine("GET CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Recovered.csv")
	String recoveredCases();
}
