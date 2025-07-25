package org.partha.wmcommon.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HoldingDto {
	
	private Integer id;
	
	private String username;
	
	private String brokername;
	
	private String isin;
	
	private String brokersymbol;
	
	private String exchangesymbol;
	
	private Integer quantity;

	private Double averagePrice;

	private String screenerLink;

	private Double investmentAmount;

	private String key;

	private String symbol;

	private String yahooFinanceTicker;

}


