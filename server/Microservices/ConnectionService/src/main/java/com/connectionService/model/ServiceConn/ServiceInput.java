package com.connectionService.model.ServiceConn;

import com.connectionService.model.AssuranceParameters;
import com.connectionService.model.ConnectionParameters;
import com.connectionService.model.OrderParameters;
import com.connectionService.model.RoutingParameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ServiceInput {
    private ServiceParams serviceParams;
    private ConnectionParameters connectionParameters;
    private RoutingParameters routingParameters;
    private AssuranceParameters assuranceParameters;
    private OrderParameters orderParameters;
    private ServiceTransmissionParams serviceTransmissionParams;
}
