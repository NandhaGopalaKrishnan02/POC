package com.connectionService.repo;

import com.connectionService.model.ConnectionParameters;
import com.connectionService.model.OrderParameters;
import com.connectionService.model.dao.NetworkConnection;
import com.connectionService.model.dao.ServiceOrder;
import com.connectionService.model.dto.Order;
import com.connectionService.service.ServiceOrderImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ServiceOrderInterfaceRepoTest {

    @Autowired
    private ServiceOrderRepo serviceOrderRepo;

    @Autowired
    ServiceOrderImpl serviceOrder;


    @Autowired
    private NetworkConnectionRepo networkConnectionRepo;

    @Test
    public void saveServiceOrder(){
        ConnectionParameters connectionParameters = ConnectionParameters.builder()
                .connectionName("MAR_phy")
                .connectionAlias("MAR_phy")
                .connectionRate("STM-16")
                .connGroupId("3456")
                .protectionType("unprotected")
                .nameFormat("Free Format")
                .serviceType("Not Applicable")
                .fromNE("LUNITE-A")
                .toNE("LUNITE-B")
                .fromPort("1-1-#-#-4-1")
                .toPort("1-1-#-#-1-1")
                .build();
        OrderParameters orderParameters = OrderParameters.builder()
                .orderStep("In effect")
                .ignoreAlarm(true)
                .orderType("Add")
                .build();
        Order order = Order.builder()
                .connectionParameters(connectionParameters)
                .orderParameters(orderParameters)
                .build();

        serviceOrder.createServiceOrder(order);

    }
    
    @Test
    public void joinNcSo(){
        
        ServiceOrder s = serviceOrderRepo.joinNcSO(2213);

        System.out.println("s = " + s);
    }
}