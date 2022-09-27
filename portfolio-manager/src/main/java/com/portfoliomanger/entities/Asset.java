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
public class Asset {
	
	private Integer id;
	private String name;
	private Double amount;
	private Date  recordDate;

}
