package wmcommon.entities;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	
	private Integer id;
	private String exchange;
	private String symbol;
	private String name;
	private Date listingDate;
	private String isin;
	private Double faceValue;
	private Double price;
	private String priceTime;
	private String priceTimeZone;
	
	//example value NSE:SBIN
	private String key;

}
