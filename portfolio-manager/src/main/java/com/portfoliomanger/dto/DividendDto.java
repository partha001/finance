package com.portfoliomanger.dto;

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
public class DividendDto {
	
	private Integer year;
	private Integer quarter;
	private String symbol;
	private String name;
	private Double amount;

}
