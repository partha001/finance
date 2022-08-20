package com.portfoliomanger.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DividendDto {

	private Integer id;
	private Integer dividendYear;
	private Integer quarter;
	private String symbol;
	private String name;
	private Double amount;
	private Double minAmount;
	private Double maxAmount;
	private Double avgAmount;


}
