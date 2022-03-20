package com.connectionService.service;

import com.connectionService.model.dao.NetworkConnection;
import com.connectionService.model.dao.ServiceOrder;
import com.connectionService.model.dto.Order;
import com.connectionService.model.dto.OrderStatus;
import com.connectionService.repo.NetworkConnectionRepo;
import com.connectionService.repo.ServiceOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceOrderImpl implements ServiceOrderInterface {

    @Autowired
    private ServiceOrderRepo serviceOrderRepo;

    @Autowired
    private NetworkConnectionRepo networkConnectionRepo;


    private ServiceOrder sOrder;

    @Override
    public OrderStatus createServiceOrder(Order order) {

        String connShape=null, connState=null, routeSelectionMode=null, terminationEndpoints=null, preplan=null,
                customerName=null, groupType=null, routingEffort=null;

        Map<String, String> connStates = new HashMap<String, String>() {{
            put("DL", "1");
            put("INFRA", "2");
            put("SERVICE", "3");
        }};

        Map<String, Integer> orderStep = new HashMap<String, Integer>(){{
            put("PLANNED", 1);
            put("LOCAL_DESIGN", 2);
            put("IMPLEMENTED", 3);
            put("IN_EFFECT", 4);
        }};

        Map<String, Integer> status = new HashMap<String, Integer>(){{
            put("IN_PROGRESS", 1);
            put("COMPLETED", 2);
            put("FAILED", 3);
        }};

        //Get logged username
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username="";
        username = principal instanceof UserDetails ?
             ((UserDetails)principal).getUsername() : principal.toString();

        // Initialize infra or service params
        if(order.getInfraParams() != null) {
            connShape = order.getInfraParams().getConnectionShape();
            connState = connStates.get("INFRA");
            routeSelectionMode = order.getInfraParams().getRouting();
            terminationEndpoints = order.getInfraParams().getTerminationEndPoints().size() == 2?"2":"1"; // 1 -> only one selected , 2-> both selected
            preplan = String.valueOf(  order.getInfraParams().isPreplanConn());
            customerName = order.getInfraParams().getCustomerName();
            groupType = order.getInfraParams().getGroupType();
            routingEffort = order.getRoutingParameters().getRoutingEffort();
        } else if (order.getServiceParams()!=null){
            connShape = order.getServiceParams().getConnectionShape();
            connState = connStates.get("SERVICE");
            routeSelectionMode = order.getServiceParams().getRouting();
            preplan = String.valueOf(  order.getServiceParams().isPreplanConn());
            customerName = order.getServiceParams().getCustomerName();
            groupType = order.getServiceParams().getGroupType();
            routingEffort = order.getRoutingParameters().getRoutingEffort();
        } else {
            connState = connStates.get("DL");
            connShape = "Simple (bi)";
        }

        // Network Connection Object
        NetworkConnection networkConnection = NetworkConnection.builder()
                .connectionName(order.getConnectionParameters().getConnectionName())
                .connectionRate(order.getConnectionParameters().getConnectionRate())
                .serviceType(order.getConnectionParameters().getServiceType())
                .connectionShape(connShape)
                .connectionState(connState)
                .routeSelectionMode(routeSelectionMode)
                .routingEffort(routingEffort)
                .protectionType(order.getConnectionParameters().getProtectionType())
                .terminationEndPoints(terminationEndpoints)
                .preplan(preplan)
                .connectionAlias(order.getConnectionParameters().getConnectionAlias())
                .groupType(groupType)
                .customerName(customerName)
                .connNameFormat(order.getConnectionParameters().getNameFormat())
                .build();

        // build serviceOrder Object
        ServiceOrder serviceOrder = ServiceOrder.builder()
                .networkConnection(networkConnection)
                .orderType(order.getOrderParameters().getOrderType().equals("Add") ? 1 : 2)
                .orderStep(orderStep.get("PLANNED"))
                .username(username)
                .stepState(status.get("IN_PROGRESS"))
                .ignoreAlarm(order.getOrderParameters().isIgnoreAlarm())
                .orderState(status.get("IN_PROGRESS"))
                .createdDate(new Date())
                .dueDate(order.getOrderParameters().getDueDate())
                .comments(order.getOrderParameters().getComments())
                .build();

        //save service Order
        sOrder = serviceOrderRepo.save(serviceOrder);
        return OrderStatus.builder().status("Order Created").serviceOrder(sOrder).build();
    }

    @Override
    public Map<String, Object> checkOrderStatus(int orderNumber) {
        return null;
    }

    @Override
    public Map<String, Object> changeOrderStatus(int orderNumber) {
        return null;
    }
}
