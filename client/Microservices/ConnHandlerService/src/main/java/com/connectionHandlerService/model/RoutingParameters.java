package com.connectionHandlerService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoutingParameters {
    private String routingEffort;
    private boolean ignoreAlarmForRoute;
}
