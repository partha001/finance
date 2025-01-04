package org.partha.wmcommon.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstrumentDataDownloadResponseDto {

    private String key;
    private String ticker;
    private String startDate;
    private String endDate;
    private Integer recordsFetched;
    private Integer recordsInserted;

}
