package com.connectionService.model.dao;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ToString(exclude = "networkConnection")
public class ServiceOrder {

    @Id
    @SequenceGenerator(
            name = "serviceOrder_seq",
            sequenceName = "serviceOrder_seq",
            initialValue = 1000,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "serviceOrder_seq"
    )
    private int orderId;
    private int orderNumber;
    private int orderState;
    private int orderType;
    private int orderStep;
    private int stepState;
    private Date implementationDate;
    private Date ineffectDate;
    private String username;
    private boolean ignoreAlarm;
    private Date dueDate;
    private String comments;
    private Date createdDate;

    @OneToOne(
            cascade = CascadeType.ALL
//            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "connectionId",
            referencedColumnName = "connectionId"
    )
    private NetworkConnection networkConnection;
}
