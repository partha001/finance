package org.partha.wmcommon.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.partha.wmcommon.entities.InstrumentUniverse;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteInstrumentUniverseResponse {
    private String message;
    private String operationStatus;
}
