package org.partha.wmcommon.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
	
	private Integer id;
	private String name;
	private Double amount;
	private Date  recordDate;

}
