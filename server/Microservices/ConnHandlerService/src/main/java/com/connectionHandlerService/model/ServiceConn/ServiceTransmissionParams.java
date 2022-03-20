package com.connectionHandlerService.model.ServiceConn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTransmissionParams {
    private String az_TRAIL_TRACE;
    private String za_TRAIL_TRACE;
    private String trailTraceFormat;
    private String trailTraceMismatch;
    private String trailTraceDisplayMode;
    private String ISDN_PRI_MODE;
}
