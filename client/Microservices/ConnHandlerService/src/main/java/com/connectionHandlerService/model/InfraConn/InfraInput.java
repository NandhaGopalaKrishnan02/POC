package com.connectionHandlerService.model.InfraConn;

import com.connectionHandlerService.model.AssuranceParameters;
import com.connectionHandlerService.model.ConnectionParameters;
import com.connectionHandlerService.model.OrderParameters;
import com.connectionHandlerService.model.RoutingParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfraInput {
    private InfraParams infraParams;
    private ConnectionParameters connectionParameters;
    private RoutingParameters routingParameters;
    private AssuranceParameters assuranceParameters;
    private OrderParameters orderParameters;
    private InfraTransmissionParams infraTransmissionParams;
}
