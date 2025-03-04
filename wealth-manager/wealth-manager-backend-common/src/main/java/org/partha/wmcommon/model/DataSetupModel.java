package org.partha.wmcommon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataSetupModel {
    private String instrumentType;
    private String instrumentName;
    private String universeName;
    private String requestType;
}
