package com.connectionHandlerService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderParameters {
    private int orderNumber;
    private String orderType;
    private String orderStep;
    private boolean ignoreAlarm;
    private String dueDate;
    private String comments;
}
