package com.connectionService.model.InfraConn;

import com.connectionService.model.AssuranceParameters;
import com.connectionService.model.ConnectionParameters;
import com.connectionService.model.OrderParameters;
import com.connectionService.model.RoutingParameters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfraInput {
    private InfraParams infraParams;
    private ConnectionParameters connectionParameters;
    private RoutingParameters routingParameters;
    private AssuranceParameters assuranceParameters;
    private OrderParameters orderParameters;
    private InfraTransmissionParams infraTransmissionParams;
}
