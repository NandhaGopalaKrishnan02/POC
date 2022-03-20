package com.connectionService.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node {
    @Id
    @SequenceGenerator(
            name = "node_sequence",
            sequenceName = "node_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "node_sequence"
    )
    private Long nodeId;
    private String displayName;
    private String ne_alias;
    private Date commLostTime;
    private Date commUpTime;
    private int commState;
    private int activityState;
    private String neCapability;
    private String neType;

}
