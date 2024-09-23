package com.kunal_vats.billing_service.entity.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FailureResponse {
    String errorType;
    String errorMessage;
    String errorCodes;
}
