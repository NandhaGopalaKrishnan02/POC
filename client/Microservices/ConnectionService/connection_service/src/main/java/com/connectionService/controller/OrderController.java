package com.connectionService.controller;


import com.connectionService.model.dao.NetworkConnection;
import com.connectionService.model.dto.Order;
import com.connectionService.model.dto.OrderStatus;
import com.connectionService.service.ServiceOrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/connService/order")
public class OrderController {

    @Autowired
    private ServiceOrderImpl serviceOrder;


    @PostMapping("/initiate")

    public ResponseEntity<?> initiateServiceOrder(@RequestBody Order order){
        return ResponseEntity.ok().body(serviceOrder.createServiceOrder(order));
    }


//    @RequestMapping("/status")
//    public ResponseEntity<?> serviceOrderStatus(){
//    }
}
