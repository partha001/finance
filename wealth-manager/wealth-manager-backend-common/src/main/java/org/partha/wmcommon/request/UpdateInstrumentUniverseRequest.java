package org.partha.wmcommon.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateInstrumentUniverseRequest {

    private String name;
    private Set<String> instrumentKeyList;
}
