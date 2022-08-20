package com.portfoliomanger.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dividend {
	
	private Integer id;
	private Integer dividendYear;
	private Integer quarter;
	private String symbol;
	private String name;
	private Double amount;

}
