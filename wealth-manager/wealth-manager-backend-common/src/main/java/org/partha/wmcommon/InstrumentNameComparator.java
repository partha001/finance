package org.partha.wmcommon;

import org.partha.wmcommon.entities.Instrument;

import java.util.Comparator;

public class InstrumentNameComparator implements Comparator<Instrument> {
    @Override
    public int compare(Instrument o1, Instrument o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
