package com.connectionHandlerService.model.ServiceConn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceParams {
    private String connectionShape;
    private String routing;
    private boolean preplanConn;
    private String customerName;
    private String groupType;
}
