package com.connectionService.model.dto;


import com.connectionService.model.ConnectionParameters;
import com.connectionService.model.InfraConn.InfraParams;
import com.connectionService.model.OrderParameters;
import com.connectionService.model.RoutingParameters;
import com.connectionService.model.ServiceConn.ServiceParams;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private ConnectionParameters connectionParameters;
    private OrderParameters orderParameters;
    private RoutingParameters routingParameters;
    private InfraParams infraParams;
    private ServiceParams serviceParams;
}
