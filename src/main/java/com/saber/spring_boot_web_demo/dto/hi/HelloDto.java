package com.saber.spring_boot_web_demo.dto.hi;

import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.ToNumberPolicy;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HelloDto {
    private String message;

    @Override
    public String toString() {
        return new GsonBuilder()
                .setLenient()
                .setPrettyPrinting()
                .enableComplexMapKeySerialization()
                .setLongSerializationPolicy(LongSerializationPolicy.DEFAULT)
                .setObjectToNumberStrategy(ToNumberPolicy.BIG_DECIMAL)
                .create().toJson(this, HelloDto.class);

    }
}
