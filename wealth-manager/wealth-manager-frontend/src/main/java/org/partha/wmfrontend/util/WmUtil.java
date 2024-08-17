package org.partha.wmfrontend.util;

import org.partha.wmcommon.enums.ExportImportFormat;

import java.util.EnumSet;

public class WmUtil {

    public static EnumSet<ExportImportFormat> getHoldingImportFormats() {
        return EnumSet.of(ExportImportFormat.ZerodhaHoldingExport_Import,
                ExportImportFormat.UpstoxHoldingExport_Import,
                ExportImportFormat.AngelOneHoldingExport_Import
                );
    }
}
