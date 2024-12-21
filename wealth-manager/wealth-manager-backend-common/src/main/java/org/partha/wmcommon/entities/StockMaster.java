package org.partha.wmcommon.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "WealthManager", name = "StockMaster")
public class StockMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String exchange;
	private String symbol;
	private String name;
	private Date listingDate;
	private String isin;
	private Double faceValue;
	
	//example value NSE:SBIN
	private String key;

}
