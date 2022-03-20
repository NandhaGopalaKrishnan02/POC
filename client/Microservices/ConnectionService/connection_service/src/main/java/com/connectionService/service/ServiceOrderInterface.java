package com.connectionService.service;

import com.connectionService.model.dto.Order;
import com.connectionService.model.dto.OrderStatus;

import java.util.Map;

public interface ServiceOrderInterface {
    public OrderStatus createServiceOrder(Order order);
    public Map<String, Object> checkOrderStatus(int orderNumber);
    public Map<String, Object> changeOrderStatus(int orderNumber);
}
