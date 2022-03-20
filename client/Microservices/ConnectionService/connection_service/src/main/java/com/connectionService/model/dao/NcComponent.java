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
public class NcComponent {
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
    private Long componentId;
    private int connectionRate;
    private String srcTpId;
    private String sinkTpId;
    private String srcNeId;
    private String sinkNeId;
    private String connectionGroupId;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "connectionId",
            referencedColumnName = "connectionId"
    )
    private NetworkConnection networkConnection;

}
