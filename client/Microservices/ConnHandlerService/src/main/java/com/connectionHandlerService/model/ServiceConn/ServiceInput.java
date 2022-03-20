package com.connectionHandlerService.model.ServiceConn;

import com.connectionHandlerService.model.AssuranceParameters;
import com.connectionHandlerService.model.ConnectionParameters;
import com.connectionHandlerService.model.OrderParameters;
import com.connectionHandlerService.model.RoutingParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceInput {
    private ServiceParams serviceParams;
    private ConnectionParameters connectionParameters;
    private RoutingParameters routingParameters;
    private AssuranceParameters assuranceParameters;
    private OrderParameters orderParameters;
    private ServiceTransmissionParams serviceTransmissionParams;
}
