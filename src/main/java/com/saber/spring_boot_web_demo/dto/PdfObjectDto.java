package com.saber.spring_boot_web_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PdfObjectDto {
    private String sexKindTitle;
    private String fullName;
    private String fatherName;
    private String birthDate;
    private String identityNumber;
    private String identityIssueCityTitle;
    private String identitySerial;
    private String identifierNo;
    private String postalCode;
    private String address;
    private String phoneNumber;
    private String mobileNumber;
    private String email;
}