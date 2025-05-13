package com.saber.spring_boot_web_demo.controllers;

import com.saber.spring_boot_web_demo.dto.ErrorResponseDto;
import com.saber.spring_boot_web_demo.dto.hi.HelloDto;
import com.saber.spring_boot_web_demo.dto.hi.HelloRequestDto;
import com.saber.spring_boot_web_demo.services.HelloService;
import com.saber.spring_boot_web_demo.services.routes.Headers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "${service.api.base-path}/hello", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "hello service", description = "hello service")
@AllArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @PostMapping(value = "/sayHello", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "sayHello", description = "sayHello", method = "post", tags = "hello service",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "helloRequest dto",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE
                            ,examples = @ExampleObject(value = "{\"firstName\": \"saber\",\"lastName\": \"azizi\"}")
                            , schema = @Schema(implementation = HelloRequestDto.class))} ))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HelloDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "504", description = "GATEWAY_TIMEOUT",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            } )
    public ResponseEntity<HelloDto> sayHello(@RequestBody @Valid @NotNull(message = "request  body is required") HelloRequestDto dto, HttpServletRequest request) {
        HelloDto helloDto = helloService.sayHello(dto,getCorrelation(request));
        return ResponseEntity.ok(helloDto);
    }

    @GetMapping(value = "/sayHello")
    @Operation(summary = "sayHello", description = "sayHello", method = "post", tags = "hello service",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "firstName", required = true, example = "bruce"),
                    @Parameter(in = ParameterIn.QUERY, name = "lastName", required = true, example = "lee")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HelloDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD_REQUEST",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "403", description = "FORBIDDEN",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "NOT_FOUND",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "503", description = "SERVICE_UNAVAILABLE",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
            @ApiResponse(responseCode = "504", description = "GATEWAY_TIMEOUT",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class))}),
    } )
    public ResponseEntity<HelloDto> sayHelloGet(@RequestParam(name = "firstName") @NotBlank(message = "firstName is required") String firstName
            , @RequestParam(name = "lastName") @NotBlank(message = "lastName is required") String lastName, HttpServletRequest request) {
        HelloDto helloDto = helloService.sayHello(firstName,lastName,getCorrelation(request));
        return ResponseEntity.ok(helloDto);
    }

    private String getCorrelation(HttpServletRequest request) {
        String correlation = "";
        if (request.getHeader(Headers.correlation) != null) {
            correlation = request.getHeader(Headers.correlation);
        } else {
            correlation = UUID.randomUUID().toString();
        }
        return correlation;
    }
}
