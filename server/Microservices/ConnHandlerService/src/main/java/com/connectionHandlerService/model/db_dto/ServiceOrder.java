package com.connectionHandlerService.model.db_dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceOrder {
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
    private NetworkConnection networkConnection;
}
