package org.partha.wmfrontend.util;

import org.partha.wmcommon.enums.AssetChartType;
import org.partha.wmcommon.enums.DividendChartType;
import org.partha.wmcommon.enums.ExportImportFormat;
import org.partha.wmcommon.enums.InstrumentType;

import java.util.EnumSet;

public class WmUtil {

    public static EnumSet<ExportImportFormat> getHoldingImportFormats() {
        return EnumSet.of(ExportImportFormat.ZerodhaHoldingExport_Import,
                ExportImportFormat.UpstoxHoldingExport_Import,
                ExportImportFormat.AngelOneHoldingExport_Import
        );
    }


    public static EnumSet<DividendChartType> getDividendSummaryTypes() {
        return EnumSet.of(DividendChartType.DividendSummaryByYear,
                DividendChartType.DividendSummaryByQuarter,
                DividendChartType.DividendSummaryByEquity
        );
    }

    public static EnumSet<AssetChartType> getAssetChartTypes() {
        return EnumSet.of(AssetChartType.Chart_AssetVsTime,
                AssetChartType.Chart_CurrentAssetCategoryDistribution
        );
    }

    public static EnumSet<InstrumentType> getInstrumentTypes() {
        return EnumSet.of(InstrumentType.Index,
                InstrumentType.Equity,
                InstrumentType.Commodity
        );
    }
}
