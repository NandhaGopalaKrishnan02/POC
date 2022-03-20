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
public class Pm {

    @Id
    @SequenceGenerator(
            name = "pm_seq",
            sequenceName = "pm_seq",
            initialValue = 1000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pm_seq"
    )
    private Long pmId;

    private String _15MCollectionType;
    private String _15MMonitoring;
    private String _15MInterval;
    private String _24HCollectionType;
    private String _24HMonitoring;
    private String _24HInterval;
    private String BiDirCollectionType;
    private String BiDirMonitoring;
    private String BiDirInterval;
    private boolean allowAlarmReporting;
    private boolean assignAlarmProfile;
    private boolean ignoreAlarmForRoute;
    private String alarmProfileName;



    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "connectionId",
            referencedColumnName = "connectionId"
    )
    private NetworkConnection networkConnection;
}
