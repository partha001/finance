package org.partha.wmcommon.entities;

import jakarta.persistence.*;
import lombok.*;
import org.partha.wmcommon.enums.InstrumentType;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "WealthManager", name = "InstrumentMaster")
public class Instrument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String instrumentType;
	private String name;
	private String exchange;
	private String key;
	private String symbol;
	private String isin;
	private Date listingDate;
	private Double faceValue;
	private String yahooFinanceTicker;
	private String sourceName;
	private String createdDate;

}
