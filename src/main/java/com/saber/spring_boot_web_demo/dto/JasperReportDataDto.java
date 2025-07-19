package com.saber.spring_boot_web_demo.dto;

import lombok.Data;

@Data
public class JasperReportDataDto<T> {
    private T reportData;
    private boolean isExcel;
    private String pdfFilename;
    private String pdfClasspath;
}
