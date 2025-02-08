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
@Table(schema = "WealthManager", name = "dailypricemaster")
public class DailyPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "priceDate")
    private Date priceDate;

    @Column(name="key")
    private String key;

    @Column(name="AdjClosePrice")
    private Double adjClosePrice;

    @Column(name="ClosePrice")
    private Double closePrice;

    @Column(name="OpenPrice")
    private Double openPrice;

    @Column(name="High")
    private Double high;

    @Column(name="Low")
    private Double low;

    @Column(name="Volume")
    private Double volume;

    @Column(name="createTime")
    private Date createTime;



}
