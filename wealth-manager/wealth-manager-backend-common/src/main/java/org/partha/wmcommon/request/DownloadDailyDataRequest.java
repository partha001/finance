package org.partha.wmcommon.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DownloadDailyDataRequest {

    private String ticker;
    private String key;
    private String start_date;
    private String end_date;
}
