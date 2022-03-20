package com.connectionHandlerService.model.DlConn;

import com.connectionHandlerService.model.AssuranceParameters;
import com.connectionHandlerService.model.ConnectionParameters;
import com.connectionHandlerService.model.OrderParameters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DLInput {
    private ConnectionParameters connectionParameters;
    private AssuranceParameters assuranceParameters;
    private OrderParameters orderParameters;
}
