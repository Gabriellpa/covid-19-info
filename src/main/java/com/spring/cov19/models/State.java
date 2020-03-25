package com.spring.cov19.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class State {

	private String state;
	private List<CasesByDay> cases;
}
