package org.partha.wmcommon.entities;

import jakarta.persistence.*;
import lombok.*;
import org.partha.wmcommon.enums.AssetType;

import java.util.Date;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "WealthManager", name = "AssetMaster")
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String username;

	@Enumerated(EnumType.STRING)
	private AssetType assetType;

	@Column
	private String assetName;

	@Column
	private Double amount;

	@Column
	private Date  valuationDate;

}
