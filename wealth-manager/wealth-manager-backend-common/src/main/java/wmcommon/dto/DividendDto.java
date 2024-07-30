package wmcommon.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DividendDto {

	private Integer id;
	private Integer dividendYear;
	private Integer quarter;
	private String symbol;
	private String name;
	private Double amount;
	private Double minAmount;
	private Double maxAmount;
	private Double avgAmount;
	private Integer frequency;


}
