package com.sam.order.controller.dto;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@Data @AllArgsConstructor
public class ResponseDto {
    @Schema(
            description = "Status code of the response",
            example = "200"
    )
    private HttpStatus statusCode;
    @Schema(
            description = "Status message of the response",
            example = "Request processed successfully"
    )
    private String statusMessage;
}
