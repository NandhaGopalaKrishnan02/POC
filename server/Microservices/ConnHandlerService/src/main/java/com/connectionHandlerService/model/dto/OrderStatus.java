package com.connectionHandlerService.model.dto;

import com.connectionHandlerService.model.db_dto.ServiceOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatus {
    private String status;
    private ServiceOrder serviceOrder;
}
