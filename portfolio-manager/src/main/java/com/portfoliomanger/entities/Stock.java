package com.portfoliomanger.entities;

import java.util.Date;

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
public class Stock {
	
	private Integer id;
	private String exchange;
	private String symbol;
	private String name;
	private Date listingDate;
	private String isin;
	private Double faceValue;
	private Double price;
	
	//example value NSE:SBIN
	private String key;

}
