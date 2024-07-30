package wmcommon.entities;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dividend {
	
	private Integer id;
	private Integer dividendYear;
	private Integer quarter;
	private String symbol;
	private String name;
	private Double amount;

}
