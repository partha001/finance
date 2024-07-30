package wmcommon.entities;

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
