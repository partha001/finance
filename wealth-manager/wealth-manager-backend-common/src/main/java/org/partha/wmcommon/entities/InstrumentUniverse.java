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
@Table(schema = "WealthManager", name = "InstrumentUniverseMaster")
public class InstrumentUniverse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Date createdDate;
	private Date updatedDate;

}
