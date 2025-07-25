package org.partha.wmcommon.projections;

public interface HoldingProjection {

    Integer getId();
    String getUsername();
    String getBrokername();
    String getIsin();
    String getBrokersymbol();
    Integer getQuantity();
    String getKey();
    String getSymbol();
    Double getAveragePrice();
    String getYahooFinanceTicker();

}
