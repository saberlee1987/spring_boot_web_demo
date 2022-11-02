package com.saber.spring_boot_web_demo.services;

import com.saber.spring_boot_web_demo.dto.hi.HelloDto;
import com.saber.spring_boot_web_demo.dto.hi.HelloRequestDto;

public interface HelloService {
    HelloDto sayHello(HelloRequestDto dto,String correlation);
    HelloDto sayHello(String firstName,String lastName,String correlation);
}
