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
@Table(schema = "WealthManager", name = "InstrumentUniverseDetail")
public class InstrumentUniverseDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer instrumentUniverseMasterId;
	private String instrumentKey;
	private Date createdDate;
	private Date updatedDate;

}
