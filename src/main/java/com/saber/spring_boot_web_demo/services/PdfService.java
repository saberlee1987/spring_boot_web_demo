package com.saber.spring_boot_web_demo.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saber.spring_boot_web_demo.dto.ErrorResponseDto;
import com.saber.spring_boot_web_demo.dto.JasperReportDataDto;
import com.saber.spring_boot_web_demo.dto.PdfObjectDto;
import com.saber.spring_boot_web_demo.exceptions.GatewayException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class PdfService {
    @Autowired
    private ResourceLoader resourceLoader;

    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String INLINE_FILENAME = "inline; filename=\"";

    public ResponseEntity<byte[]> createPdf() {
        byte[] bytes = new byte[0];
        JasperReportDataDto<PdfObjectDto> jasperReportDataDto = new JasperReportDataDto<>();
        try {
            PdfObjectDto pdfObjectDto = PdfObjectDto.builder()
                    .sexKindTitle("مرد")
                    .fullName("عزیزی قلیچی")
                    .fatherName("آقا ده ده")
                    .birthDate("1366/09/16")
                    .identityNumber("34671")
                    .identityIssueCityTitle("تهران")
                    .identitySerial("986567")
                    .identifierNo("0079028748")
                    .postalCode("1655877493")
                    .address("تهران پارس خیابان جشنواره خیابان 186 شرقی ")
                    .phoneNumber("02177704784")
                    .mobileNumber("09365627895")
                    .email("saberazizi66@yahoo.com")
                    .build();
            jasperReportDataDto.setReportData(pdfObjectDto);

            jasperReportDataDto.setPdfClasspath("reportTest1.jasper");
            jasperReportDataDto.setPdfFilename("report.pdf");
            Map<String, Object> parameters = new ObjectMapper()
                    .convertValue(jasperReportDataDto.getReportData(), new TypeReference<>() {
                    });

            List<JasperPrint> jasperPrints = new ArrayList<>();
            JasperReport jasperReport = getResourceFromClasspath(jasperReportDataDto.getPdfClasspath());
            jasperPrints.add(JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource()));

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();

            return ResponseEntity.ok()
                    .header(CONTENT_DISPOSITION,
                            INLINE_FILENAME + jasperReportDataDto.getPdfFilename() + "\""
                    )
                    .body(outputStream.toByteArray());

        } catch (Exception e) {
            throwException(e);
        }
        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION,
                        INLINE_FILENAME + jasperReportDataDto.getPdfFilename() + "\""
                )
                .body(bytes);
    }


//    public void createPdf2(HttpServletResponse response) {
//
//        try {
//            BaseFont baseFont = BaseFont.createFont(BaseFont.IDENTITY_H, BaseFont.WINANSI, BaseFont.EMBEDDED);
//            Font font = new Font(baseFont, 14);
//            font.setStyle(Font.BOLD);
//
//            Document pdfDocument = new Document(PageSize.A4);
//            PdfWriter.getInstance(pdfDocument, response.getOutputStream());
//            pdfDocument.open();
////            URL url = resourceLoader.getResource("classpath:/images/tejaratLogo.png")
////                    .getURL();
////            Image image = Image.getInstance(url);
////            image.set
////            pdfDocument.add(image);
//            Paragraph paragraph = new Paragraph("سلام من صابر عزیزی هستم.برنامه نویس جاوا");
//
//            paragraph.setFont(font);
//            pdfDocument.add(paragraph);
//
//            Paragraph paragraph2 = new Paragraph("sdfsdfgsdhrg sdfjklsdfkjsdf sadflkdjflksdjf sldkfjsldfkj");
//
//            paragraph2.setFont(font);
//            pdfDocument.add(paragraph2);
//
//            Paragraph paragraph3 = new Paragraph("سلام من صابر عزیزی هستم.برنامه نویس جاوا");
//            paragraph3.setFont(font);
//            pdfDocument.add(paragraph3);
//
//            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=report_"+UUID.randomUUID()+".pdf");
////            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
//
//            pdfDocument.close();
//
//            log.info("pdf created .....");
//
//        } catch (Exception e) {
//            throwException(e);
//        }
//    }

    private static void throwException(Exception e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setCode(500);
        errorResponseDto.setText(e.getMessage());
        errorResponseDto.setOriginalMessage(String.format("{\"code\":%d,\"text\":\"%s\"}", 500, e.getMessage()));
        throw new GatewayException(500, UUID.randomUUID().toString(), errorResponseDto);
    }

    private JasperReport getResourceFromClasspath(String classpath) throws JRException, IOException {
        return (JasperReport) JRLoader.loadObject(
                resourceLoader.getResource("classpath:/reports_test/" + classpath)
                        .getInputStream()
        );
    }

}
