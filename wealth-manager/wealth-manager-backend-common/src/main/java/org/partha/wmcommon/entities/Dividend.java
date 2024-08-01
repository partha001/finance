package org.partha.wmcommon.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "WealthManager", name = "Dividend")
public class Dividend {

	@Id
	private Integer id;
	@Column
	private Integer dividendYear;
	@Column
	private Integer quarter;
	@Column
	private String symbol;
	@Column
	private String name;
	@Column
	private Double amount;

}


