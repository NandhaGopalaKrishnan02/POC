package com.connectionService.model.dao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NetworkConnection {

    @Id
    @SequenceGenerator(
            name = "connection_seq",
            sequenceName = "connection_seq",
            initialValue = 1000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "connection_seq"
    )
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

    @OneToOne(
            cascade = CascadeType.ALL
//            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "connectionId",
            referencedColumnName = "connectionId"
    )
    private ServiceOrder serviceOrder;
}
