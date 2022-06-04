package com.socialnetwork.project.dto;

import com.socialnetwork.project.util.ErrorCodeException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Data
@Builder
@Jacksonized
public class ErrorDTO {

    private String message;

    private Map<String, String> violations;

    private ErrorCodeException errorCode;
}
