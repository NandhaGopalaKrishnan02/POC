package com.connectionService.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "node")
public class Tp {
    @Id
    @SequenceGenerator(
            name = "tp_sequence",
            sequenceName = "tp_sequence",
            initialValue = 1000,
            allocationSize = 1 // 1 means primary key will be incremented by 1 otherwise by default it will take 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tp_sequence"
    )
    private Long tpId;
    private int ptpId;
    private String tpPrimaryRate;
    private String tpNativeName;
    private String tpState;
    private String tpType;
    private int tpGroupId;

    @JsonIgnoreProperties(value = {"node", "hibernateLazyInitializer"})
    // If we are using LAZY load then we have to give @JsonIgnore then Jackson libray won't serailize it to json property
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "node_id",
            referencedColumnName = "nodeId"
    )
    private Node node;
}
