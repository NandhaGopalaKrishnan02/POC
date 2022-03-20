package com.connectionHandlerService.model.dto;


import com.connectionHandlerService.model.ConnectionParameters;
import com.connectionHandlerService.model.InfraConn.InfraParams;
import com.connectionHandlerService.model.OrderParameters;
import com.connectionHandlerService.model.RoutingParameters;
import com.connectionHandlerService.model.ServiceConn.ServiceParams;
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
