package com.connectionHandlerService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionParameters {
    private String connectionRate;
    private String connectionName;
    private String connectionAlias;
    private String protectionType;
    private String serviceType;
    private String nameFormat;
    private String fromNE;
    private String toNE;
    private String fromPort;
    private String toPort;
    private String connGroupId;
}
