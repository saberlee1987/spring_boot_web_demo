package com.saber.spring_boot_web_demo.controllers;

import com.saber.spring_boot_web_demo.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping(value = "/pdf")
    public ResponseEntity<byte[]> createPdf() {
        return pdfService.createPdf();
    }

//    @GetMapping(value = "/pdf2",produces = MediaType.ALL_VALUE)
////    @ResponseStatus(HttpStatus.OK)
//    public void createPdf2(HttpServletResponse response) {
//        pdfService.createPdf2(response);
//    }
}
