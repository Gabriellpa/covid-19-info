package com.spring.cov19.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CasesByDay {

	private String date;
	private int numberOfCases;
}
