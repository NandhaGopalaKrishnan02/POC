package com.connectionService.model.dto;


import com.connectionService.model.dao.ServiceOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatus {
    private String status;
    private ServiceOrder serviceOrder;
}
