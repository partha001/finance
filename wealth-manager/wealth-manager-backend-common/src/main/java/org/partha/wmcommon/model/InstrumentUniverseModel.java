package org.partha.wmcommon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.partha.wmcommon.entities.Instrument;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstrumentUniverseModel {

    private String[] selectedEquities;
    private String[] selectedIndices;
    private String newUniverseName;
    private String selectedUniverseName;
    private String submitType;
    private String isUniverseDropdownChange;
    private String deleteRequest;

}
