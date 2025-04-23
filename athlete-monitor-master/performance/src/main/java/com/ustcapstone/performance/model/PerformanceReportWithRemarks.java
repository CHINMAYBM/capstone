package com.ustcapstone.performance.model;

public class PerformanceReportWithRemarks {
    private PerformanceReport performanceReport;
    private String remarks;

    public PerformanceReportWithRemarks(PerformanceReport performanceReport, String remarks) {
        this.performanceReport = performanceReport;
        this.remarks = remarks;
    }

    public PerformanceReport getPerformanceReport() {
        return performanceReport;
    }

    public void setPerformanceReport(PerformanceReport performanceReport) {
        this.performanceReport = performanceReport;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
