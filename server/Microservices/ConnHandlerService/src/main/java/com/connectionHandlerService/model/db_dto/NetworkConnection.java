package com.connectionHandlerService.model.db_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NetworkConnection {
    private int connectionId;
    private String connectionName;
    private String connectionAlias;
    private String connectionRate;
    private String serviceType;
    private String protectionType;
    private String connectionShape;
    private String connectionState;
    private String connNameFormat;
    private String customerName;
    private String groupType;
    private String routeSelectionMode;
    private String routingEffort;
    private String preplan;
    private String terminationEndPoints;
    private ServiceOrder serviceOrder;
}
