package com.connectionService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoutingParameters {
    private String routingEffort;
    private boolean ignoreAlarmForRoute;
}
