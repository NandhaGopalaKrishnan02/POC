package com.connectionService.model.ServiceConn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceParams {
    private String connectionShape;
    private String routing;
    private boolean preplanConn;
    private String customerName;
    private String groupType;
}
