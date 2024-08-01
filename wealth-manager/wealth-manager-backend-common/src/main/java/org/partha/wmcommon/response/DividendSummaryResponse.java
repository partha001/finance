package org.partha.wmcommon.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;
import org.partha.wmcommon.dto.DividendDto;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DividendSummaryResponse {
	
	List<DividendDto> list;

}
