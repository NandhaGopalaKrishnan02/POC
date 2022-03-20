package com.connectionHandlerService.model.InfraConn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InfraTransmissionParams {
    private String az_TRAIL_TRACE;
    private String za_TRAIL_TRACEE;
    private String trailTraceFormat;
    private String trailTraceMismatch;
    private String trailTraceDisplayMode;
}
