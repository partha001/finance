package org.partha.wmcommon.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "WealthManager", name = "HoldingMaster")
public class Holding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String username;
	@Column
	private String brokername;
	@Column
	private String isin;
	@Column
	private String brokersymbol;
	@Column
	private String exchangesymbol;
	@Column
	private Integer quantity;
	@Column
	private Double averagePrice;

}


