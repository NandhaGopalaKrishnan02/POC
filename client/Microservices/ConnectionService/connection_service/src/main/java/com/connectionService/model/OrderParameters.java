package com.connectionService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderParameters {
    private int orderNumber;
    private String orderType;
    private String orderStep;
    private boolean ignoreAlarm;
    private Date dueDate;
    private String comments;
}
